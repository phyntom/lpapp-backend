package co.radiantmic.lpapp.services;

import co.radiantmic.lpapp.domain.Bank;
import co.radiantmic.lpapp.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankService {

    @Autowired
    BankRepository bankRepository;

    public Iterable<Bank> findAllBank(){
        return bankRepository.findAll();
    }

    public Bank createBank(Bank bank){
       return bankRepository.save(bank);
    }

    public Optional<Bank> findBankById(Long bankId){
        return bankRepository.findById(bankId);
    }

}
