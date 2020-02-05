package app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RentControllerTest {

    private static final String NAME = "John";
    private static final String BIRTH_YEAR = "1976";
    private static final String MODEL = "Barracuda";
    private static final String MOD_YEAR = "1970";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addClientTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/clients")
                .param("name", NAME)
                .param("birthYear", BIRTH_YEAR)
                .param("carModel", MODEL)
                .param("modYear", MOD_YEAR)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().string("Client successfully added!"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteClientTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/clients")
                .param("name", NAME)
                .param("carModel", MODEL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().string("Client successfully deleted!"))
                .andExpect(status().isOk());
    }

}
