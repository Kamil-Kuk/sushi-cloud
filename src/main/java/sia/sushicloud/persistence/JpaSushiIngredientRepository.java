package sia.sushicloud.persistence;

import org.springframework.data.repository.CrudRepository;
import sia.sushicloud.model.Ingredient;

public interface JpaSushiIngredientRepository extends CrudRepository<Ingredient, Long> {

}
