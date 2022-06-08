package ru.dimk.crm.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Table(name = "clients")
public class Client implements Cloneable {

    @Id
    @Column("id")
    private Long id;

    @Column("name")
    private String name;

    private Address address;

    private List<Phone> phones;

    public Client() {
    }

    public Client(String name) {
        this.id = null;
        this.name = name;
        this.address = null;
        this.phones = Collections.emptyList();
    }

    public Client(Long id, String name) {
        this.id = id;
        this.name = name;
        this.address = null;
        this.phones = Collections.emptyList();
    }

    public Client(Long id, String name, Address address, List<Phone> phones) {
        this.id = id;
        this.name = name;
        this.address = address;
        setPhones(phones);
    }

    @Override
    public Client clone() {
        return new Client(this.id, this.name, this.address, this.phones);
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

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        for (Phone phone : phones) {
            phone.setClient(this);
        }
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
                """.formatted(id, name, address!=null?address.toJson():null, phonesString);
    }
}
