package sia.sushicloud.contoller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import sia.sushicloud.model.Order;
import sia.sushicloud.model.PaymentStatus;
import sia.sushicloud.model.User;
import sia.sushicloud.persistence.JpaOrderRepository;
import sia.sushicloud.persistence.OrderRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private JpaOrderRepository orderRepository;

    @Autowired
    public OrderController(JpaOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(){
//        model.addAttribute("order",new Order());
//        List<String> paymentMethods = new ArrayList<>();
//        for(PaymentMethod paymentMethod: PaymentMethod.values()){
//            paymentMethods.add(paymentMethod.toString().toLowerCase().replaceAll("_", " "));
//        }
//        model.addAttribute("paymentMethods", paymentMethods);
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
        if(errors.hasErrors()){
            return "orderForm";
        }
        order.setUser(user);
        log.info(order.toString());
        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
