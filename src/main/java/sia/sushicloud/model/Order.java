package sia.sushicloud.model;

import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Sushi_Order")
public class Order implements Serializable {

//    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date placeAt;

    @NotBlank(message = "You need to provide name and surname")
    private String name;

    @NotBlank(message = "You need to provide street name")
    private String street;

    @NotBlank(message = "You need to provide building or apartment numbers")
    private String streetNr;

    @NotBlank(message = "You need to provide city name")
    private String city;

    @NotBlank(message = "You need to provide zip code")
    private String zip;

    @CreditCardNumber(message = "This is not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Date must be given in format MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV code")
    private String ccCvv;

    @ManyToMany(targetEntity = Sushi.class)
    @JoinTable(
            name = "SUSHI_ORDER_SUSHI",
            joinColumns = { @JoinColumn(name = "sushi_order_id", referencedColumnName = "id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "sushi_id", referencedColumnName = "id", nullable = false) }
    )
    private List<Sushi> sushi;

    public void addSushi(Sushi sushi){
        List<Sushi> sushiList = this.getSushi();
        if(sushiList == null){
            sushiList = new ArrayList<>();
        }
        sushiList.add(sushi);
        this.setSushi(sushiList);
    }

    @PrePersist
    void placedAt(){
        this.placeAt = new Date();
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
