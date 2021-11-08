package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditEventDescriptor;
import seedu.address.model.event.Event;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Event.
 */
public class EventUtil {

    /**
     * Returns an add command string for adding the {@code event}.
     */
    public static String getAddCommand(Event event) {
        return AddCommand.COMMAND_WORD + " " + getEventDetails(event);
    }

    /**
     * Returns the part of command string for the given {@code event}'s details.
     */
    public static String getEventDetails(Event event) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + event.getName().fullName + " ");
        sb.append(PREFIX_DATE + event.getDate().value + " ");
        sb.append(PREFIX_TIMESLOT + event.getTimeSlot().toString() + " ");
        sb.append(PREFIX_LOCATION + event.getLocation().value + " ");
        event.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        sb.append(PREFIX_REMARK + event.getRemark().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditEventDescriptor}'s details.
     */
    public static String getEditEventDescriptorDetails(EditEventDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getDate().ifPresent(date -> sb.append(PREFIX_DATE).append(date.value).append(" "));
        descriptor.getTimeSlot().ifPresent(timeSlot -> sb.append(PREFIX_TIMESLOT).append(timeSlot).append(" "));
        descriptor.getLocation().ifPresent(address -> sb.append(PREFIX_LOCATION).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG).append(" ");
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        descriptor.getRemark().ifPresent(remark -> sb.append(PREFIX_REMARK).append(remark.value));
        return sb.toString();
    }
}
