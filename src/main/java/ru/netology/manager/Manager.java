package ru.netology.manager;

import ru.netology.data.Ticket;
import ru.netology.repository.Repository;

public class Manager {
    private Repository repository;

    public Manager(Repository repository) {
        this.repository = repository;
    }

    // Добавить билет
    public void add(Ticket ticket) {
        repository.save(ticket);
    }

    // Удалить билет
    public void removeById(int id) {
        repository.removeById(id);
    }

    // Получить список билетов
    public void findAll() {
        repository.findAll();
    }


    //  В менеджере методов findAll должен претерпеть некоторые изменения: он должен принимать два параметра:
    //  from - аэропорта вылета
    // to - аэропорт прилёта
    // Соответственно, в результате поиска возвращается массив только с теми билетами, что соответствуют условиям поиска.
    // Кроме того, результаты должны быть отсортированы по цене (от меньшей к большей).

    public Ticket[] searchByFromAndTo(String from,String to) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repository.findAll()) {
            if (matches(ticket, from, to)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                //(из какого массива, с какого места из источника, куда копировать,с какого места всавлять вцелевой, количество элементов которые хотим скопировать)
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        return result;
    }

    public boolean matches(Ticket ticket, String search1,String search2) {
            if (ticket.getFrom().contains(search1) && ticket.getTo().contains(search2)) {
                return true;
            }
            return false;
        }

}
