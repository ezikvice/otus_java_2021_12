package ru.otus.crm.model;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addresses_generator")
    @SequenceGenerator(name = "addresses_generator", sequenceName = "addresses_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;


    @Column(name = "street")
    private String street;

    @Column(name="client_id")
    private Long clientId;

    public Address() {
    }

    public Address(Long id, String street) {
        this.id = id;
        this.street = street;
        this.clientId = null;
    }

    public Address(Long id, String street, Long clientId) {
        this.id = id;
        this.street = street;
        this.clientId = clientId;
    }

    @Override
    public Address clone() {
        return new Address(this.id, this.street, this.clientId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                '}';
    }

    public String toJson() {
        return """
                {
                    "id": %d,
                    "street": "%s"
                }
                """.formatted(id, street);
    }
}
