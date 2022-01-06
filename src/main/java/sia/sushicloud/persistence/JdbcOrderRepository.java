package sia.sushicloud.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import sia.sushicloud.model.Order;
import sia.sushicloud.model.Sushi;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository{

    private SimpleJdbcInsert orderInsert;
    private SimpleJdbcInsert orderSushiInsert;
    private ObjectMapper objectMapper;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbc){
        this.orderInsert = new SimpleJdbcInsert(jdbc).withTableName("Sushi_Order").usingGeneratedKeyColumns("id");
        this.orderSushiInsert = new SimpleJdbcInsert(jdbc).withTableName("Sushi_Order_Sushi");
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Order save(Order order) {
        order.setPlaceAt(new Date());
        Long orderId = saveOrderDetails(order);
        order.setId(orderId);
        List<Sushi> sushiList = order.getSushiList();
        for(Sushi sushi: sushiList){
            saveSushiToOrder(sushi, orderId);
        }
        return order;
    }

    private void saveSushiToOrder(Sushi sushi, Long orderId) {
        Map<String, Object> values = new HashMap<>();
        values.put("sushi_order", orderId);
        values.put("sushi", sushi.getId());
        orderSushiInsert.execute(values);
    }

    private Long saveOrderDetails(Order order) {
        @SuppressWarnings("unchecked")
        Map<String, Object> values = objectMapper.convertValue(order, Map.class);
        values.put("placeAt", order.getPlaceAt());
        Long orderId = orderInsert.executeAndReturnKey(values).longValue();
        return orderId;
    }
}
