package sia.sushicloud.assets;

import lombok.Data;

import java.util.List;

@Data
public class Sushi {

    private String name;
    private List<String> ingredients;
    private SushiType type;
}
