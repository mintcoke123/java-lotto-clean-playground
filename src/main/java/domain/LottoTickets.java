package domain;

import java.util.List;

public class LottoTickets {
    private final List<Lotto> tickets;

    public LottoTickets(List<Lotto> tickets) {
        this.tickets = List.copyOf(tickets);
    }

    public List<Lotto> asList() {
        return tickets;
    }

    @Override
    public String toString() {
        return tickets.toString();
    }
}