package pl.lenistwo.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.lenistwo.rest.error.Error;
import pl.lenistwo.rest.exceptions.TicketNotFoundException;

@ControllerAdvice
public class TicketControllerAdvice {

    @ResponseBody
    @ExceptionHandler({
            TicketNotFoundException.class
    })
    public ResponseEntity<Error> ticketNotFoundExceptionHandle() {
        Error error = new Error("Ticket not Found");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
