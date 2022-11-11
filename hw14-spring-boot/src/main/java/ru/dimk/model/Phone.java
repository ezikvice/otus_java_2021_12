package ru.dimk.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "phones")
public class Phone {
    @Id
    @Column("id")
    private Long id;

    @Column("number")
    private String number;

    @Column("client_id")
    private Long clientId;

    public Phone() {
    }

    public Phone(String number) {
        this.id = null;
        this.number = number;
    }

    @PersistenceCreator
    public Phone(Long id, Long clientId, String number) {
        this.id = id;
        this.clientId = clientId;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClient(Long clientId) {
        this.clientId = clientId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }

    public String toJson() {
        return """
                {"id": %d, "number": "%s"}
                """.formatted(id, number);
    }
}
