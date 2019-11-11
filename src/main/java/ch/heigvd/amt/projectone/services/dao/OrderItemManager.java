package ch.heigvd.amt.projectone.services.dao;


import ch.heigvd.amt.projectone.exceptions.DuplicateKeyException;
import ch.heigvd.amt.projectone.exceptions.KeyNotFoundException;
import ch.heigvd.amt.projectone.model.OrderItem;
import ch.heigvd.amt.projectone.model.Product;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;

@Stateless
public class OrderItemManager implements OrderItemManagerLocal {

    @Resource(lookup = "jdbc/chillout")
    DataSource dataSource;

    /**
     * Cree un OrderItem
     * @param entity base de la creation
     * @return OrderItem
     * @throws DuplicateKeyException
     */
    @Override
    public OrderItem create(OrderItem entity) throws DuplicateKeyException {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();

            PreparedStatement statement = connection.prepareStatement("INSERT INTO `OrderItem` (idProduct, quantity) VALUES(?, ?);", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getProductId());
            statement.setInt(2, entity.getQuantity());

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return entity;

    }

    /**
     * Cherche un OrderItem via son id
     * @param id de l'OrderItem
     * @return OrderItem
     * @throws KeyNotFoundException
     */
    @Override
    public OrderItem findById(int id) throws KeyNotFoundException {
        OrderItem orderItem  = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `OrderItem` WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                int idDB = result.getInt("id");
                int idProduct = result.getInt("idProduct");
                int quantity = result.getInt("quantity");

                orderItem = new OrderItem(idDB, idProduct, quantity);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return orderItem;
    }

    /**
     * Met a jour un OrderItem
     * @param entity OrderItem
     * @throws KeyNotFoundException
     */
    @Override
    public void update(OrderItem entity) throws KeyNotFoundException {

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE OrderItem SET idProduct = ?, quantity = ? WHERE id = ?");
            preparedStatement.setInt(1, entity.getProductId());
            preparedStatement.setInt(2, entity.getQuantity());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }

    }

    /**
     * Supprime un OrderItem via son ID
     * @param id de l'OrderItem
     * @throws KeyNotFoundException
     */
    @Override
    public void deleteById(int id) throws KeyNotFoundException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(" DELETE OrderItem WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * cherche le produit de l'OrderItem
     * @param orderItem OrderItem
     * @return product
     */
    @Override
    public Product getProductOfOrderItem(OrderItem orderItem) {
        Product product = null;

        int id = orderItem.getProductId();
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `Product` WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet =  preparedStatement.executeQuery();

            if (resultSet.next()) {
                int idDB = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("unitPrice");
                String desc = resultSet.getString("description");

                product = new Product(idDB, name, price, desc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        return product;
    }
}
