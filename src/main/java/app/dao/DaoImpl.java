package app.dao;

import app.exceptions.AutoNotFoundException;
import app.exceptions.ClientNotFoundException;
import app.hibernate.HibernateSessionFactoryUtil;
import app.models.Auto;
import app.models.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DaoImpl implements Dao {
    public void createClient(Client client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction1 = session.beginTransaction();

        session.save(client);
        transaction1.commit();
        session.close();
    }

    public List<Client> getAllClients() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Client> clients = session.createQuery("From Client").list();
        session.close();

        return clients;
    }

    public void delClient(Client client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(client);
        transaction.commit();
        session.close();
    }

    public void updateClient(Client client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction3 = session.beginTransaction();

        session.update(client);
        transaction3.commit();
        session.close();
    }

    public Client findClientByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Client where name = :name");
        query.setParameter("name", name);

        Client client;
        try {
            client = (Client) query.list().get(0);
        } catch (NullPointerException | ClassCastException | IndexOutOfBoundsException e) {
            throw new ClientNotFoundException();
        }

        session.close();
        return client;
    }

    public Auto findAutoByModel(String carModel) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Query query = session.createQuery("from Auto where model = :carModel");
        query.setParameter("carModel", carModel);

        Auto auto;
        try {
            auto = (Auto) query.list().get(0);
        } catch (NullPointerException | ClassCastException | IndexOutOfBoundsException e) {
            throw new AutoNotFoundException();
        }

        session.close();
        return auto;
    }


}
