package seedu.siasa.testutil;

import seedu.siasa.model.Siasa;
import seedu.siasa.model.person.Person;
import seedu.siasa.model.policy.Policy;

/**
 * A utility class to help with building objects.
 * Example usage: <br>
 *     {@code Siasa ab = new SiasaBuilder().withPerson("John", "Doe").build();}
 */
public class SiasaBuilder {

    private Siasa siasa;

    public SiasaBuilder() {
        siasa = new Siasa();
    }

    public SiasaBuilder(Siasa siasa) {
        this.siasa = siasa;
    }

    /**
     * Adds a new {@code Person} to the {@code Siasa} that we are building.
     */
    public SiasaBuilder withPerson(Person person) {
        siasa.addPerson(person);
        return this;
    }

    /**
     * Adds a new {@code Policy} to the {@code Siasa} that we are building.
     */
    public SiasaBuilder withPolicy(Policy policy) {
        siasa.addPolicy(policy);
        return this;
    }

    public Siasa build() {
        return siasa;
    }
}
