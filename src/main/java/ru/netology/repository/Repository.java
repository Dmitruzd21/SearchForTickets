package ru.netology.repository;

import ru.netology.data.Ticket;

public class Repository {
    Ticket[] tickets = new Ticket[0];

    // Добавить билет
    public void save(Ticket ticket) {
        Ticket[] tmp = new Ticket[tickets.length + 1];
        for (int i = 0; i < tickets.length; i++) {
            tmp[i] = tickets[i];
        }
        tmp[tmp.length - 1] = ticket;
        tickets = tmp;
    }

    // Удалить билет
    public void removeById(int id) {
        Ticket[] tmp2 = new Ticket[tickets.length - 1];
        int index = 0;
        for (Ticket ticket : tickets) {
            if (ticket.getId() != id) {
                tmp2[index] = ticket;
                index++;
            }
        }
        tickets = tmp2;

    }

    // Получить список билетов
    public Ticket[] findAll() {
        return tickets;
    }

}
