package sia.sushicloud.contoller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sia.sushicloud.model.Order;
import sia.sushicloud.persistence.JpaOrderRepository;


@RestController
@RequestMapping(path = "/order", produces = {"application/json", "text/xml"})
@CrossOrigin(origins = "*")
public class OrderRestController {

    private JpaOrderRepository orderRepository;

    @Autowired
    EntityLinks entityLinks;

    @PutMapping("{orderId}")
    public Order updateOrder(@RequestBody Order order){
        return orderRepository.save(order);
    }

    @PatchMapping(value = "{orderId}", consumes = "application/json")
    public Order updateOrder(@PathVariable("orderId") Long id, @RequestBody Order patchOrder){
        Order order = orderRepository.findById(id).get();
        if(patchOrder.getName() != null){
            order.setName(patchOrder.getName());
        }
        if(patchOrder.getStreet() != null){
            order.setStreet(patchOrder.getStreet());
        }
        if(patchOrder.getCity() != null){
            order.setCity(patchOrder.getCity());
        }
        if(patchOrder.getStreetNr() != null){
            order.setStreetNr(patchOrder.getStreetNr());
        }
        if(patchOrder.getCcNumber() != null){
            order.setCcNumber(patchOrder.getCcNumber());
        }
        if(patchOrder.getCcExpiration() != null){
            order.setCcExpiration(patchOrder.getCcExpiration());
        }
        if(patchOrder.getCcCvv() != null){
            order.setCcCvv(patchOrder.getCcCvv());
        }
        return orderRepository.save(order);
    }

    @DeleteMapping("{orderId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long id){
        try{
            orderRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
    }
}
