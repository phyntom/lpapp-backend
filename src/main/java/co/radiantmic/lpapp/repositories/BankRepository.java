package co.radiantmic.lpapp.repositories;

import co.radiantmic.lpapp.domain.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank,Integer> {
    Optional<Bank> getBankByBankName(String bankName);
    Optional<Bank> findBankByBankName(String bankName);
}
