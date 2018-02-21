package com.homework.wspolnota.model;

public enum Sex {

    MALE("Mężczyzna"), FEMALE("Kobieta");

    Sex (String sex){
        this.sex = sex;
    }

    private final String sex;
}
