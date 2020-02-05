package app;

import app.dao.DaoImpl;
import app.exceptions.ClientAlreadyPresentException;
import app.exceptions.ClientNotFoundException;
import app.models.Auto;
import app.models.Client;
import io.swagger.annotations.ApiOperation;

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

    @ApiOperation(value = "Search for a car in the database by model name.")
    public Auto findAutoByModel(String carModel) {
        return userDao.findAutoByModel(carModel);
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

    public void createAuto(Auto auto) { userDao.createAuto(auto); }

    public void updateAuto(Auto auto) { userDao.updateAuto(auto); }

    public void deleteAuto(Auto auto) { userDao.deleteAuto(auto); }

    public List<Auto> getAllAutos() { return userDao.getAllAutos(); }

}
