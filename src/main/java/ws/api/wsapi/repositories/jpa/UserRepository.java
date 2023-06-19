package ws.api.wsapi.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ws.api.wsapi.model.jpa.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
