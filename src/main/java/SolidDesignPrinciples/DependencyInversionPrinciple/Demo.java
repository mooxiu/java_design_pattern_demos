package SolidDesignPrinciples.DependencyInversionPrinciple;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.javatuples.Triplet;

class Demo {
    public static void main(String[] args) {
        Person parent = new Person("John");
        Person child = new Person("Cole");
        Relationships r = new Relationships();
        r.addParentRelation(parent, child);

        new Research(r);
    }
}

enum Relationship {
    PARENT,
    CHILD,
    SIBLINGS,
}

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

interface RelationshipBrowser {
    List<Person> findAllChildrenOf(String parent);
}

class Relationships implements RelationshipBrowser { // low level: related to data storage
    private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();

    public void addParentRelation(Person parent, Person child) {
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }

    @Override
    public List<Person> findAllChildrenOf(String parent) {
        List<Person> res = relations.stream()
                .filter(x -> x.getValue0().getName().equals(parent) && x.getValue1() == Relationship.PARENT)
                .map(Triplet::getValue2).toList();
        return res;
    }

//    // Problem: we expose our internal storage implementation to public
//    public List<Triplet<Person, Relationship, Person>> getRelations() {
//        return relations;
//    }

}

class Research { // high level

//    public Research(Relationships relationships) { // problem: we rely this on low-level module, this is bad
//        List<Triplet<Person, Relationship, Person>> relations = relationships.getRelations();
//        relations.stream()
//                .filter(x -> x.getValue0().getName().equals("John") && x.getValue1() == Relationship.PARENT)
//                .forEach(ch -> System.out.println("John has a son called " + ch.getValue2().getName()));
//    }

    public Research(RelationshipBrowser rb) {
        rb.findAllChildrenOf("John").forEach(ch -> System.out.println("John has a son called " + ch.getName()));
    }
}

