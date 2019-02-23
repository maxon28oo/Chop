package com.libary.models;

import com.libary.interfaces.Printable;
import com.libary.interfaces.PublicationType;

public class Book implements Printable {

    private String name;
    private boolean isTaken;
    private PublicationType publicationType;


    public boolean isTaken() {
        return isTaken;
    }


    public Book(String name) {
        this.name = name;
        isTaken = false;
        this.publicationType = PublicationType.BOOK;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getAllName() {
        return name;
    }

    @Override
    public String infoPrint() {
        return name+","+(isTaken ? "true" : "false");

    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", Available=" + !isTaken +
                ", publicationType=" + publicationType;
    }
}
