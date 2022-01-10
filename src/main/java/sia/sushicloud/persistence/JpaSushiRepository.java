package sia.sushicloud.persistence;

import org.springframework.data.repository.CrudRepository;
import sia.sushicloud.model.Sushi;

public interface JpaSushiRepository extends CrudRepository<Sushi, Long> {
}
