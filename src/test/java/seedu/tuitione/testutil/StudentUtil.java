package seedu.tuitione.testutil;

import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.Set;

import seedu.tuitione.logic.commands.AddCommand;
import seedu.tuitione.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.tuitione.model.remark.Remark;
import seedu.tuitione.model.student.Student;

/**
 * A utility class for Student.
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
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + student.getName().fullName + " ");
        sb.append(PREFIX_PHONE + student.getParentContact().value + " ");
        sb.append(PREFIX_EMAIL + student.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + student.getAddress().value + " ");
        sb.append(PREFIX_GRADE + student.getGrade().value + " ");
        student.getRemarks().stream().forEach(
            s -> sb.append(PREFIX_REMARK + s.remarkName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditStudentDescriptor}'s details.
     */
    public static String getEditStudentDescriptorDetails(EditStudentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getGrade().ifPresent(grade -> sb.append(PREFIX_GRADE).append(grade.value).append(" "));
        if (descriptor.getRemarks().isPresent() && !descriptor.getRemarks().get().isEmpty()) {
            Set<Remark> remarks = descriptor.getRemarks().get();
            if (remarks.isEmpty()) {
                sb.append(PREFIX_REMARK);
            } else {
                remarks.forEach(s -> sb.append(PREFIX_REMARK).append(s.remarkName).append(" "));
            }
        }
        return sb.toString();
    }


}
