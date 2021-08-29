package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.Ticket;
import ru.netology.repository.Repository;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    private Repository repository = new Repository();
    private Manager manager = new Manager(repository);
    Ticket t1 = new Ticket(1, 4000, "DME", "LED", 85);      // Домодедово - Санк-Петербург
    Ticket t2 = new Ticket(2, 1000, "DME", "LED", 90);      // Домодедово - Санк-Петербург
    Ticket t3 = new Ticket(3, 9000, "DME", "AYT", 180);     // Домодедово - Анталия
    Ticket t4 = new Ticket(4, 500, "DME", "LED", 89);       // Домодедово - Санк-Петербург
    Ticket t5 = new Ticket(5, 8000, "DME", "LED", 95);      // Домодедово - Санк-Петербург
    Ticket t6 = new Ticket(6, 20_000, "NQZ", "DME", 195);   // Нур-Султан - Домодедово
    Ticket t7 = new Ticket(7, 21_000, "DME", "NQZ", 195);   // Домодедово- Нур-Султан
    Ticket t8 = new Ticket(8, 13_000, "LHR", "СDG", 95);    // Лондон - Париж
    Ticket t9 = new Ticket(9, 15_000, "KZN", "OVB", 190);   // Казань - Новосибирск
    Ticket t10 = new Ticket(10, 3000, "LED", "DME", 90);    // Санкт-Петербурс - Домодедово
    Ticket t11 = new Ticket(11, 45_000, "DEL", "DXB", 300); // Дели - Дубаи
    Ticket t12 = new Ticket(12, 1500, "LED", "DME", 90);    // Санкт -Петербург - Домодедово

    @BeforeEach
    public void setUp() {
        manager.add(t1);
        manager.add(t2);
        manager.add(t3);
        manager.add(t4);
        manager.add(t5);
        manager.add(t6);
        manager.add(t7);
        manager.add(t8);
        manager.add(t9);
        manager.add(t10);
        manager.add(t11);
        manager.add(t12);
    }

    // ТЕСТИРОВАНИЕ С СОРТИРОВКОЙ ПО ВРЕМЕНИ ПОЛЕТА

    //1.1. имеется 0 билетов с указанными аоропортами отправления и прибытия
    @Test
    void shouldsearchByFromAndToIfNoTicketsWithFromAndToAndTimeComparator() {
        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchByFromAndTo("XXX", "PPP", new TicketByTimeTravelAscComparator());
        assertArrayEquals(expected, actual);
    }

    //1.2. имеется только 1 билет с указанными аоропортами отправления и прибытия
    @Test
    void shouldsearchByFromAndToIfOnlyOneTicketWithFromAndToExistAndTimeComparator() {
        Ticket[] expected = new Ticket[]{t8};
        Ticket[] actual = manager.searchByFromAndTo("LHR", "СDG", new TicketByTimeTravelAscComparator());
        assertArrayEquals(expected, actual);
    }

    //1.3. имеется много билетов с указанными аоропортами отправления и прибытия (сортировка по цене от наиболее дешевого к более дорогому)
    @Test
    void shouldsearchByFromAndToIfManyTicketsWithFromAndToExistAndTimeComparator() {
        Ticket[] expected = new Ticket[]{t1, t4, t2, t5};
        Ticket[] actual = manager.searchByFromAndTo("DME", "LED", new TicketByTimeTravelAscComparator());
        assertArrayEquals(expected, actual);
    }

    //1.4. нет билетов, если перепутать аэропорт отправления и прибытия
    @Test
    void shouldsearchByFromAndToIfFromAndToMixUpAndTimeComparator() {
        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchByFromAndTo("СDG", "LHR", new TicketByTimeTravelAscComparator());
        assertArrayEquals(expected, actual);
    }

    //1.5. убеждаемся, что поиск билетов происходит только при условии истинности ДВУХ условий (совпадает только From)
    @Test
    void shouldsearchByFromAndToIfOnlyFirstConditionIsMetAndTimeComparator() {
        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchByFromAndTo("KZN", "XXX", new TicketByTimeTravelAscComparator());
        assertArrayEquals(expected, actual);
    }

    //1.6. убеждаемся, что поиск билетов происходит только при условии истинности ДВУХ условий (совпадает только To)
    @Test
    void shouldsearchByFromAndToIfOnlySecondConditionIsMetAndTimeComparator() {
        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchByFromAndTo("XXX", "KZN", new TicketByTimeTravelAscComparator());
        assertArrayEquals(expected, actual);
    }


    // 2. ТЕСТИРОВАНИЕ С СОРТИРОВКОЙ ПО ЦЕНЕ

    //2.1. имеется 0 билетов с указанными аоропортами отправления и прибытия
    @Test
    void shouldsearchByFromAndToIfNoTicketsWithFromAndToAndPriceComparator() {
        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchByFromAndTo("XXX", "PPP", new TicketByPriceAscComparator());
        assertArrayEquals(expected, actual);
    }

    //2.2. имеется только 1 билет с указанными аоропортами отправления и прибытия
    @Test
    void shouldsearchByFromAndToIfOnlyOneTicketWithFromAndToExistAndPriceComparator() {
        Ticket[] expected = new Ticket[]{t8};
        Ticket[] actual = manager.searchByFromAndTo("LHR", "СDG", new TicketByPriceAscComparator());
        assertArrayEquals(expected, actual);
    }

    //2.3. имеется много билетов с указанными аоропортами отправления и прибытия (сортировка по цене от наиболее дешевого к более дорогому)
    @Test
    void shouldsearchByFromAndToIfManyTicketsWithFromAndToExistAndPriceComparator() {
        Ticket[] expected = new Ticket[]{t4, t2, t1, t5};
        Ticket[] actual = manager.searchByFromAndTo("DME", "LED", new TicketByPriceAscComparator());
        assertArrayEquals(expected, actual);
    }

    //2.4. нет билетов, если перепутать аэропорт отправления и прибытия
    @Test
    void shouldsearchByFromAndToIfFromAndToMixUpAndPriceComparator() {
        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchByFromAndTo("СDG", "LHR", new TicketByPriceAscComparator());
        assertArrayEquals(expected, actual);
    }

    //2.5. убеждаемся, что поиск билетов происходит только при условии истинности ДВУХ условий (совпадает только From)
    @Test
    void shouldsearchByFromAndToIfOnlyFirstConditionIsMetAndPriceComparator() {
        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchByFromAndTo("KZN", "XXX", new TicketByPriceAscComparator());
        assertArrayEquals(expected, actual);
    }

    //2.6. убеждаемся, что поиск билетов происходит только при условии истинности ДВУХ условий (совпадает только To)
    @Test
    void shouldsearchByFromAndToIfOnlySecondConditionIsMetAndPriceComparator() {
        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchByFromAndTo("XXX", "KZN", new TicketByPriceAscComparator());
        assertArrayEquals(expected, actual);
    }
}