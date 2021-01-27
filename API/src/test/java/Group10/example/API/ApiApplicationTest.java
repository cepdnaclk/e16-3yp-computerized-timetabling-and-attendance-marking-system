package Group10.example.API;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ApiApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class ApiApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void main() throws Exception{

            RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/mobile/test/");
            MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
            assertEquals("Hi",mvcResult.getResponse().getContentAsString());
            System.out.println(mvcResult.getResponse().getContentAsString());
    }
}