package co.radiantmic.lpapp.services;

import co.radiantmic.lpapp.domain.District;
import co.radiantmic.lpapp.exception.BadRequestException;
import co.radiantmic.lpapp.repositories.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService {
    
    @Autowired
    private DistrictRepository districtRepository;
    
    public List<District> findAllDistricts(){
        try {
            return districtRepository.findAll();
        }
        catch(Exception ex){
            ex.printStackTrace();
            throw new BadRequestException("Error | Bad request was sent");
        }
    }
}
