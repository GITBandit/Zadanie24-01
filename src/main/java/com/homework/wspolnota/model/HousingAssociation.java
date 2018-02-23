package com.homework.wspolnota.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class HousingAssociation {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String streetName;
    private int houseNumber;
    @OneToMany(mappedBy = "housingAssociation", cascade = {CascadeType.ALL})
    private List<Flat> flatList;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public List<Flat> getFlatList() {
        return flatList;
    }

    public void setFlatList(List<Flat> flatList) {
        this.flatList = flatList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        if(name == null){
            return streetName + " " + houseNumber;
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
