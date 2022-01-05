package sia.sushicloud.contoller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import sia.sushicloud.model.Sushi;
import sia.sushicloud.model.Ingredient;
import sia.sushicloud.model.SushiType;
import sia.sushicloud.persistence.SushiIngredientRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignSushiController {

    private final SushiIngredientRepository ingredientRepository;

    @Autowired
    public DesignSushiController(SushiIngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);
        Ingredient.IngredientType[] types = Ingredient.IngredientType.values();
        for(Ingredient.IngredientType type: types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        model.addAttribute("design", new Sushi());
        model.addAttribute("sushiType", getSushiTypesStringList());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Sushi sushi, Errors errors){
        if(errors.hasErrors()){
            return "design";
        }
        log.info("processing designed sushi called " + sushi.getName());
        return "redirect:/orders/current";
    }

    private List<String> getSushiTypesStringList() {
        SushiType[] types = SushiType.values();
        List<String> result = new ArrayList<>();
        for(SushiType type: types){
            result.add(type.toString().toLowerCase().replace("_"," "));
        }
        return result;
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.IngredientType type) {
        List<Ingredient> result = new ArrayList<>();
        for(Ingredient ingredient: ingredients){
            if(type.equals(ingredient.getIngredientType())){
                result.add(ingredient);
            }
        }
        return result;
    }


}
