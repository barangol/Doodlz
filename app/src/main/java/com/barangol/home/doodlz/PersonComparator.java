package com.barangol.home.doodlz;

import java.util.Comparator;

class PersonComparator implements Comparator<Person> {

    public int compare(Person a, Person b){

        return a.getName().compareTo(b.getName());
    }
}
class Person{

    private String name;
    Person(String name){

        this.name=name;
    }
    String getName(){return name;}
}