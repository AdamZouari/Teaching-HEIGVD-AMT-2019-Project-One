package ch.heigvd.amt.projectone.services.dao;

import ch.heigvd.amt.projectone.model.Order;

import javax.ejb.Local;
import java.util.List;

@Local
public interface OrdersManagerLocal extends IDAO<Integer, Order>{

    List<Order> getAllOrders();
}