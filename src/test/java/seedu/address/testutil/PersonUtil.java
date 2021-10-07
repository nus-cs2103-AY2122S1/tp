package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_PHONE;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.model.person.Person;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddStudentCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_STUDENT_NAME + person.getStudentName().fullName + " ");
        sb.append(PREFIX_STUDENT_PHONE + person.getStudentPhone().value + " ");
        sb.append(PREFIX_PARENT_NAME + person.getParentName().fullName + " ");
        sb.append(PREFIX_PARENT_PHONE + person.getParentPhone().value + " ");

        return sb.toString();
    }
}
