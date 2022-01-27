package sia.sushicloud.contoller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sia.sushicloud.model.Ingredient;
import sia.sushicloud.model.Sushi;
import sia.sushicloud.model.utils.IngredientModel;
import sia.sushicloud.model.utils.IngredientModelAssembler;
import sia.sushicloud.model.utils.SushiModel;
import sia.sushicloud.model.utils.SushiModelAssembler;
import sia.sushicloud.persistence.JpaSushiIngredientRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/ingredients", produces="application/json")
@CrossOrigin(origins = "*")
public class IngredientRestController {

    private JpaSushiIngredientRepository ingredientRepository;

    @Autowired
    public IngredientRestController(JpaSushiIngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public Iterable<IngredientModel> allIngredients() {
        Iterable<Ingredient> ingredientList = ingredientRepository.findAll();
        CollectionModel<IngredientModel> ingredientModels = new IngredientModelAssembler().toCollectionModel(ingredientList);
        ingredientModels.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(IngredientRestController.class).allIngredients()).withRel("ingredients"));
        return ingredientModels;
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientModel> getById(@PathVariable("id") Long id){
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(id);
        if(optionalIngredient.isPresent()) {
            IngredientModel ingredientModel = new IngredientModelAssembler().toModel(optionalIngredient.get());
            return new ResponseEntity<>(ingredientModel, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
