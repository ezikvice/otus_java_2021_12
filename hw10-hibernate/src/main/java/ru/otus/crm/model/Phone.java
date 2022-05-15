package ru.otus.crm.model;

import javax.persistence.*;

@Entity
@Table(name = "phones")
public class Phone {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phones_generator")
    @SequenceGenerator(name = "phones_generator", sequenceName = "phones_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "number")
    private String number;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false, foreignKey = @ForeignKey(foreignKeyDefinition = ""))
    private Client client;

    public Phone() {
    }

    public Phone(Long id, String number) {
        this.id = id;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
}
