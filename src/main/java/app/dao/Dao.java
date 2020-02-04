package app.dao;

import app.models.Auto;
import app.models.Client;

import java.util.List;

public interface Dao {

    void createClient(Client client);

    List<Client> getAllClients();

    void delClient(Client client);

    void updateClient(Client client);

    Client findClientByName(String name);

    Auto findAutoByModel(String carModel);
}
