package ws.api.wsapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ws.api.wsapi.model.UserPaymentInfo;

public interface UserPaymentInfoRepository extends JpaRepository<UserPaymentInfo, Long> {
}