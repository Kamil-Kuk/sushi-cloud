package sia.sushicloud.contoller.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import sia.sushicloud.model.User;
import sia.sushicloud.persistence.JpaOrderRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
@ConfigurationProperties(prefix = "sushi.order")
public class OrderController {

    private int pageSize = 20;
    private JpaOrderRepository orderRepository;

    @Autowired
    public OrderController(JpaOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @GetMapping("/current")
    public String orderForm(){
        return "orderForm";
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user, Model model){
        Pageable pageable = (Pageable) PageRequest.of(0, pageSize);
        model.addAttribute("orders", orderRepository.findAllByUserOrderByPlaceAtDesc(user, pageable));
        return "orderList";
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
