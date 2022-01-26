package sia.sushicloud.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import sia.sushicloud.service.SushiTypeConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Sushi extends RepresentationModel<Sushi> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createdAt;

    @NotNull
    @Size(min=5, message = "Name must consist of at least 5 characters")
    private String name;

    @ManyToMany(targetEntity = Ingredient.class)
    @JoinTable(
            name = "SUSHI_INGREDIENT",
            joinColumns = { @JoinColumn(name = "sushi_id", referencedColumnName = "id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "ingredient_id", referencedColumnName = "id", nullable = false) }
    )
    @Size(min = 1, message = "You need to choose at least one ingredient")
    private List<Ingredient> ingredient;

    private SushiType type;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}
