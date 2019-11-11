package ch.heigvd.amt.projectone.services.dao;

import ch.heigvd.amt.projectone.model.OrderItem;
import ch.heigvd.amt.projectone.model.Product;

public interface OrderItemManagerLocal extends IDAO<OrderItem> {

    Product getProductOfOrderItem(OrderItem orderItem);
}
