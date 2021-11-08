package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.logic.position.AddPositionCommand;
import seedu.address.logic.position.EditPositionCommand.EditPositionDescriptor;
import seedu.address.model.position.Position;

public class PositionUtil {

    /**
     * Returns an add command string for adding the {@code position}.
     */
    public static String getAddPositionCommand(Position position) {
        return AddPositionCommand.COMMAND_WORD + " " + getPositionDetails(position);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPositionDetails(Position position) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TITLE + position.getTitle().fullTitle + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPositionDescriptor}'s details.
     */
    public static String getEditPositionDescriptorDetails(EditPositionDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getTitle().ifPresent(title -> sb.append(PREFIX_TITLE).append(title.fullTitle).append(" "));
        descriptor.getPositionStatus()
                .ifPresent(status -> sb.append(PREFIX_POSITION_STATUS)
                        .append(status.toString().toLowerCase()).append(" "));
        return sb.toString();
    }
}
