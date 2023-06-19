package ws.api.wsapi.repositories.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ws.api.wsapi.model.redis.UserRecoveryCode;

@Repository
public interface UserRecoveryCodeRepository extends CrudRepository<UserRecoveryCode,String> {
}
