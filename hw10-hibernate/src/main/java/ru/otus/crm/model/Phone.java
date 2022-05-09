package ru.otus.crm.model;

import javax.persistence.*;

@Entity
@Table(name = "phones")
public class Phone {
    public Phone() {
    }

    public Phone(Long id, String number) {
        this.id = id;
        this.number = number;
    }

    @ManyToOne
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private Client client;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phones_generator")
    @SequenceGenerator(name = "phones_generator", sequenceName = "phones_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "number")
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
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
