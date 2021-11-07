package seedu.address.logic.commands.guest;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.guest.Guest;

/**
 * Checks in a guest into the hotel.
 */
public class CheckInReturningGuestCommand extends Command {

    public static final String COMMAND_WORD = "returncheckin";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Checks in a returning guest to the hotel.\n"
            + "Parameters: "
            + PREFIX_PASSPORT_NUMBER + "PASSPORT_NUMBER "
            + PREFIX_ROOM_NUMBER + "ROOM_NUMBER \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PASSPORT_NUMBER + "T288230D "
            + PREFIX_ROOM_NUMBER + "123";

    public static final String MESSAGE_SUCCESS_RETURNING_GUEST = "Returning guest checked in: %1$s";
    public static final String MESSAGE_DUPLICATE_GUEST = "This guest is already checked in.";
    public static final String MESSAGE_NONEXISTENT_GUEST = "This guest does not belong in our archive.";
    public static final String MESSAGE_DUPLICATE_ROOM = "This room number is already in use.";

    private Guest toCheckIn;

    /**
     * Creates an CheckInReturningGuestCommand to add the specified {@code Guest}
     */
    public CheckInReturningGuestCommand(Guest guest) {
        requireNonNull(guest);
        toCheckIn = guest;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasGuest(toCheckIn)) {
            throw new CommandException(MESSAGE_DUPLICATE_GUEST);
        }

        if (model.getArchivedGuest(toCheckIn.getPassportNumber()).isEmpty()) {
            throw new CommandException(MESSAGE_NONEXISTENT_GUEST);
        }

        if (model.getFilteredGuestList()
                .stream()
                .filter(v -> v.getRoomNumber().equals(toCheckIn.getRoomNumber()))
                .findAny()
                .orElse(null) != null) {
            throw new CommandException(MESSAGE_DUPLICATE_ROOM);
        }

        Guest archivedGuest = model.getArchivedGuest(toCheckIn.getPassportNumber()).get();
        Guest returningGuest = new Guest(archivedGuest.getName(), archivedGuest.getEmail(),
                archivedGuest.getTags(), toCheckIn.getRoomNumber(), archivedGuest.getPassportNumber());
        this.toCheckIn = returningGuest;
        model.addGuest(returningGuest);
        model.deleteArchivedGuest(archivedGuest);
        return new CommandResult(String.format(MESSAGE_SUCCESS_RETURNING_GUEST, returningGuest));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CheckInReturningGuestCommand // instanceof handles nulls
                && toCheckIn.equals(((CheckInReturningGuestCommand) other).toCheckIn));
    }
}
