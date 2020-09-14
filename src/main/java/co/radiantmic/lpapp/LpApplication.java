package co.radiantmic.lpapp;

import co.radiantmic.lpapp.domain.Bank;
import co.radiantmic.lpapp.domain.Role;
import co.radiantmic.lpapp.domain.User;
import co.radiantmic.lpapp.repositories.BankRepository;
import co.radiantmic.lpapp.repositories.RoleRepository;
import co.radiantmic.lpapp.repositories.UserRepository;
import co.radiantmic.lpapp.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.*;

@SpringBootApplication
public class LpApplication {

    @Autowired
    BankRepository bankRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @PostConstruct
    void init() {

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public LpApplication() {

    }

    public static void main(String[] args) {
        SpringApplication.run(LpApplication.class, args);
    }


//    @Bean
//    CommandLineRunner runner() {
//        return args -> {
//            Bank bkBank =bankRepository.save(new Bank("BK", "MAIN",BigInteger.valueOf(10000000),0.01));
//            Bank equityBank = bankRepository.save(new Bank("EQUITY BANK", "BRANCH",BigInteger.valueOf(10000000),0.03));
//            Bank ecoBank = bankRepository.save(new Bank("ECOBANK", "BRANCH",BigInteger.valueOf(10000000),0.0));
//
//            Role userRole = roleRepository.save(new Role("USER"));
//            Role adminRole = roleRepository.save(new Role("ADMIN"));
//            Role superAdmin = roleRepository.save(new Role("SUPER-ADMIN"));
//            Role agent = roleRepository.save(new Role("AGENT"));
//
//            User user = new User("user", passwordEncoder().encode("user"), "phyntom@gmail.com");
//            User admin = new User("admin", passwordEncoder().encode("admin"), "phyntom@gmail.com");
//
//            user.getRoles().add(userRole);
//            user.setBank(bkBank);
//            admin.getRoles().add(adminRole);
//            admin.setBank(ecoBank);
//            userRepository.save(user);
//            userRepository.save(admin);
//        };
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
