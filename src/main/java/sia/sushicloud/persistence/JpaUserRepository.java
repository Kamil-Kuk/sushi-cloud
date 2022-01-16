package sia.sushicloud.persistence;

import org.springframework.data.repository.CrudRepository;
import sia.sushicloud.model.User;

public interface JpaUserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
