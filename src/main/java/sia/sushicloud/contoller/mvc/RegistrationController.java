package sia.sushicloud.contoller.mvc;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.sushicloud.model.RegistrationForm;
import sia.sushicloud.persistence.JpaUserRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private JpaUserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(JpaUserRepository userRepository, PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String registerForm(){
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form){
        userRepository.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
