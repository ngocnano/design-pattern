package com.ngoctm.builder;

public class Person {
    private String name;
    private String position;

    public Person(){

    }

    public Person(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}

class PersonBuilder <SELF extends PersonBuilder<SELF>>{
    protected Person person;

    public PersonBuilder(){
        person = new Person();
    }

    public SELF withName(String name){
        this.person.setName(name);
        return self();
    }

    protected SELF self(){
        return (SELF) this;
    }
}

class EmployeeBuilder extends PersonBuilder<EmployeeBuilder>{

    public EmployeeBuilder withPosition(String position){
        this.person.setPosition(position);
        return self();
    }

    @Override
    protected EmployeeBuilder self() {
        return this;
    }
}
