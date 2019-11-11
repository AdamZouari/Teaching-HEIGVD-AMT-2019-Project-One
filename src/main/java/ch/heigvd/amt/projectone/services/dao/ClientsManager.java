package ch.heigvd.amt.projectone.services.dao;

import ch.heigvd.amt.projectone.exceptions.DuplicateKeyException;
import ch.heigvd.amt.projectone.exceptions.KeyNotFoundException;
import ch.heigvd.amt.projectone.model.Client;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ClientsManager implements ClientsManagerLocal {
    @Resource(lookup = "jdbc/chillout")
    private DataSource dataSource;


    /**
     * Creation d'un client
     * @param entity client entre par l'utilisateur
     * @return client cree
     * @throws DuplicateKeyException
     */
    @Override
    public Client create(Client entity) throws DuplicateKeyException {

        Connection connection = null;

        try {
            connection = dataSource.getConnection();

            PreparedStatement statement = connection.prepareStatement("INSERT INTO `Client` (name, username, password, isAdmin) VALUES(?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getUsername());
            statement.setString(3, entity.getPassword());
            statement.setBoolean(4, entity.isAdmin());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return entity;

    }

    /**
     * Recherche d'un utilisateur par son id
     * @param id de l'utilisateur
     * @return client recherche
     * @throws KeyNotFoundException
     */
    @Override
    public Client findById(int id) throws KeyNotFoundException {
        Client client = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `Client` WHERE id = ?");
            preparedStatement.setInt(1, id);

            client = getClient(preparedStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return client;

    }

    /**
     * Mise a jour de l'utilisateur
     * @param entity client a mettre a jour
     * @throws KeyNotFoundException
     */
    @Override
    public void update(Client entity) throws KeyNotFoundException {

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `Client` SET name=?, username=?, password=?, isAdmin=? WHERE id=?;");
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getUsername());
            preparedStatement.setString(3, entity.getPassword());
            preparedStatement.setBoolean(4, entity.isAdmin());
            preparedStatement.setInt(5, entity.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }

    }

    /**
     * Suppression d'un utilisateur avec son id
     * @param id de l'utilisateur a supprimer
     * @throws KeyNotFoundException
     */
    @Override
    public void deleteById(int id) throws KeyNotFoundException {

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(" DELETE Client WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }


    /**
     * Recherche d'un utilisateur avec son username
     * @param username de l'utilisateur recherche
     * @return client
     */
    public Client findClientByUsername(String username) {
        Client client = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `Client` WHERE username = ?");
            preparedStatement.setString(1, username);

            client = getClient(preparedStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return client;

    }

    /**
     * Recherche l'id d'un utilisateur avec son username
     * @param username de l'utilisateur
     * @return id
     */
    public int getIdByUsername(String username) {

        int id = -1;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM `Client` WHERE username = ?");
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

    /**
     *
     * @param id
     * @return
     */
    public Client getClientById(int id){

        Client client = null;
        Connection connection = null;


        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `Client` WHERE id = ?");
            preparedStatement.setInt(1, id);

            client = getClient(preparedStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return client;

    }

    /**
     * Liste tous les clients
     * @return Liste des clients
     */
    public List<Client> getAllClients(){
        Connection connection = null;
        List<Client> clients = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            System.out.println("Schema : " + connection.getSchema());
            System.out.println("Catalog : " + connection.getCatalog());

            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM `Client`");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                boolean isAdmin = rs.getBoolean("isAdmin");
                clients.add(new Client(id, name, username, password, isAdmin));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return clients;
    }


    /**
     * Execute la requete pour retourner un client
     * @param ps requete
     * @return client
     */
    private Client getClient(PreparedStatement ps){
        try {
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String userDB = resultSet.getString("username");
                String passDB = resultSet.getString("password");
                boolean isAdmin = resultSet.getBoolean("isAdmin");

                return new Client(id, name, userDB, passDB, isAdmin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
