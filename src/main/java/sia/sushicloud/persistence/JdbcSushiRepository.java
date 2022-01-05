package sia.sushicloud.persistence;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import sia.sushicloud.model.Sushi;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@Repository
public class JdbcSushiRepository implements SushiRepository {

    private final JdbcTemplate jdbc;

    public JdbcSushiRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Sushi save(Sushi design) {
        Long sushiId = saveSushiInfo(design);
        design.setId(sushiId);
        for (String ingredient: design.getIngredients()){
            saveIngredientToSushi(ingredient, sushiId);
        }
        return design;
    }

    private void saveIngredientToSushi(String ingredient, Long sushiId) {
        jdbc.update("INSERT INTO Sushi_Ingredient (sushi, ingredient) VALUES (?, ?)", sushiId, ingredient);
    }

    private Long saveSushiInfo(Sushi sushi) {
        sushi.setCreatedAt(new Date());
        PreparedStatementCreatorFactory preparedStatementCreatorFactory = new PreparedStatementCreatorFactory("INSERT INTO Sushi (name, createdAt) values (?, ?)", Types.VARCHAR, Types.TIMESTAMP);
        preparedStatementCreatorFactory.setReturnGeneratedKeys(true);
        PreparedStatementCreator psc = preparedStatementCreatorFactory.newPreparedStatementCreator(Arrays.asList(sushi.getName(), new Timestamp(sushi.getCreatedAt().getTime())));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
