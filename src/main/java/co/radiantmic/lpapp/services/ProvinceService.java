package co.radiantmic.lpapp.services;

import co.radiantmic.lpapp.domain.Province;
import co.radiantmic.lpapp.repositories.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    public List<Province> findAllProvinces(){
         return provinceRepository.findAll();
    }
}
