package com.homework.wspolnota.model;

public enum Sex {

    MALE("Mężczyzna"), FEMALE("Kobieta");

    Sex (String sexTranslation){
        this.sexTranslation = sexTranslation;
    }

    private final String sexTranslation;

    public String getSexTranslation() {
        return sexTranslation;
    }
}
