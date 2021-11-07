package seedu.address.testutil.guest;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.guest.CheckInNewGuestCommand;
import seedu.address.logic.commands.guest.EditGuestCommand.EditGuestDescriptor;
import seedu.address.model.guest.Guest;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Guest.
 */
public class GuestUtil {

    /**
     * Returns an add command string for adding the {@code guest}.
     */
    public static String getAddCommand(Guest guest) {
        return CheckInNewGuestCommand.COMMAND_WORD + " " + getGuestDetails(guest);
    }

    /**
     * Returns the part of command string for the given {@code guest}'s details.
     */
    public static String getGuestDetails(Guest guest) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + guest.getName().fullName + " ");
        sb.append(PREFIX_EMAIL + guest.getEmail().value + " ");
        sb.append(PREFIX_ROOM_NUMBER + guest.getRoomNumber().value + " ");
        sb.append(PREFIX_PASSPORT_NUMBER + guest.getPassportNumber().value + " ");
        guest.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditGuestDescriptor}'s details.
     */
    public static String getEditGuestDescriptorDetails(EditGuestDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getPassportNumber().ifPresent(
            passportNumber -> sb.append(PREFIX_PASSPORT_NUMBER).append(passportNumber.value).append(" "));
        descriptor.getRoomNumber().ifPresent(
            roomNumber -> sb.append(PREFIX_ROOM_NUMBER).append(roomNumber.value).append(" "));

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
