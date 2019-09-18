package pl.lenistwo.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.lenistwo.rest.entity.Ticket;
import pl.lenistwo.rest.exceptions.TicketNotFoundException;
import pl.lenistwo.rest.repositories.TicketRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketRepository repository;

    @Test
    public void findAll() throws Exception {
        // given
        Ticket first = new Ticket("Lenistwo", "that wont work", "help plox");
        Ticket second = new Ticket("Kikrun", "dziala", "help ");
        Ticket third = new Ticket("tremek", "NIe dziala", "help plox");
        Iterable<Ticket> tickets = Arrays.asList(first, second, third);

        given(repository.findAll()).willReturn(tickets);

        // when + then
        this.mockMvc.perform(get("/ticket-api/all-tickets")).
                andExpect(status().isOk()).
                andExpect(content().json("[{\"id\":null,\"playerName\":\"Lenistwo\",\"subject\":\"that wont work\",\"content\":\"help plox\",\"requestDate\":\"2019-09-18\"},{\"id\":null,\"playerName\":\"Kikrun\",\"subject\":\"dziala\",\"content\":\"help \",\"requestDate\":\"2019-09-18\"},{\"id\":null,\"playerName\":\"tremek\",\"subject\":\"NIe dziala\",\"content\":\"help plox\",\"requestDate\":\"2019-09-18\"}]"));
    }

    @Test
    public void findTicketWithPlayerName() throws Exception {
        // given
        Ticket lenistwoTicket = new Ticket("Lenistwo", "that wont work", "help plox");
        List<Ticket> tickets = Collections.singletonList(lenistwoTicket);

        given(repository.getAllByPlayerName(lenistwoTicket.getPlayerName())).willReturn(tickets);

        // when + then
        this.mockMvc.perform(get("/ticket-api/player-tickets?userName=" + lenistwoTicket.getPlayerName())).
                andExpect(status().isOk()).andExpect(content().json("[{\"id\":null,\"playerName\":\"Lenistwo\",\"subject\":\"that wont work\",\"content\":\"help plox\",\"requestDate\":\"2019-09-18\"}]"));
    }

    @Test
    public void getTicketByIdOutOfBound() throws Exception {
        // given
        Long id = 99L;

        given(repository.getById(id)).willReturn(null);

        // when + then
        this.mockMvc.perform(get("/ticket-api/ticket-id?id=" + id)).andExpect(status().isOk());
    }

    @Test
    public void shouldHandleTicketNotFoundException() throws Exception {
        // given
        Long id = 99L;

        given(repository.getById(id)).willReturn(null);

        // when + then
        this.mockMvc.perform(get("/ticket-api/delete-ticket?id=" + id)).andExpect(status().isOk());
    }
}