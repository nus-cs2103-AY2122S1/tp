package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutee.Tutee;

/**
 * A utility class for Tutee.
 */
public class TuteeUtil {

    /**
     * Returns an add command string for adding the {@code tutee}.
     */
    public static String getAddCommand(Tutee tutee) {
        return AddCommand.COMMAND_WORD + " " + getTuteeDetails(tutee);
    }

    /**
     * Returns the part of command string for the given {@code tutee}'s details.
     */
    public static String getTuteeDetails(Tutee tutee) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + tutee.getName().fullName + " ");
        sb.append(PREFIX_PHONE + tutee.getPhone().value + " ");
        sb.append(PREFIX_SCHOOL + tutee.getSchool().value + " ");
        sb.append(PREFIX_LEVEL + tutee.getLevel().value + " ");
        sb.append(PREFIX_ADDRESS + tutee.getAddress().value + " ");
        tutee.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditTuteeDescriptor}'s details.
     */
    public static String getEditTuteeDescriptorDetails(EditCommand.EditTuteeDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getSchool().ifPresent(school -> sb.append(PREFIX_SCHOOL).append(school.value).append(" "));
        descriptor.getLevel().ifPresent(level -> sb.append(PREFIX_LEVEL).append(level.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
