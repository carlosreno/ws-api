package ws.api.wsapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ws.api.wsapi.exception.NotFoundException;
import ws.api.wsapi.model.redis.UserRecoveryCode;
import ws.api.wsapi.repositories.jpa.UserDetailsRepository;
import ws.api.wsapi.repositories.redis.UserRecoveryCodeRepository;
import ws.api.wsapi.service.UserDetailService;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService  implements UserDetailService {
    private final UserDetailsRepository userDetailsRepository;
    private final UserRecoveryCodeRepository recoveryCodeRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optUserDetail = userDetailsRepository.findByUserName(username);
        if (optUserDetail.isPresent()){
            return optUserDetail.get();
        }
        throw new NotFoundException("usuário com o username informado não existe");
    }
    @Override
    public Object sendRecoveryCode(String email) {
        String code = String.format("%4d",new Random().nextInt(9999));
        recoveryCodeRepository.save(UserRecoveryCode.builder().code(code).build());
        return null;
    }
}
