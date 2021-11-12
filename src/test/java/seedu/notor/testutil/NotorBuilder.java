package seedu.notor.testutil;

import seedu.notor.model.Notor;
import seedu.notor.model.person.Person;

/**
 * A utility class to help with building Notor objects.
 * Example usage: <br>
 * {@code Notor ab = new NotorBuilder().withPerson("John", "Doe").build();}
 */
public class NotorBuilder {

    private final Notor notor;

    public NotorBuilder() {
        notor = new Notor();
    }

    public NotorBuilder(Notor notor) {
        this.notor = notor;
    }

    /**
     * Adds a new {@code Person} to the {@code Notor} that we are building.
     */
    public NotorBuilder withPerson(Person person) {
        notor.addPerson(person);
        return this;
    }

    public Notor build() {
        return notor;
    }
}
