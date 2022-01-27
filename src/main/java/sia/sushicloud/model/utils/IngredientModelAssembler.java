package sia.sushicloud.model.utils;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import sia.sushicloud.contoller.rest.IngredientRestController;
import sia.sushicloud.model.Ingredient;
import sia.sushicloud.model.Sushi;

public class IngredientModelAssembler extends RepresentationModelAssemblerSupport<Ingredient, IngredientModel> {

    public IngredientModelAssembler() {
        super(IngredientRestController.class, IngredientModel.class);
    }

    @Override
    public IngredientModel toModel(Ingredient entity) {
        return createModelWithId(entity.getId(), entity);
    }

    @Override
    protected IngredientModel instantiateModel(Ingredient entity) {
        return new IngredientModel(entity);
    }
}
