package seedu.programmer.testutil;

import static seedu.programmer.logic.parser.CliSyntax.PREFIX_CLASS_ID;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_GRADE;
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
        return PREFIX_NAME + student.getName().fullName + " "
                + PREFIX_STUDENT_ID + student.getStudentId().studentId + " "
                + PREFIX_CLASS_ID + student.getClassId().classId + " "
                + PREFIX_GRADE + student.getGrade().grade + " ";
    }

    /**
     * Returns the part of command string for the given {@code EditStudentDescriptor}'s details.
     */
    public static String getEditStudentDescriptorDetails(EditStudentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getStudentId().ifPresent(studentId ->
                sb.append(PREFIX_STUDENT_ID).append(studentId.studentId).append(" "));
        descriptor.getClassId().ifPresent(classId -> sb.append(PREFIX_CLASS_ID).append(classId.classId).append(" "));
        descriptor.getGrade().ifPresent(grade -> sb.append(PREFIX_GRADE).append(grade.grade).append(" "));

        return sb.toString();
    }
}
