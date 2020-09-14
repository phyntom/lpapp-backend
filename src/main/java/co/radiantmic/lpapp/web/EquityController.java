package co.radiantmic.lpapp.web;

import co.radiantmic.lpapp.domain.Equity;
import co.radiantmic.lpapp.services.EquityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/equities")
public class EquityController {

    @Autowired
    EquityService equityService;

    @GetMapping("")
    public ResponseEntity<?> getAllEquities(){
        List<Equity> equities = equityService.getAllEquities();
          return new ResponseEntity<>(equities, HttpStatus.OK);
    }
}
