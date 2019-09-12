package pl.lenistwo.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.lenistwo.rest.entity.Ticket;
import pl.lenistwo.rest.repositories.TicketRepository;

@ControllerAdvice
public class TicketControllerAdvice {

    private TicketRepository repository;

    @Autowired
    public TicketControllerAdvice(TicketRepository repository) {
        this.repository = repository;
    }

    @ResponseBody
    @ExceptionHandler({
            EmptyResultDataAccessException.class
    })
    public Iterable<Ticket> emptyResultDataAccessExceptionHandle(){
        return repository.findAll();
    }

}
