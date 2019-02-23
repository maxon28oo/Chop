package com.libary.models;

import com.libary.interfaces.Printable;

public class Human implements Printable {
    private String name, surname;
    private int age;


    public Human(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    @Override
    public String getAllName() {
        return name+' '+surname;
    }

    @Override
    public String infoPrint() {
        return name+','+surname+','+age;

    }

    @Override
    public String toString() {
        return "name='" + name + "'" +
                ", surname='" + surname + "'" +
                ", age=" + age;
    }
}
