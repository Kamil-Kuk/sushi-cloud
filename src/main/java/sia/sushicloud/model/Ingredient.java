package sia.sushicloud.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private IngredientType ingredientType;

    public enum IngredientType {
        FISH, VEGGIE, ADDITIONS
    }
}
