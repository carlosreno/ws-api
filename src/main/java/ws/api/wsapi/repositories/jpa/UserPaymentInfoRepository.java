package ws.api.wsapi.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ws.api.wsapi.model.jpa.UserPaymentInfo;

public interface UserPaymentInfoRepository extends JpaRepository<UserPaymentInfo, Long> {
}