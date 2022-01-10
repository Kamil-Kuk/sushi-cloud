package sia.sushicloud.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Sushi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt;

    @NotNull
    @Size(min=5, message = "Name must consist of at least 5 characters")
    private String name;

    @ManyToMany(targetEntity = Ingredient.class)
    @Size(min = 1, message = "You need to choose at least one ingredient")
    private List<String> ingredients;

    private SushiType type;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}
