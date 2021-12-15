package sia.sushicloud.contoller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.sushicloud.assets.Sushi;
import sia.sushicloud.assets.SushiIngredient;
import sia.sushicloud.assets.SushiType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignSushiController {

    @GetMapping
    public String showDesignForm(Model model){
        List<SushiIngredient> ingredients = Arrays.asList(
                new SushiIngredient(1L, "tuna", SushiIngredient.IngredientType.FISH),
                new SushiIngredient(2L, "salmon", SushiIngredient.IngredientType.FISH),
                new SushiIngredient(3L, "smoked salmon", SushiIngredient.IngredientType.FISH),
                new SushiIngredient(4L, "shrimp", SushiIngredient.IngredientType.FISH),
                new SushiIngredient(5L, "avocado", SushiIngredient.IngredientType.VEGGIE),
                new SushiIngredient(6L, "cucumber", SushiIngredient.IngredientType.VEGGIE),
                new SushiIngredient(7L, "radish", SushiIngredient.IngredientType.VEGGIE),
                new SushiIngredient(8L, "philadelphia cheese", SushiIngredient.IngredientType.ADDITIONS),
                new SushiIngredient(8L, "wasabi", SushiIngredient.IngredientType.ADDITIONS)
        );
        SushiIngredient.IngredientType[] types = SushiIngredient.IngredientType.values();
        for(SushiIngredient.IngredientType type: types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        model.addAttribute("design", new Sushi());
        model.addAttribute("sushiType", getSushiTypesStringList());
        return "design";
    }

    private List<String> getSushiTypesStringList() {
        SushiType[] types = SushiType.values();
        List<String> result = new ArrayList<>();
        for(SushiType type: types){
            result.add(type.toString().toLowerCase().replace("_"," "));
        }
        return result;
    }

    private List<SushiIngredient> filterByType(List<SushiIngredient> ingredients, SushiIngredient.IngredientType type) {
        List<SushiIngredient> result = new ArrayList<>();
        for(SushiIngredient ingredient: ingredients){
            if(type.equals(ingredient.getType())){
                result.add(ingredient);
            }
        }
        return result;
    }


}
