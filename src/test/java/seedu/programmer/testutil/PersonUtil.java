package seedu.programmer.testutil;

import static seedu.programmer.logic.parser.CliSyntax.PREFIX_CLASS_ID;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import seedu.programmer.logic.commands.AddCommand;
import seedu.programmer.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.programmer.model.person.Person;


/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_STUDENT_ID + person.getStudentId().studentId + " ");
        sb.append(PREFIX_CLASS_ID + person.getClassId().classId + " ");
        sb.append(PREFIX_GRADE + person.getGrade().grade + " ");

        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getStudentId().ifPresent(studentId ->
                sb.append(PREFIX_STUDENT_ID).append(studentId.studentId).append(" "));
        descriptor.getClassId().ifPresent(classId -> sb.append(PREFIX_CLASS_ID).append(classId.classId).append(" "));
        descriptor.getGrade().ifPresent(grade -> sb.append(PREFIX_GRADE).append(grade.grade).append(" "));

        return sb.toString();
    }
}
