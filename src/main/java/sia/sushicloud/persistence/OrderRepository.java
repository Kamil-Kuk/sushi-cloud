package sia.sushicloud.persistence;

import sia.sushicloud.model.Order;

public interface OrderRepository {
    Order save(Order order);
}
