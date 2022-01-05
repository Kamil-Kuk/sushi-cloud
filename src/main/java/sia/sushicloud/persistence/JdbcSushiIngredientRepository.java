package sia.sushicloud.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sia.sushicloud.model.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcSushiIngredientRepository implements SushiIngredientRepository{

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcSushiIngredientRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private Ingredient mapRowToIngredient(ResultSet resultSet, int rowNum) throws SQLException {
        return new Ingredient(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                Ingredient.IngredientType.valueOf(resultSet.getString("ingredient_type")));
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbc.query("SELECT id, name, ingredient_type FROM Ingredient", this::mapRowToIngredient);
    }

    @Override
    public Ingredient findById(Long id) {
        return jdbc.queryForObject("SELECT id, name, ingredient_type FROM Ingredient WHERE id=?", this::mapRowToIngredient);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbc.update("INSERT INTO Ingredient (id, name, ingredient_type) VALUES (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getIngredientType().toString());
        return ingredient;
    }
}
