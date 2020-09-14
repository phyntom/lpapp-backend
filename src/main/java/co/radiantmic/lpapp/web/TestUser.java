package co.radiantmic.lpapp.web;

import co.radiantmic.lpapp.services.IdentificationService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

public class TestUser {
    public static void main(String args[]){
        System.out.println("User phyntom | password "+new BCryptPasswordEncoder().encode("Mespeloua123"));
        System.out.println("User graceryumugabe | password "+new BCryptPasswordEncoder().encode("graceryumugabe"));
//      System.out.println("User alice | password "+new BCryptPasswordEncoder().encode("Mespeloua123"));
        System.out.println("User francoisx | password "+new BCryptPasswordEncoder().encode("francoisx123"));
        System.out.println("User evode | password "+new BCryptPasswordEncoder().encode("evode123"));
        System.out.println("User david | password "+new BCryptPasswordEncoder().encode("davidmalone@123"));
        IdentificationService service = new IdentificationService();
        service.getCustomerIdentificationKyc("250789895757");
        int [] data = new int[4];
        data[0] = 5;
        data[1] = 4;
        data[2] = 3;
        data[3] = 1;

        Arrays.sort(data);

        System.out.println(Arrays.toString(data));
    }
}
