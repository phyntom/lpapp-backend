package co.radiantmic.lpapp.web;

import co.radiantmic.lpapp.domain.Identification;
import co.radiantmic.lpapp.services.IdentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private IdentificationService identificationService;

    @GetMapping("/id/{nationalId}")
    public ResponseEntity<?> getCustomerIdentificationByNid(@PathVariable String nationalId) {
        Identification foundIdentification = identificationService.getCustomerIdentification(nationalId);
        if (foundIdentification == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } else {
            if (foundIdentification.getDateOfBirth() == null) {
                return new ResponseEntity<>(foundIdentification, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(foundIdentification, HttpStatus.OK);
            }
        }
    }

    @GetMapping("/search/{mobileNumber}")
    public ResponseEntity<?> getCustomerIdentificationByKyc(@PathVariable String mobileNumber) {
        Identification foundIdentification = identificationService.getCustomerIdentificationKyc(mobileNumber);
        if (foundIdentification == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } else {
            if (foundIdentification.getDateOfBirth() == null) {
                return new ResponseEntity<>(foundIdentification, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(foundIdentification, HttpStatus.OK);
            }
        }
    }

}
