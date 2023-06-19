package ws.api.wsapi.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ws.api.wsapi.model.jpa.UserCredentials;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserCredentials,Long> {
    Optional<UserCredentials> findByUserName(String username);
}
