package ru.netology.data;

public class Ticket  {
    private int id;
    private int price;
    private String from;
    private String to;
    private int travelTimeMin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getTravelTimeMin() {
        return travelTimeMin;
    }

    public void setTravelTimeMin(int travelTimeMin) {
        this.travelTimeMin = travelTimeMin;
    }

    public Ticket(int id, int price, String from, String to, int travelTimeMin) {
        this.id = id;
        this.price = price;
        this.from = from;
        this.to = to;
        this.travelTimeMin = travelTimeMin;
    }

    public Ticket() {
    }
}
