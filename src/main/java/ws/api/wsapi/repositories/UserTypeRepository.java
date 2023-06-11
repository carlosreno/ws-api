package ws.api.wsapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ws.api.wsapi.model.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType,Long> {
}
