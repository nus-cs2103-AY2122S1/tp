package nustracker.testutil;

import static nustracker.logic.parser.CliSyntax.PREFIX_DATE;
import static nustracker.logic.parser.CliSyntax.PREFIX_NAME;
import static nustracker.logic.parser.CliSyntax.PREFIX_TIME;

import nustracker.logic.commands.CreateCommand;
import nustracker.logic.commands.EditCommand;
import nustracker.model.event.Event;

/**
 * A utility class for Event.
 */
public class EventUtil {

    /**
     * Returns an add command string for adding the {@code event}.
     */
    public static String getCreateCommand(Event event) {
        return CreateCommand.COMMAND_WORD + " " + getEventDetails(event);
    }

    /**
     * Returns the part of command string for the given {@code event}'s details.
     */
    public static String getEventDetails(Event event) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + event.getName().toString() + " ");
        sb.append(PREFIX_DATE + event.getDate().getEventDate() + " ");
        sb.append(PREFIX_TIME + event.getTime().getEventTime() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditEventDescriptor}'s details.
     */
    public static String getEditEventDescriptorDetails(EditCommand.EditStudentDescriptor descriptor) {
        // To use when event filtering feature is implemented
        return "";
    }
}
