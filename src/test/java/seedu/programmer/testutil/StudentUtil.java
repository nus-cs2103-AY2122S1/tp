package seedu.programmer.testutil;

import static seedu.programmer.logic.parser.CliSyntax.PREFIX_CLASS_ID;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import seedu.programmer.logic.commands.AddCommand;
import seedu.programmer.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.programmer.model.student.Student;


/**
 * A utility class for student.
 */
public class StudentUtil {

    /**
     * Returns an add command string for adding the {@code student}.
     */
    public static String getAddCommand(Student student) {
        return AddCommand.COMMAND_WORD + " " + getStudentDetails(student);
    }

    /**
     * Returns the part of command string for the given {@code student}'s details.
     */
    public static String getStudentDetails(Student student) {
        return PREFIX_NAME + student.getNameValue() + " "
                + PREFIX_STUDENT_ID + student.getStudentIdValue() + " "
                + PREFIX_CLASS_ID + student.getClassIdValue() + " "
                + PREFIX_EMAIL + student.getEmailValue() + " ";
    }

    /**
     * Returns the part of command string for the given {@code EditStudentDescriptor}'s details.
     */
    public static String getEditStudentDescriptorDetails(EditStudentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.toString()).append(" "));
        descriptor.getStudentId().ifPresent(studentId ->
                sb.append(PREFIX_STUDENT_ID).append(studentId.toString()).append(" "));
        descriptor.getClassId().ifPresent(classId -> sb.append(PREFIX_CLASS_ID).append(classId.toString()).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.toString()).append(" "));

        return sb.toString();
    }
}
