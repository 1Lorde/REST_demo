package ua.co.savchuk.REST_demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WizardNotFoundException extends ResponseStatusException {

    public WizardNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "Wizard with id="+id+" not exist");
    }

    public WizardNotFoundException(String name) {
        super(HttpStatus.NOT_FOUND, "Wizard with name, like" + name + ", not found");
    }
}
