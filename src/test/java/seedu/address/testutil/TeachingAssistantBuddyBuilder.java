package seedu.address.testutil;

import seedu.address.model.TeachingAssistantBuddy;
import seedu.address.model.module.Module;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class TeachingAssistantBuddyBuilder {

    private TeachingAssistantBuddy teachingAssistantBuddy;

    public TeachingAssistantBuddyBuilder() {
        teachingAssistantBuddy = new TeachingAssistantBuddy();
    }

    public TeachingAssistantBuddyBuilder(TeachingAssistantBuddy teachingAssistantBuddy) {
        this.teachingAssistantBuddy = teachingAssistantBuddy;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public TeachingAssistantBuddyBuilder withModule(Module module) {
        teachingAssistantBuddy.addModule(module);
        return this;
    }

    public TeachingAssistantBuddy build() {
        return teachingAssistantBuddy;
    }
}
