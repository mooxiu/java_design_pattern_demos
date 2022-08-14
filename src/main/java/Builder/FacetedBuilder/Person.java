package Builder.FacetedBuilder;

/*
    learn: sometimes we need more than one builders to buid different aspects of an object
 */

public class Person {
    // address
    public String streetAddress, postcode, city;
    // employment
    public String companyName, position;
    public int annualIncome;

    @Override
    public String toString() {
        return "Person{" +
                "streetAddress='" + streetAddress + '\'' +
                ", postcode=" + postcode + '\'' +
                ", city=" + city + '\'' +
                ", companyName=" + companyName + '\'' +
                ", position=" + position + '\'' +
                ", annualIncome=" + annualIncome +
                '}';
    }

    public static void main(String[] args) {
        var pb = new PersonBuilder();
        var p = pb
                .lives()
                .at("abbey road")
                .in("london")
                .withPostCode("123-456")
                .works()
                .at("google")
                .withAnnualIncome(1000000)
                .withPosition("sdeIII")
                .build();
        System.out.println(p);
    }
}

// builder facade
class PersonBuilder {
    protected Person person = new Person();

    // learn: so any builder extends this builder can call this to get PersonAddressBuilder Object
    public PersonAddressBuilder lives() {
        return new PersonAddressBuilder(person);
    }

    public PersonJobBuilder works() {
        return new PersonJobBuilder(person);
    }

    public Person build() {
        return person;
    }
}

/*
    learn: why we need to inherit from PersonBuilder?
    So we can have the call works() to get another builder facade.
 */
class PersonAddressBuilder extends PersonBuilder {
    public PersonAddressBuilder(Person person) {
        this.person = person;
    }

    public PersonAddressBuilder at(String street) {
        this.person.streetAddress = street;
        return this;
    }

    public PersonAddressBuilder in(String city) {
        this.person.city = city;
        return this;
    }

    public PersonAddressBuilder withPostCode(String postCode) {
        this.person.postcode = postCode;
        return this;
    }
}

class PersonJobBuilder extends PersonBuilder {
    public PersonJobBuilder(Person person) {
        this.person = person;
    }

    public PersonJobBuilder at(String companyName) {
        this.person.companyName = companyName;
        return this;
    }

    public PersonJobBuilder withPosition(String position) {
        this.person.position = position;
        return this;
    }

    public PersonJobBuilder withAnnualIncome(int income) {
        this.person.annualIncome = income;
        return this;
    }
}
