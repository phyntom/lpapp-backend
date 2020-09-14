package co.radiantmic.lpapp;

import co.radiantmic.lpapp.domain.Bank;
import co.radiantmic.lpapp.repositories.BankRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BankRepositoryTest {

    @Autowired
    BankRepository bankRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void saveBank() {
        Bank testBank = new Bank("EQUITY BANK","BRANCH",BigInteger.valueOf(10000000),0.01);
        testEntityManager.persistAndFlush(testBank);
        assertThat(testBank.getBankId()).isNotNull();
    }

//    @Test
//    public void deleteBank(){
//        Bank bankOne = testEntityManager.persistAndFlush(new Bank("KCB BANK","BRANCH", BigInteger.valueOf(1000000),0.01));
//        Bank bankTwo =testEntityManager.persistAndFlush(new Bank("URWEGO BANK", "BRANCH",BigInteger.valueOf(500000),0.01));
//        bankRepository.deleteAll();
//        assertThat(bankRepository.findAll()).isEmpty();
//    }

    @Test
    public void findBank(){
        testEntityManager.persistAndFlush(new Bank("KCB BANK","BRANCH", BigInteger.valueOf(1000000),0.01));
        testEntityManager.persistAndFlush(new Bank("URWEGO BANK", "BRANCH",BigInteger.valueOf(500000),0.01));
        assertThat(bankRepository.getBankByBankName("KCB BANK")).isNotNull();
        assertThat(bankRepository.getBankByBankName("URWEGO BANK")).isNotNull();
    }

}
