package pl.lenistwo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.lenistwo.rest.entity.Ticket;
import pl.lenistwo.rest.repositories.TicketRepository;

@Component
public class LineRunner implements CommandLineRunner {

    private final TicketRepository repository;

    @Autowired
    public LineRunner(TicketRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        repository.save(new Ticket("Lenistwo", "", "help plox"));
        repository.save(new Ticket("Kikrun", "dziala", "help "));
        repository.save(new Ticket("Tremek", "NIe dziala", "help plox"));
    }
}
