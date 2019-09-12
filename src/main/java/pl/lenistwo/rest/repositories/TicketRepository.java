package pl.lenistwo.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.lenistwo.rest.entity.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {

    Iterable<Ticket> getAllByPlayerName(String playerName);

    Ticket getById(Long id);

}
