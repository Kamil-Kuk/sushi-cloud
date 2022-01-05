package sia.sushicloud.persistence;

import sia.sushicloud.model.Ingredient;

public interface SushiIngredientRepository {

    Iterable<Ingredient> findAll();
    Ingredient findById (Long id);
    Ingredient save (Ingredient ingredient);
}
