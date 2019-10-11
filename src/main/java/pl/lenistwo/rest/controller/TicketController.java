package pl.lenistwo.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/all-tickets", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public TicketsList getAllTickets() {
        ArrayList<Ticket> tickets = new ArrayList<>();
        ticketRepository.findAll().forEach(tickets::add);
        return new TicketsList("All tickets", tickets);
    }

    @GetMapping(value = "/ticket-id", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Ticket> getTicketById(@RequestParam(value = "id") Long id) {
        Ticket ticket = Optional.ofNullable(ticketRepository.getById(id)).
                orElseThrow(TicketNotFoundException::new);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @GetMapping(value = "/player-tickets", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TicketsList> getAllPlayerTickets(@RequestParam(value = "username") String userName) {
        ArrayList<Ticket> tickets = new ArrayList<>();
        Iterable<Ticket> ticketIterable = Optional.ofNullable(ticketRepository.getAllByPlayerName(userName)).
                orElseThrow(TicketNotFoundException::new);
        ticketIterable.forEach(tickets::add);
        return new ResponseEntity<>(new TicketsList("All player tickets", tickets), HttpStatus.OK);
    }

    @GetMapping(value = "/delete-ticket", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> deleteTicketById(@RequestParam("id") Long id) {
        Optional<Ticket> ticketOptional = Optional.ofNullable(ticketRepository.getById(id));
        ticketRepository.delete(ticketOptional.
                orElseThrow(TicketNotFoundException::new));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/create-ticket", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createNewTicket(@RequestBody Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/update-ticket", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateTicket(@RequestBody Ticket ticket) {
        ticketRepository.save(ticket);
    }

}
