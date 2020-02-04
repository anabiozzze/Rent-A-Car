package app.controllers;

import app.Service;
import app.models.Auto;
import app.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class RentController {

    private Service service;

    @Autowired
    public RentController(Service service){
        this.service=service;
    }


    @PostMapping(value = "api/clients", produces = MediaType.APPLICATION_JSON_VALUE)
    public Client addClient(@RequestParam String name, @RequestParam String birthYear,
                            @RequestParam String carModel, @RequestParam String modYear){
        Client client = new Client(name, birthYear);
        Auto auto = new Auto(carModel, modYear);

        auto.setClient(client);
        client.setAuto(auto);

        service.createClientIfAbsent(client);
        return client;
    }

    @DeleteMapping(value = "api/clients")
    public ResponseEntity<String> deleteClient(@RequestParam String name,
                                       @RequestParam String carModel){

        service.findAutoByModel(carModel);
        service.deleteClientIfPresent(service.findClient(name));

        return new ResponseEntity<>(name + " successfully deleted!",  HttpStatus.OK);
    }

    //    @GetMapping
//    public String main(Map<String, Object> model) {
//        Iterable<Client> clients = service.findAllClients();
//        model.put("clients", clients);
//        return "main";
//    }
}