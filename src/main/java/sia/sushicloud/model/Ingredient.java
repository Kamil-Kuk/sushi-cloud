package sia.sushicloud.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Ingredient {
    private final Long id;
    private final String name;
    private final IngredientType ingredientType;

    public enum IngredientType {
        FISH, VEGGIE, ADDITIONS
    }
}
