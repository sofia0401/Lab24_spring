package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "manufactures")
public class Manufacture {

    @Id
    @SequenceGenerator(name = "manufactures_seq", sequenceName =
            "manufactures_sequence", allocationSize = 1)
    @GeneratedValue(generator = "manufactures_seq", strategy =
            GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", length = 30)
    private String name;
    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "manufacture",targetEntity = Phone.class,cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Phone> phonesSet;

    public Set<Phone> getPhoneSet() {
        return phonesSet;
    }

    public void setPhonesSet(Set<Phone> phonesSet) {
        this.phonesSet = phonesSet;
    }


    public Manufacture() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Manufacture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}