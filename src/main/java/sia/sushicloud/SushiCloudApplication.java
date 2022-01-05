package sia.sushicloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SushiCloudApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SushiCloudApplication.class, args);
	}

}
