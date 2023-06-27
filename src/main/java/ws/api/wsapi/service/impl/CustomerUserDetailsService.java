package ws.api.wsapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ws.api.wsapi.dto.DtoRecovery;
import ws.api.wsapi.dto.message.MessageDTO;
import ws.api.wsapi.exception.BusinessException;
import ws.api.wsapi.exception.NotFoundException;
import ws.api.wsapi.integration.MailIntegration;
import ws.api.wsapi.model.jpa.UserCredentials;
import ws.api.wsapi.model.redis.UserRecoveryCode;
import ws.api.wsapi.repositories.jpa.UserDetailsRepository;
import ws.api.wsapi.repositories.redis.UserRecoveryCodeRepository;
import ws.api.wsapi.service.UserDetailService;
import ws.api.wsapi.utils.PassWordUtils;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService  implements UserDetailService {
    private final UserDetailsRepository userDetailsRepository;
    private final UserRecoveryCodeRepository recoveryCodeRepository;
    private final MailIntegration mailIntegration;
    private UserRecoveryCode userRecoveryCode;
    @Override
    public MessageDTO sendRecoveryCode(String email) {

        String code = generateForCaracteres();
        var userRecoveryCodeOptional =
                recoveryCodeRepository.findUserRecoveryCodesByEmail(email);
        userRecoveryCode = veryfyRecoveryCode(userRecoveryCodeOptional,email,code);
        if (!userRecoveryCode.isCodeValid()){
            userRecoveryCode = regularizeCode(userRecoveryCode,code);
        }

        try {
            recoveryCodeRepository.save(userRecoveryCode);
            mailIntegration.send(email, "Codigo de recuperação:"+code+"<br>Este código expirará em 10 minutos","teste");
            HttpStatus status = HttpStatus.OK;
            return MessageDTO.builder()
                    .httpStatus(status)
                    .message("enviado com sucesso")
                    .statusCode(status.value())
                    .build();

        }catch (Exception e){
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return MessageDTO.builder()
                    .httpStatus(status)
                    .message("falha ao enviar o codigo")
                    .statusCode(status.value())
                    .build();

        }

    }

    private UserRecoveryCode regularizeCode(UserRecoveryCode userRecoveryCode,String code) {
            return userRecoveryCode.builder()
                    .id(userRecoveryCode.getId())
                    .email(userRecoveryCode.getEmail())
                    .code(code)
                    .createDate(LocalDateTime.now())
                    .build();

    }

    private UserRecoveryCode veryfyRecoveryCode(Optional<UserRecoveryCode> userRecoveryCode, String email,String code) {
        if (userRecoveryCode.isEmpty()) {
            var user = userDetailsRepository.findByUserName(email);
            if (user.isEmpty()) {
                throw new NotFoundException("Usuário não encontrado");
            }
            var newUserRecoveryCode = new UserRecoveryCode();
            newUserRecoveryCode.setEmail(email);
            newUserRecoveryCode.setCode(code);
            newUserRecoveryCode.setCreateDate(LocalDateTime.now());
            return newUserRecoveryCode;
        }
        return userRecoveryCode.get();
    }

    @Override
    public boolean recoveryIsValid(String code, String email) {
        var userRecoveryCodeOptional =
                recoveryCodeRepository.findUserRecoveryCodesByEmail(email);
        if (userRecoveryCodeOptional.isEmpty()){
            throw new NotFoundException("Usuário não encontrado");
        }
        UserRecoveryCode recoveryCode = userRecoveryCodeOptional.get();
        if (recoveryCode.getCode().equals(code)){
            var timeValid = LocalDateTime.now().minusMinutes(1);
            if (recoveryCode.getCreateDate().isAfter(timeValid)){
                return true;
            }
            throw new BusinessException("o codigo expirou");
        }
        return false;
    }

    @Override
    public MessageDTO updatePasswordByRecoveryCode(DtoRecovery dtoRecovery) {
        if (recoveryIsValid(dtoRecovery.getRecoveryCode(),dtoRecovery.getEmail())) {
            var optUSerDetail = userDetailsRepository.findByUserName(dtoRecovery.getEmail());
            optUSerDetail.ifPresent(userCredentials ->
                    userCredentials.setPassword(PassWordUtils.encode(dtoRecovery.getPassword())));
            userDetailsRepository.save(optUSerDetail.get());

        }else {
            throw new BusinessException("codigo invalido");
        }
        HttpStatus status = HttpStatus.OK;
        return MessageDTO.builder()
                .httpStatus(status)
                .message("Atualizado com sucesso")
                .statusCode(status.value())
                .build();
    }

    @Override
    public UserCredentials loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userDetailsRepository.findByUserName(username);
        if (user.isPresent()){
            return user.get();
        }
        throw new NotFoundException("usuário com o username informado não existe");
    }

    private static String generateForCaracteres() {
        String code = String.format("%4d", new Random().nextInt(10000));
        return code;
    }
}
