package ch.heigvd.amt.projectone.web;

import ch.heigvd.amt.projectone.exceptions.KeyNotFoundException;
import ch.heigvd.amt.projectone.model.Order;
import ch.heigvd.amt.projectone.model.OrderItem;
import ch.heigvd.amt.projectone.model.Product;
import ch.heigvd.amt.projectone.services.dao.OrderItemManagerLocal;
import ch.heigvd.amt.projectone.services.dao.OrdersManagerLocal;


import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrdersServlet", urlPatterns = "/orders")
public class OrdersServlet extends HttpServlet {

    @EJB
    private OrdersManagerLocal ordersManagerLocal;

    @EJB
    private OrderItemManagerLocal orderItemManagerLocal;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = ordersManagerLocal.getAllOrders();

        ArrayList<OrderItem> orderItems = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();


        for (Order o : orders) {
            try {
                OrderItem oi = orderItemManagerLocal.findById(o.getIdOrderItem());
                orderItems.add(oi);
            } catch (KeyNotFoundException e) {
                e.printStackTrace();
            }
        }

        for (OrderItem orderItem : orderItems) {
            Product product = orderItemManagerLocal.getProductOfOrderItem(orderItem);
            products.add(product);
        }

        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("orders", orders);
        request.setAttribute("orderItems", orderItems);
        request.setAttribute("productsOfOrders", products);
        request.getRequestDispatcher("/WEB-INF/pages/orders.jsp").forward(request, response);
    }
}
