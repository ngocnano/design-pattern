package com.ngoctm.builder.faceted;

public class Person {
    //address
    private String streetAddress, postcode, city;

    //employee
    private String companyName, positions;
    private int annualIncome;

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }

    public int getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(int annualIncome) {
        this.annualIncome = annualIncome;
    }

    @Override
    public String toString() {
        return "Person{" +
                "streetAddress='" + streetAddress + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                ", companyName='" + companyName + '\'' +
                ", positions='" + positions + '\'' +
                ", annualIncome=" + annualIncome +
                '}';
    }
}

class PersonBuilder{
    protected Person person = new Person();

    public PersonAddressBuilder lives(){
        return new PersonAddressBuilder(person);
    }

    public PersonJobBuilder works()
    {
        return new PersonJobBuilder(person);
    }

    public Person build(){
        return this.person;
    }
}

class PersonAddressBuilder extends PersonBuilder
{
    public PersonAddressBuilder(Person person)
    {
        this.person = person;
    }

    public PersonAddressBuilder at(String streetAddress)
    {
        this.person.setStreetAddress(streetAddress);
        return this;
    }

    public PersonAddressBuilder in(String city)
    {
        this.person.setCity(city);
        return this;
    }

    public PersonAddressBuilder withCode(String postcode)
    {
        this.person.setPostcode(postcode);
        return this;
    }
}

class PersonJobBuilder extends PersonBuilder
{
    public PersonJobBuilder(Person person)
    {
        this.person = person;
    }

    public PersonJobBuilder at(String companyName)
    {
        this.person.setCompanyName(companyName);
        return this;
    }

    public PersonJobBuilder asA(String position)
    {
        this.person.setPositions(position);
        return this;
    }

    public PersonJobBuilder earning(int annualIncome){
        this.person.setAnnualIncome(annualIncome);
        return this;
    }

}

class Demo
{
    public static void main(String[] args) {
        PersonBuilder personBuilder = new PersonBuilder();
        Person person = personBuilder.lives().at("Nguyen Trai").withCode("10000").in("Ha Noi")
                .works().at("Viettel").asA("SE").earning(100).build();
        System.out.println(person);
    }
}