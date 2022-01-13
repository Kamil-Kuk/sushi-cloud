package sia.sushicloud.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;

    private final String name;

    @Enumerated(EnumType.STRING)
    private final IngredientType ingredientType;

    public enum IngredientType {
        FISH, VEGGIE, ADDITIONS
    }
}
