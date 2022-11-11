package ru.dimk.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;
import java.util.stream.Collectors;

@Table(name = "clients")
public class Client {

    @Id
    @Column("id")
    private Long id;

    @Column("name")
    private String name;

    @MappedCollection(idColumn = "client_id")
    private Address address;

    @MappedCollection(idColumn = "client_id")
    private Set<Phone> phones;

    public Client() {
    }

    public Client(String name, Set<Phone> phones) {
        this.id = null;
        this.name = name;
        this.address = null;
        this.phones = phones;
    }

    @PersistenceCreator
    public Client(Long id, String name, Address address, Set<Phone> phones) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phones = phones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", phones=" + phones +
                '}';
    }

    public String toJson() {
        String phonesString = this.phones.stream()
                .map(Phone::toJson)
                .collect(Collectors.joining(",", "[", "]"));

        return """
                {
                    "id": %d,
                    "name": "%s",
                    "address": %s,
                    "phones": %s
                }
                """.formatted(id, name, address != null ? address.toJson() : null, phonesString);
    }
}
