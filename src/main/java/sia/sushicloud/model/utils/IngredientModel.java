package sia.sushicloud.model.utils;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import sia.sushicloud.model.Ingredient;

@Data
public class IngredientModel extends RepresentationModel<Ingredient> {

    private String name;
    private Ingredient.IngredientType ingredientType;

    public IngredientModel(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.ingredientType = ingredient.getIngredientType();
    }
}
