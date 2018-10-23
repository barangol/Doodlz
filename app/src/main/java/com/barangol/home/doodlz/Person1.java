package com.barangol.home.doodlz;

import android.support.annotation.NonNull;

import java.util.regex.Pattern;

public class Person1 implements Comparable<Person1>{
    private String name;
    Integer id;
    static String national;

    public Person1(String name) {
        this.name = name;
    }
    public String getName(){

        return this.name;
    }

    @Override
    public int compareTo(Person1 p) {
        return this.name.compareTo(p.getName());
    }
}
