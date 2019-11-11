package ch.heigvd.amt.projectone.services.dao;

import ch.heigvd.amt.projectone.model.Client;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ClientsManagerLocal extends IDAO<Client>{

    int getIdByUsername(String username);
    Client getClientById(int id);
    List<Client> getAllClients();
}
