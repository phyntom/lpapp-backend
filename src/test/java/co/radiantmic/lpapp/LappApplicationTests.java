package co.radiantmic.lpapp;

import co.radiantmic.lpapp.web.BankController;
import co.radiantmic.lpapp.web.UserController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LappApplicationTests {

	@Autowired
	private UserController userController;

	@Autowired
	private BankController bankController;

	@Test
	public void contextLoads() {
		assertThat(bankController).isNotNull();
		assertThat(userController).isNotNull();
	}

}
