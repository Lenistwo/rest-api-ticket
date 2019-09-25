package pl.lenistwo.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.lenistwo.rest.entity.TicketsList;
import pl.lenistwo.rest.entity.Ticket;
import pl.lenistwo.rest.exceptions.TicketNotFoundException;
import pl.lenistwo.rest.repositories.TicketRepository;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/ticket-api")
public class TicketController {

    private TicketRepository ticketRepository;

    @Autowired
    public TicketController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @GetMapping(value = "/all-tickets", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public TicketsList getAllTickets() {
        ArrayList<Ticket> tickets = new ArrayList<>();
        ticketRepository.findAll().forEach(tickets::add);
        return new TicketsList("All tickets", tickets);
    }

    @GetMapping(value = "/ticket-id", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Ticket getTicketById(@RequestParam(value = "id") Long id) {
        return ticketRepository.getById(id);
    }

    @GetMapping(value = "/player-tickets", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public TicketsList getAllPlayerTickets(@RequestParam(value = "userName") String userName) {
        ArrayList<Ticket> tickets = new ArrayList<>();
        ticketRepository.getAllByPlayerName(userName).forEach(tickets::add);
        return new TicketsList("All player tickets", tickets);
    }

    @GetMapping(value = "/delete-ticket", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public TicketsList deleteTicketById(@RequestParam("id") Long id) {
        Optional<Ticket> ticketOptional = Optional.ofNullable(ticketRepository.getById(id));
        ticketRepository.delete(ticketOptional.orElseThrow(TicketNotFoundException::new));
        ArrayList<Ticket> tickets = new ArrayList<>();
        ticketRepository.findAll().forEach(tickets::add);
        return new TicketsList("All tickets", tickets);
    }


}
