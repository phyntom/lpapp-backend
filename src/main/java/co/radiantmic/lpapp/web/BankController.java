package co.radiantmic.lpapp.web;

import co.radiantmic.lpapp.domain.Bank;
import co.radiantmic.lpapp.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 36000000)
@RestController
@RequestMapping("/api/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping("")
    public ResponseEntity<?> getAllBanks() {

        List<Bank> listBanks = bankService.findAllBanks();
        return new ResponseEntity<>(listBanks, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createNewBank(@Valid @RequestBody Bank bank, BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map>(errorMap, HttpStatus.BAD_REQUEST);
        }
        Bank createdBank = bankService.createBank(bank);
        return new ResponseEntity<>(createdBank, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<?> editBank(@Valid @RequestBody Bank bank, BindingResult result) {

        try {
            if (result.hasErrors()) {
                Map<String, String> errorMap = new HashMap<>();
                for (FieldError error : result.getFieldErrors()) {
                    errorMap.put(error.getField(), error.getDefaultMessage());
                }
                return new ResponseEntity<Map>(errorMap, HttpStatus.BAD_REQUEST);
            }
            Bank createdBank = bankService.editBank(bank);
            return new ResponseEntity<>(createdBank, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{bankId}")
    public ResponseEntity<?> findBankById(@PathVariable Integer bankId) {

        Optional<Bank> optionalBank = bankService.findBankById(bankId);
        if (!optionalBank.isPresent()) {
            new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Bank bank = optionalBank.get();
        return new ResponseEntity<>(bank, HttpStatus.OK);
    }
}
