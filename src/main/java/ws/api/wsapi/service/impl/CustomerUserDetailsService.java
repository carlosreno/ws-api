package ws.api.wsapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ws.api.wsapi.exception.NotFoundException;
import ws.api.wsapi.repositories.UserDetailsRepository;

@Service
public class CustomerUserDetailsService  implements UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optUserDetail = userDetailsRepository.findByUserName(username);
        if (optUserDetail.isPresent()){
            return optUserDetail.get();
        }
        throw new NotFoundException("usuário com o username informado não existe");
    }
}
