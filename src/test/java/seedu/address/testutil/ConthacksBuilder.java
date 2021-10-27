package seedu.address.testutil;

import seedu.address.model.Conthacks;
import seedu.address.model.modulelesson.ModuleLesson;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code Conthacks ab = new ConthacksBuilder().withPerson("John", "Doe").build();}
 */
public class ConthacksBuilder {

    private Conthacks conthacks;

    public ConthacksBuilder() {
        conthacks = new Conthacks();
    }

    public ConthacksBuilder(Conthacks conthacks) {
        this.conthacks = conthacks;
    }

    /**
     * Adds a new {@code Person} to the {@code Conthacks} that we are building.
     */
    public ConthacksBuilder withPerson(Person person) {
        conthacks.addPerson(person);
        return this;
    }

    /**
     * Adds a new {@code ModuleLesson} to the {@code Conthacks} that we are building.
     */
    public ConthacksBuilder withModuleLesson(ModuleLesson moduleLesson) {
        conthacks.addLesson(moduleLesson);
        return this;
    }

    public Conthacks build() {
        return conthacks;
    }
}
