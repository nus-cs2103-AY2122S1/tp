package seedu.fast.testutil;

import seedu.fast.model.Fast;
import seedu.fast.model.person.Person;

/**
 * A utility class to help with building FAST objects.
 * Example usage: <br>
 *     {@code Fast fast = new FastBuilder().withPerson("John", "Doe").build();}
 */
public class FastBuilder {

    private Fast fast;

    public FastBuilder() {
        fast = new Fast();
    }

    public FastBuilder(Fast fast) {
        this.fast = fast;
    }

    /**
     * Adds a new {@code Person} to the {@code Fast} that we are building.
     */
    public FastBuilder withPerson(Person person) {
        fast.addPerson(person);
        return this;
    }

    public Fast build() {
        return fast;
    }
}
