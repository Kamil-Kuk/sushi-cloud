package sia.sushicloud.contoller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import sia.sushicloud.model.Order;
import sia.sushicloud.model.Sushi;
import sia.sushicloud.model.Ingredient;
import sia.sushicloud.model.SushiType;
import sia.sushicloud.persistence.SushiIngredientRepository;
import sia.sushicloud.persistence.SushiRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignSushiController {

    private final SushiIngredientRepository ingredientRepository;
    private SushiRepository sushiRepository;

    @Autowired
    public DesignSushiController(SushiIngredientRepository ingredientRepository, SushiRepository sushiRepository){
        this.ingredientRepository = ingredientRepository;
        this.sushiRepository = sushiRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "sushi")
    public Sushi sushi() {
        return new Sushi();
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
    public String processDesign(@Valid Sushi design, Errors errors, @ModelAttribute Order order){
        if(errors.hasErrors()){
            return "design";
        }
        Sushi saved = sushiRepository.save(design);
        order.addSushi(saved);
        log.info("processing designed sushi called " + design.getName());
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
