package sia.sushicloud.model;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class Order {

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
//    private String paymentMethod;
    private PaymentStatus paymentStatus;
    @CreditCardNumber(message = "This is not a valid credit card number")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Date must be given in format MM/YY")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV code")
    private String ccCvv;
}
