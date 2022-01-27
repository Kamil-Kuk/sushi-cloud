package sia.sushicloud.model.utils;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import sia.sushicloud.model.Ingredient;

@Data
@Relation(value = "ingredient", collectionRelation = "ingredientList")
public class IngredientModel extends RepresentationModel<Ingredient> {

    private String name;
    private Ingredient.IngredientType ingredientType;

    public IngredientModel(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.ingredientType = ingredient.getIngredientType();
    }
}
