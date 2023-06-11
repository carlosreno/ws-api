package ws.api.wsapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ws.api.wsapi.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
