package ch.heigvd.amt.projectone.services.dao;

import ch.heigvd.amt.projectone.exceptions.DuplicateKeyException;
import ch.heigvd.amt.projectone.exceptions.KeyNotFoundException;
import ch.heigvd.amt.projectone.model.Order;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Stateless
public class OrdersManager implements OrdersManagerLocal {

    @Resource(lookup = "jdbc/chillout")
    DataSource dataSource;

    /**
     * Checrhe tous les Order
     * @return Liste d'Order
     */
    public List<Order> getAllOrders(){

        List<Order> orders = new ArrayList<>();

        try{
            Connection connection = dataSource.getConnection();

            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM `Order`");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int idClient = rs.getInt("idClient");
                int idOrderItem = rs.getInt("idOrderItem");
                orders.add(new Order(id, idClient, idOrderItem));
            }

            connection.close();

        }
        catch (SQLException ex){
            Logger.getLogger(OrdersManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;

    }

    /**
     * Cherche les Order faite par un Client via son id
     * @param idClient
     * @return List<Order>
     */
    public List<Order> findOrdersByClientId(int idClient) {
        List<Order> orders = new ArrayList<>();
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `Order` WHERE idClient = ?");
            preparedStatement.setInt(1, idClient);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                int id = result.getInt("id");
                int idClientDB = result.getInt("idClient");
                int idOrderItem = result.getInt("idOrderItem");

                orders.add(new Order(id,idClientDB,idOrderItem));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return orders;

    }

    /**
     * Cree une Order
     * @param entity a creer
     * @return Order
     * @throws DuplicateKeyException
     */
    @Override
    public Order create(Order entity) throws DuplicateKeyException {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();

            PreparedStatement statement = connection.prepareStatement("INSERT INTO `Order` (idClient, idOrderItem) VALUES(?, ?);", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getIdClient());
            statement.setInt(2, entity.getIdOrderItem());

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return entity;

    }

    /**
     * Cherche une Order via son id
     * @param id de l'Order
     * @return Order
     * @throws KeyNotFoundException
     */
    @Override
    public Order findById(int id) throws KeyNotFoundException {
        Order order = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `Order` WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                int idDB = result.getInt("id");
                int idClient = result.getInt("idClient");
                int idOrderItem = result.getInt("idOrderItem");

                order = new Order(idDB,idClient,idOrderItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return order;

    }

    /**
     * Met a jour une Order
     * @param entity Order
     * @throws KeyNotFoundException
     */
    @Override
    public void update(Order entity) throws KeyNotFoundException {

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Order SET idOrderItem = ? WHERE id = ?");
            preparedStatement.setInt(1, entity.getIdOrderItem());
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }

    }

    /**
     * Supprime une Order via son ID
     * @param id de l'Order
     * @throws KeyNotFoundException
     */
    @Override
    public void deleteById(int id) throws KeyNotFoundException {

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(" DELETE ORDER WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }

    }

}
