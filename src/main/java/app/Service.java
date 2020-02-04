package app;

import app.dao.DaoImpl;
import app.exceptions.ClientAlreadyPresentException;
import app.exceptions.ClientNotFoundException;
import app.models.Auto;
import app.models.Client;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    DaoImpl userDao = new DaoImpl();

    public Service() {}

    public void createClientIfAbsent(Client client) {
        List<Client> clients = userDao.getAllClients();

        for (Client c : clients) {
            if (c.equals(client)) {
                throw new ClientAlreadyPresentException();
            }
        }
        userDao.createClient(client);
    }

    public void deleteClientIfPresent(Client client) throws ClientNotFoundException {
        ArrayList<Client> clients = (ArrayList) userDao.getAllClients();
        for (Client c : clients) {
            if (c.equals(client)) {
                userDao.delClient(client);
                return;
            }
        }
        throw new ClientNotFoundException();
    }

    public void updateClient(Client client) {
        userDao.updateClient(client);
    }

    public Client findClient(String name) {
        return userDao.findClientByName(name);
    }

    public List<Client> findAllClients() {
        return userDao.getAllClients();
    }

    public Auto findAutoByModel(String carModel) {
        return userDao.findAutoByModel(carModel);
    }
}
