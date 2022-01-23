package sia.sushicloud.persistence;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import sia.sushicloud.model.Order;
import sia.sushicloud.model.User;

import java.util.List;

public interface JpaOrderRepository extends PagingAndSortingRepository<Order, Long> {

//    List<Order> findByZip(String zip);
//
//    List<Order> readByZipAndPlaceAtBetween(String zip, Date startDate, Date endDate);

    List<Order> findAllByUserOrderByPlaceAtDesc(User user, Pageable pageable);
}
