package app.controllers;

import app.Service;
import app.exceptions.NonCorrectOwnerException;
import app.models.Auto;
import app.models.Client;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@Api(value = "REST Controller - at startup, five cars are added to the database.")
public class RentController {

    private Service service;

    @Autowired
    public RentController(Service service){
        this.service=service;
        addCars(); // filling DB for tests
    }


    @PostMapping
    @ApiOperation(value = "Adding a new client to the DB. Arguments - the name and year of birth of the client, " +
            "model and year of manufacture of the car.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Client successfully added."),
            @ApiResponse(code = 404, message = "Сlient is already in the DB or the model is incorrect.")})
    public ResponseEntity<String> addClient(@RequestParam String name, @RequestParam String birthYear,
                            @RequestParam String carModel, @RequestParam String modYear){
        Client client = new Client(name, birthYear);
        service.createClientIfAbsent(client);

        Auto auto = service.findAutoByModel(carModel);
        auto.setClient(client);
        service.updateAuto(auto);

        return new ResponseEntity<>("Client successfully added!", HttpStatus.OK);
    }

    @DeleteMapping
    @ApiOperation(value = "Removing a client from the DB. Arguments - the client name and model of the car.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Client successfully removed."),
            @ApiResponse(code = 404, message = "Name or model is incorrect.")})
    public ResponseEntity<String> deleteClient(@RequestParam String name,
                                       @RequestParam String carModel){

        Auto auto = service.findAutoByModel(carModel);

        if (auto.getClient()==null || !auto.getClient().getName().equals(name)) {
            throw new NonCorrectOwnerException();
        } else {
            auto.setClient(null);
            service.updateAuto(auto);
            service.deleteClientIfPresent(service.findClient(name));
        }

        return new ResponseEntity<>("Client successfully deleted!",  HttpStatus.OK);
    }

    private void addCars() {
        if(service.getAllAutos().size()<5){
            service.createAuto(new Auto("Dodge", "1976"));
            service.createAuto(new Auto("Corolla", "1998"));
            service.createAuto(new Auto("Focus", "2008"));
            service.createAuto(new Auto("Bluebird", "1995"));
            service.createAuto(new Auto("Barracuda", "1970"));
        } else return;
    }
}