package sia.sushicloud.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import sia.sushicloud.model.Sushi;

public interface JpaSushiRepository extends PagingAndSortingRepository<Sushi, Long> {
}
