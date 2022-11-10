package ru.dimk.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "addresses")
public class Address {

    @Id
    @Column("id")
    private Long id;

    @Column("client_id")
    private Long clientId;

    @Column("street")
    private String street;

    public Address(String street) {
        this.id = null;
        this.clientId = null;
        this.street = street;
    }

    @PersistenceCreator
    public Address(Long id, Long clientId, String street) {
        this.id = id;
        this.clientId = clientId;
        this.street = street;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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
