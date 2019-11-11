package ch.heigvd.amt.projectone.services.dao;

import ch.heigvd.amt.projectone.exceptions.DuplicateKeyException;
import ch.heigvd.amt.projectone.exceptions.KeyNotFoundException;

import ch.heigvd.amt.projectone.model.Product;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class ProductsManager implements ProductsManagerLocal {

    @Resource(lookup = "jdbc/chillout")
    private DataSource dataSource;

    public List<Product> getAllProducts(int currentPage, int recordsPerPage) {

        Connection connection = null;
        List<Product> products = new ArrayList<>();

        int start = currentPage * recordsPerPage - recordsPerPage;


        try {
            connection = dataSource.getConnection();
            System.out.println("Schema : " + connection.getSchema());
            System.out.println("Catalog : " + connection.getCatalog());

            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM `Product` LIMIT ?, ?");
            pstmt.setInt(1, start);
            pstmt.setInt(2, recordsPerPage);


            getProducts(products, pstmt);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return products;
    }

    public List<Product> getAllProducts() {

        Connection connection = null;
        List<Product> products = new ArrayList<>();



        try {
            connection = dataSource.getConnection();
            System.out.println("Schema : " + connection.getSchema());
            System.out.println("Catalog : " + connection.getCatalog());

            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM `Product`");

            getProducts(products, pstmt);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return products;
    }

    @Override
    public Product create(Product entity) throws DuplicateKeyException {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();

            PreparedStatement statement = connection.prepareStatement("INSERT INTO `Product` (name, unitPrice, description) VALUES(?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getName());
            statement.setDouble(2, entity.getUnitPrice());
            statement.setString(3, entity.getDescription());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return entity;
    }

    @Override
    public Product findById(int id) throws KeyNotFoundException {
        Product product = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `Product` WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                int idDB = result.getInt("id");
                String name = result.getString("name");
                Double unitPrice = result.getDouble("unitPrice");
                String description = result.getString("description");

                product = new Product(idDB, name, unitPrice, description);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return product;
    }

    public int getIdByName(String username) {

        int id = -1;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM `Product` WHERE name = ?");
            preparedStatement.setString(1, username);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                id = result.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return id;

    }

    @Override
    public void update(Product entity) throws KeyNotFoundException {


        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Product SET name = ?, unitPrice = ?, description = ? WHERE id = ?");
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setDouble(2, entity.getUnitPrice());
            preparedStatement.setString(3, entity.getDescription());
            preparedStatement.setInt(4, entity.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public void deleteById(int id) throws KeyNotFoundException {

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(" DELETE Product WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public int getNumberOfRows() {
        String sql = "SELECT COUNT(id) FROM `Product`";
        int numOfRows = 0;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement  = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                numOfRows = resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        return numOfRows;
    }

    private void getProducts(List<Product> products, PreparedStatement pstmt) throws SQLException {
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            double unitPrice = rs.getDouble("unitPrice");
            String description = rs.getString("description");
            products.add(new Product(id, name, unitPrice, description));
        }
    }
}
