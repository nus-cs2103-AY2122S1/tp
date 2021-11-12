package seedu.notor.testutil;

import seedu.notor.logic.executors.person.PersonEditExecutor;
import seedu.notor.model.person.Person;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns a create command string for adding a {@code person} which does not have any tags.
     */
    public static String getCreateCommand(Person person) {
        return String.format("person %s /create p:%s e:%s", person.getName(),
                person.getPhone(), person.getEmail());
    }

    public static String getCreateCommandWithTags(Person person) {
        return String.format("person %s /create p:%s e:%s t:%s", person.getName(),
                person.getPhone(), person.getEmail(), person.getTagsString());
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorCommand(int index, PersonEditExecutor.PersonEditDescriptor descriptor) {
        String command = String.format("person %d /edit n:%s p:%s e:%s", index, descriptor.getName().get(),
                descriptor.getPhone().get(), descriptor.getEmail().get());
        return command;
    }
}
