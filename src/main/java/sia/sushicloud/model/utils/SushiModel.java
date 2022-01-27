package sia.sushicloud.model.utils;

import lombok.Data;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import sia.sushicloud.model.Sushi;

import java.util.Date;

@Data
public class SushiModel extends RepresentationModel<Sushi> {

    private static final IngredientModelAssembler ingredientAssembler = new IngredientModelAssembler();
    private final String name;
    private final Date createdAt;
    private final CollectionModel<IngredientModel> ingredients;

    public SushiModel(Sushi sushi) {
        this.name = sushi.getName();
        this.createdAt = sushi.getCreatedAt();
        this.ingredients = ingredientAssembler.toCollectionModel(sushi.getIngredient());
    }
}
