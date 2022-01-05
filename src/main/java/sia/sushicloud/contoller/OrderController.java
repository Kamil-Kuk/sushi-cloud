package sia.sushicloud.contoller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.sushicloud.model.Order;
import sia.sushicloud.model.PaymentStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/current")
    public String orderForm(Model model){
        model.addAttribute("order",new Order());
//        List<String> paymentMethods = new ArrayList<>();
//        for(PaymentMethod paymentMethod: PaymentMethod.values()){
//            paymentMethods.add(paymentMethod.toString().toLowerCase().replaceAll("_", " "));
//        }
//        model.addAttribute("paymentMethods", paymentMethods);
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors){
        if(errors.hasErrors()){
            return "orderForm";
        }
        order.setPaymentStatus(PaymentStatus.PENDING);
        log.info("New order was issued " + order);
        return "redirect:/";
    }
}
