package sia.sushicloud.model.utils;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import sia.sushicloud.contoller.rest.DesignSushiRestController;
import sia.sushicloud.model.Sushi;

public class SushiModelAssembler extends RepresentationModelAssemblerSupport<Sushi, SushiModel> {

    public SushiModelAssembler() {
        super(DesignSushiRestController.class, SushiModel.class);
    }

    @Override
    public SushiModel toModel(Sushi entity) {
        return createModelWithId(entity.getId(), entity);
    }

    @Override
    protected SushiModel instantiateModel(Sushi entity) {
        return new SushiModel(entity);
    }
}
