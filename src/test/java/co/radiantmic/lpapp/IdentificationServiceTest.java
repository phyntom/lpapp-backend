package co.radiantmic.lpapp;

import co.radiantmic.lpapp.domain.Identification;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@AutoConfigureWebTestClient
public class IdentificationServiceTest {

    @Autowired
    private WebTestClient webTestClient;

    @Before
    public void setUp() {
        webTestClient = webTestClient.mutate().responseTimeout(Duration.ofMillis(36000)).build();
    }

    @Test
    public void testIdentification() throws Exception {

//        EntityExchangeResult<Identification> result = webTestClient.get().uri("http://105.179.7.34:8080/microinsuranceservices/settings/indangamuntu/1198380045854089").header("radiant-access-code", "hasdfkjhasdfjkasdfhasdfjkasdfasdfjhkasdfjhkasd").accept(MediaType.APPLICATION_JSON_UTF8).exchange().expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8).expectStatus().isOk().expectBody(Identification.class).returnResult();
//        System.out.println(result);
    }

}
