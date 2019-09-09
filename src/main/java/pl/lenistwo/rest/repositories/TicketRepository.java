package pl.lenistwo.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lenistwo.rest.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
