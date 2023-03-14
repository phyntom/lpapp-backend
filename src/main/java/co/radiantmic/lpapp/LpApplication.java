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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
