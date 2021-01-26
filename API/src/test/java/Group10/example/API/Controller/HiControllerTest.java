package Group10.example.API.Controller;

import org.junit.jupiter.api.Test;
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

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = HiController.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
class HiControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void index() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("mobile/test/");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertEquals("Hi",mvcResult.getResponse().getContentAsString());
    }
}