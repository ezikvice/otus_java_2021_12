package ru.otus.crm.model;

public class Phone {
    public Phone() {
    }

    public Phone(String number) {
        this.number = number;
    }

    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "number='" + number + '\'' +
                '}';
    }
}
