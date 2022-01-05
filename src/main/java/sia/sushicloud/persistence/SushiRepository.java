package sia.sushicloud.persistence;

import sia.sushicloud.model.Sushi;

public interface SushiRepository {
    Sushi save(Sushi design);
}
