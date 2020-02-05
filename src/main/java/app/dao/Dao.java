package app.dao;

import app.models.Auto;
import app.models.Client;

import java.util.List;

public interface Dao {

    void createClient(Client client);

    void updateClient(Client client);

    List<Client> getAllClients();

    void delClient(Client client);

    Client findClientByName(String name);

    void createAuto(Auto auto);

    void updateAuto(Auto auto);

    Auto findAutoByModel(String carModel);
}
