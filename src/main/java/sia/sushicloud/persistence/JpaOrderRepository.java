package sia.sushicloud.persistence;

import org.springframework.data.repository.CrudRepository;
import sia.sushicloud.model.Order;

import java.util.Date;
import java.util.List;

public interface JpaOrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByZip(String zip);

    List<Order> readByZipAndPlaceAtBetween(String zip, Date startDate, Date endDate);
}
