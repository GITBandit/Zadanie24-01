package com.homework.wspolnota.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Flat {

    @Id
    @GeneratedValue
    private long id;
    private int flatNumber;
    private double flatArea;
    @OneToMany(mappedBy = "flat")
    private List<Occupant> occupantList;
    @ManyToOne
    private HousingAssociation housingAssociation;

    public int getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(int flatNumber) {
        this.flatNumber = flatNumber;
    }

    public double getFlatArea() {
        return flatArea;
    }

    public void setFlatArea(double flatArea) {
        this.flatArea = flatArea;
    }

    public List<Occupant> getOccupantList() {
        return occupantList;
    }

    public void setOccupantList(List<Occupant> occupantList) {
        this.occupantList = occupantList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public HousingAssociation getHousingAssociation() {
        return housingAssociation;
    }

    public void setHousingAssociation(HousingAssociation housingAssociation) {
        this.housingAssociation = housingAssociation;
    }
}
