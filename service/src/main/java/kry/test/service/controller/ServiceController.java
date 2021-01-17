package kry.test.service.controller;

import kry.test.service.model.Service;
import kry.test.service.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/")
public class ServiceController {
    private final static Logger LOGGER = Logger.getLogger(ServiceController.class.getName());

    @Autowired
    private ServiceService serviceService;

    @RequestMapping(value = "/services", method = RequestMethod.GET)
    public List<Service> list() {
        return serviceService.listAll();
    }

    @RequestMapping(value = "/status", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Service> pollServices() {
        serviceService.listAll().forEach(service -> {
            Service updatedService = service;
            updatedService.setLastUpdated(new Timestamp(new Date().getTime()));

            try {
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(service.getUrl())).build();
                HttpClient client = HttpClient.newHttpClient();

                try {
                    HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    updatedService.setStatus(String.valueOf(response.statusCode()).startsWith("2") ? "OK" : "FAIL");
                    serviceService.saveService(updatedService);
                } catch (IOException | InterruptedException ioException) {
                    LOGGER.severe("Big oopsie.");
                }
            } catch (IllegalArgumentException ie) {
                updatedService.setStatus("FAIL");
                serviceService.saveService(updatedService);
            }
        });
        return serviceService.listAll();
    }

    @RequestMapping(value = "/services", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Service service) {
        serviceService.saveService(service);
    }

}