package sia.sushicloud.contoller.mvc;

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
import sia.sushicloud.persistence.JpaSushiIngredientRepository;
import sia.sushicloud.persistence.JpaSushiRepository;
import sia.sushicloud.persistence.SushiIngredientRepository;
import sia.sushicloud.persistence.SushiRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignSushiController {

    private final JpaSushiIngredientRepository ingredientRepository;
    private JpaSushiRepository sushiRepository;

    @Autowired
    public DesignSushiController(JpaSushiIngredientRepository ingredientRepository, JpaSushiRepository sushiRepository){
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
        return Arrays.stream(SushiType.values()).map(Enum::toString).collect(Collectors.toList());
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
