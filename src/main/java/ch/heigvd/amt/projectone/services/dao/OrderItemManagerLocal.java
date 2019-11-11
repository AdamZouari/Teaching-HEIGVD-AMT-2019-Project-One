package ch.heigvd.amt.projectone.services.dao;

import ch.heigvd.amt.projectone.model.OrderItem;
import ch.heigvd.amt.projectone.model.Product;

import javax.ejb.Local;

@Local
public interface OrderItemManagerLocal extends IDAO<OrderItem> {

    Product getProductOfOrderItem(OrderItem orderItem);
}
