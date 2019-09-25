package pl.lenistwo.rest.entity;

import java.util.List;

public class TicketsList {

    private String description;
    private List<Ticket> tickets;

    public TicketsList() {
    }

    public TicketsList(String description, List<Ticket> tickets) {
        this.description = description;
        this.tickets = tickets;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
