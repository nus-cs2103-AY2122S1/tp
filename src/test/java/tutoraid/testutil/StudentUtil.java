package tutoraid.testutil;

import static tutoraid.logic.parser.CliSyntax.PREFIX_PARENT_NAME;
import static tutoraid.logic.parser.CliSyntax.PREFIX_PARENT_PHONE;
import static tutoraid.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;
import static tutoraid.logic.parser.CliSyntax.PREFIX_STUDENT_PHONE;

import tutoraid.logic.commands.AddStudentCommand;
import tutoraid.model.student.Student;

/**
 * A utility class for Student.
 */
public class StudentUtil {

    /**
     * Returns an add command string for adding the {@code student}.
     */
    public static String getAddCommand(Student student) {
        return AddStudentCommand.COMMAND_WORD + " " + getStudentDetails(student);
    }

    /**
     * Returns the part of command string for the given {@code student}'s details.
     */
    public static String getStudentDetails(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_STUDENT_NAME + student.getStudentName().fullName + " ");
        sb.append(PREFIX_STUDENT_PHONE + student.getStudentPhone().value + " ");
        sb.append(PREFIX_PARENT_NAME + student.getParentName().fullName + " ");
        sb.append(PREFIX_PARENT_PHONE + student.getParentPhone().value + " ");

        return sb.toString();
    }
}
