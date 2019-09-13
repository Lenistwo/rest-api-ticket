package pl.lenistwo.rest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.lenistwo.rest.controller.TicketController;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class RestApplicationTests {

    @Autowired
    private TicketController ticketController;

    @Test
    public void contextLoads() {
        Assert.assertNotNull(ticketController);
    }

}
