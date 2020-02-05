package app;

import app.controllers.RentController;
import app.dao.DaoImpl;
import app.exceptions.AutoNotFoundException;
import app.exceptions.ClientAlreadyPresentException;
import app.exceptions.ClientNotFoundException;
import app.exceptions.NonCorrectOwnerException;
import app.models.Auto;
import app.models.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarRentUnitTests{

    private static Client CLIENT;
    private static String BIRTH_YEAR;
    private static String NAME;

    private static Auto AUTO;
    private static String MODEL;
    private static String YEAR;


    @Autowired
    private RentController controller;

    @Autowired
    private Service service;

    @Autowired
    private DaoImpl dao;


    @Before
    public void initialize(){
        NAME = "Maria";
        BIRTH_YEAR = "1997";

        MODEL = "Focus";
        YEAR = "2008";

        CLIENT = new Client(NAME, BIRTH_YEAR);
        AUTO = new Auto(MODEL, YEAR);
    }


    @Test
    public void testFindByName(){
        CLIENT = new Client("Henry", "1985");
        dao.createClient(CLIENT);
        Client clientNew = dao.findClientByName("Henry");
        assertTrue("True", CLIENT.equals(clientNew));
    }


    @Test(expected=AutoNotFoundException.class)
    public void testAddWrongCar(){
        controller.addClient("Mark", BIRTH_YEAR, "Prius", YEAR);
    }

    @Test(expected=AutoNotFoundException.class)
    public void testDelWrongCar(){
        controller.addClient("Steven", "1987", MODEL, YEAR);
        controller.deleteClient("Steven", "Prius");
    }


    @Test(expected=NonCorrectOwnerException.class)
    public void testDelFreeCar(){
        controller.addClient("Simon", "1975", "Corolla", "1998");
        controller.deleteClient("Simon", "Dodge");
    }

    @Test(expected=ClientNotFoundException.class)
    public void testUserNotFoundToDelete(){
        CLIENT = new Client("Ann", "1987");
        System.out.println(CLIENT);
        service.deleteClientIfPresent(CLIENT);
    }

    @Test(expected=ClientAlreadyPresentException.class)
    public void testAddPresentClient(){
        service.createClientIfAbsent(CLIENT);
        service.createClientIfAbsent(CLIENT);
    }
}