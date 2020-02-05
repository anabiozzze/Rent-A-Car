package app.models;

import com.sun.deploy.security.ValidationState;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "autos")
@EqualsAndHashCode(of={"model", "year"})
@NoArgsConstructor
public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String model;
    private String year;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private Client client;


    public Auto(String model, String year) {
        this.model = model;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Client getClient () {
        return client;
    }

    public void setClient(Client user) {
        this.client = user;
    }

    @Override
    public String toString() {
        return model + ", " + year;
    }
}
