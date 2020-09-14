package co.radiantmic.lpapp.web;

import co.radiantmic.lpapp.domain.District;
import co.radiantmic.lpapp.services.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*",maxAge = 36000000)
@RestController
@RequestMapping("/api/districts")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @GetMapping("")
    public ResponseEntity<?> getAllDistricts(){
        List<District> listDistricts = districtService.findAllDistricts();
        return new ResponseEntity<>(listDistricts,HttpStatus.OK);
    }

}
