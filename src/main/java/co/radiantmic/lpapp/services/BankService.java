package co.radiantmic.lpapp.services;

import co.radiantmic.lpapp.domain.Bank;
import co.radiantmic.lpapp.exception.EntityAlreadyExistsException;
import co.radiantmic.lpapp.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService {

    @Autowired
    BankRepository bankRepository;
    /**
     * method to return all the banks
     * @return
     */
    public List<Bank> findAllBanks() {
        return bankRepository.findAll();
    }

    /**
     * method to create new bank
     * @param bank
     * @return
     */
    public Bank createBank(Bank bank) {
        Optional<Bank> optionalBank= bankRepository.findBankByBankName(bank.getBankName().toUpperCase());
        if(optionalBank.isPresent()){
            throw new EntityAlreadyExistsException("Bank with name "+bank.getBankName()+" already exists");
        }
        bank.setBankName(bank.getBankName().toUpperCase());
        return bankRepository.save(bank);
    }

    public Bank editBank(Bank bank) {
        return bankRepository.save(bank);
    }

    /**
     *
     * @param bankId
     * @return
     */
    public Optional<Bank> findBankById(Integer bankId) {
        return bankRepository.findById(bankId);
    }

}
