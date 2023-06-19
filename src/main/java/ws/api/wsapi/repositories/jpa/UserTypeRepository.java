package ws.api.wsapi.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ws.api.wsapi.model.jpa.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType,Long> {
}
