package com.caresyntax.ssa.model;


public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE"),
    NONE("NONE");

    private String value;

    private Gender(String gender){
        this.value = gender;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
