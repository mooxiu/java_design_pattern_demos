package Builder.BeforeRecursiveGenerics;

public class Person {
    public String name;
    public String position;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}

// learn: recursive generics
class PersonBuilder<E extends PersonBuilder<E>> {
    protected Person person = new Person();

    public E withName(String name) {
        person.name = name;
        return e();
    }

    protected E e() {
        return (E) this;
    }

    public Person build() {
        return person;
    }
}

class EmployeeBuilder extends PersonBuilder<EmployeeBuilder> {
    public EmployeeBuilder worksAs(String position) {
        person.position = position;
        return this;
    }

    @Override
    protected EmployeeBuilder e() {
        return this;
    }
}

class Demo {
    public static void main(String[] args) {
        EmployeeBuilder eb = new EmployeeBuilder()
                .withName("Dmitri").worksAs("analyst");
        System.out.println(eb.build());
    }
}
