package pl.lenistwo.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.lenistwo.rest.entity.Ticket;
import pl.lenistwo.rest.repositories.TicketRepository;

@RestController
@RequestMapping("/ticket-api")
public class TicketController {

    private TicketRepository ticketRepository;

    @Autowired
    public TicketController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @GetMapping(value = "/all-tickets", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Iterable<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @GetMapping(value = "/ticket-id", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Ticket getTicketById(@RequestParam(value = "id") Long id) {
        return ticketRepository.getById(id);
    }

    @GetMapping(value = "/player-tickets", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Iterable<Ticket> getAllPlayerTickets(@RequestParam(value = "userName") String userName) {
        return ticketRepository.getAllByPlayerName(userName);
    }

    @GetMapping(value = "/delete-ticket", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Iterable<Ticket> deleteTicketById(@RequestParam("id") Long id) {
        ticketRepository.deleteById(id);
        return ticketRepository.findAll();
    }


}
