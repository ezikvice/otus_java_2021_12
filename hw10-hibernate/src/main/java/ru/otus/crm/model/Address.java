package ru.otus.crm.model;

public class Address {

    public Address() {
    }

    public Address(String street) {
        this.street = street;
    }

    private String street;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                '}';
    }
}
