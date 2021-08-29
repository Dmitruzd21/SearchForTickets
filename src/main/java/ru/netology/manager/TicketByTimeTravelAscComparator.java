package ru.netology.manager;

import ru.netology.data.Ticket;

import java.util.Comparator;

public class TicketByTimeTravelAscComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket o1, Ticket o2) {
        return o1.getTravelTimeMin() - o2.getTravelTimeMin();
    }
}
