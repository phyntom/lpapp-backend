package co.radiantmic.lpapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAuthentication() throws Exception {
        // Testing authentication with correct credentials
        this.mockMvc.perform(post("http://localhost:3010/api/token/generate").contentType("application/json")
                .content("{\"username\": \"phyntom\",\"password\": \"Mespeloua123\"}"))
                .andDo(print()).andExpect(status().isOk());

        // Testing authentication with wrong credentials
        this.mockMvc.perform(post("http://localhost:3010/api/token/generate").contentType("application/json")
                .content("{\"username\": \"admin\",\"password\": \"wrong\"}"))
                .andDo(print()).andExpect(status().is4xxClientError());
    }
}
