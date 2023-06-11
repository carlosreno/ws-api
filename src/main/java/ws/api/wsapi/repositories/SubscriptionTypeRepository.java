package ws.api.wsapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ws.api.wsapi.model.SubscriptionType;

@Repository
public interface SubscriptionTypeRepository extends JpaRepository<SubscriptionType,Long> {
}
