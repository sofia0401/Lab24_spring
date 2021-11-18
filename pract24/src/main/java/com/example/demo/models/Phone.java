package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.FetchType;

@Entity
@Table(name = "phones")
public class Phone {

    @Id
    @SequenceGenerator(name = "phones_seq", sequenceName =
            "phones_sequence", allocationSize = 1)
    @GeneratedValue(generator = "phones_seq", strategy =
            GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", length = 20)
    private String name;
    @Column(name = "creationyear", length = 4)
    private int creationYear;

    @ManyToOne(targetEntity = Manufacture.class, cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name="manufacture_id")
    @JsonIgnore

    private Manufacture manufacture;

    public Manufacture getManufacture() {
        return manufacture;
    }
    public void setManufacture(Manufacture manufacture) {
        this.manufacture = manufacture;
    }



    public Phone() {

    }


    public Phone(Long id, String name, int creationYear) {
        this.id = id;
        this.name = name;
        this.creationYear = creationYear;
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

    public int getCreationYear() {
        return creationYear;
    }

    public void setCreationYear(int creationYear) {
        this.creationYear = creationYear;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationYear=" + creationYear +
                '}';
    }


}

