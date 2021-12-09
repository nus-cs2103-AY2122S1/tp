package seedu.times.testutil;

import static seedu.times.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.times.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.times.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.times.logic.parser.CliSyntax.PREFIX_NOK;
import static seedu.times.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.times.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.times.logic.commands.AddCommand;
import seedu.times.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.times.model.person.Student;
import seedu.times.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Student student) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(student);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + student.getName().fullName + " ");
        sb.append(PREFIX_PHONE + student.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + student.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + student.getAddress().value + " ");
        student.getTags().stream().forEach(s -> sb.append(PREFIX_TAG + s.tagName + " "));
        sb.append(PREFIX_NOK + " ");
        sb.append(PREFIX_NAME + student.getNok().getName().fullName + " ");
        sb.append(PREFIX_PHONE + student.getNok().getPhone().value + " ");
        sb.append(PREFIX_EMAIL + student.getNok().getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + student.getNok().getAddress().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditStudentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        //if (descriptor.getTuitionClasses().isPresent()) {
        //    List<TuitionClass> tuitionClasses = descriptor.getTuitionClasses().get();
        //    if(tuitionClasses.isEmpty()) {
        //       sb.append()
        //    }
        //}
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        if (descriptor.getNokName().isPresent() || descriptor.getNokAddress().isPresent()
                || descriptor.getNokEmail().isPresent() || descriptor.getNokPhone().isPresent()) {
            sb.append(PREFIX_NOK).append(" ");
        }
        descriptor.getNokName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getNokPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getNokEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getNokAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));

        return sb.toString();
    }
}
