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
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService  implements UserDetailService {
    private final UserDetailsRepository userDetailsRepository;
    private final UserRecoveryCodeRepository recoveryCodeRepository;
    private final MailIntegration mailIntegration;
    @Override
    public MessageDTO sendRecoveryCode(String email) {
        UserRecoveryCode userRecoveryCode;
        String code = String.format("%4d", new Random().nextInt(10000));
        var userRecoveryCodeOptional =
                recoveryCodeRepository.findUserRecoveryCodesByEmail(email);
        if (userRecoveryCodeOptional.isEmpty()) {
            var user = userDetailsRepository.findByUserName(email);
            if (user.isEmpty()) {
                throw new NotFoundException("Usuário não encontrado");
            }
            userRecoveryCode = new UserRecoveryCode();
            userRecoveryCode.setEmail(email);
            userRecoveryCode.setCode(code);
            userRecoveryCode.setCreateDate(LocalDateTime.now());
        }else {
            userRecoveryCode = userRecoveryCodeOptional.get();
            var limitTime = LocalDateTime.now().minusMinutes(1);
            if (userRecoveryCode.getCreateDate().isBefore(limitTime)){
                System.out.println(userRecoveryCode);
                userRecoveryCode = UserRecoveryCode.builder()
                        .id(userRecoveryCode.getId())
                        .email(userRecoveryCode.getEmail())
                        .code(code)
                        .createDate(LocalDateTime.now())
                        .build();
            }
            System.out.println(userRecoveryCode);
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
}
