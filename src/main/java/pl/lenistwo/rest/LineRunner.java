package pl.lenistwo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.lenistwo.rest.entity.Ticket;
import pl.lenistwo.rest.repositories.TicketRepository;

@Component
public class LineRunner implements CommandLineRunner {

    @Autowired
    private TicketRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.save(new Ticket("Lenistwo","NIe dziala","help plox"));
        repository.save(new Ticket("Kikrun","dziala","help "));
        repository.save(new Ticket("tremek","NIe dziala","help plox"));
    }
}
