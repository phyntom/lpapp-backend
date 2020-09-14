package co.radiantmic.lpapp.services;

import co.radiantmic.lpapp.domain.Equity;
import co.radiantmic.lpapp.repositories.EquityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquityService {

    @Autowired
    EquityRepository equityRepository;

    public List<Equity> getAllEquities(){
        return equityRepository.findAll();
    }
}
