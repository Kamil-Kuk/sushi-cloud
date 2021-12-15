package sia.sushicloud.assets;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SushiIngredient {
    private final Long id;
    private final String name;
    private final IngredientType type;

    public enum IngredientType {
        FISH, VEGGIE, ADDITIONS
    }
}
