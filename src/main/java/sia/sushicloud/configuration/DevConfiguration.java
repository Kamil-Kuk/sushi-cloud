package sia.sushicloud.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import sia.sushicloud.persistence.JpaSushiIngredientRepository;
import sia.sushicloud.persistence.JpaUserRepository;

@Profile("!prod")
@Configuration
public class DevConfiguration {

}
