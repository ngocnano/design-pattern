package com.ngoctm.solid.dip;

// High-level modules should not depend on low-level module
// Both should depend on abstraction

import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;

// Abstraction should not depend on detail
// de depend on abs
public class Demo {
    public static void main(String[] args) {
        RelationShips relationships = new RelationShips();
        Person john = new Person("John");
        relationships.addParentAndChild(john,new Person("Hung"));
        relationships.addParentAndChild(john,new Person("Hoang"));
        relationships.addParentAndChild(john,new Person("Tuan"));

        Research research = new Research(relationships);
    }
}

enum Relationship {
    PARENT,
    CHILD,
    SIBLING
}

class Person {
    public String name;

    public Person(String name) {
        this.name = name;
    }
}


//solution
interface RelationshipBrowser {
    List<Person> findAllChildOf(String name);
}

class RelationShips implements RelationshipBrowser{ // low-level relations store data
    private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();

    public void addParentAndChild(Person parent, Person child){
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }

    public List<Triplet<Person, Relationship, Person>> getRelations() {
        return relations;
    }

    public void setRelations(List<Triplet<Person, Relationship, Person>> relations) {
        this.relations = relations;
    }

    @Override
    public List<Person> findAllChildOf(String name) {
        List<Person> res = new ArrayList<>();
        relations.stream().filter(x -> (x.getValue0().name.equals("John")) && (x.getValue1() == Relationship.PARENT))
        .forEach(ch -> res.add(ch.getValue2()));
        return res;
    }
}

class Research { // high-level sort action depend on low-module
    //public Research(RelationShips relationShips) {
     //   List<Triplet<Person, Relationship, Person>> relations = relationShips.getRelations();
      //  relations.stream().filter(x -> (x.getValue0().name.equals("John")) && (x.getValue1() == Relationship.PARENT))
     //           .forEach(ch -> System.out.println("John has child " + ch.getValue2().name));
   // } // Khi Relationship thay doi rel List -> Array ??
    public Research(RelationshipBrowser relationShips){
        relationShips.findAllChildOf("John").forEach(ch -> System.out.println(ch.name));

    }
}
