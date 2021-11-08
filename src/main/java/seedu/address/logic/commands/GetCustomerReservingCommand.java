package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.util.CommandUtil;
import seedu.address.model.Model;
import seedu.address.model.person.customer.CustomerContainsPhonePredicate;
import seedu.address.model.reservation.Reservation;

/**
 * Gets the corresponding customer who made the reservation
 */
public class GetCustomerReservingCommand extends Command {
    public static final String COMMAND_WORD = "getc";

    public static final String MESSAGE_USAGE = CommandUtil.formatCommandWord(COMMAND_WORD)
            + ": Gets the customer who made the reservation "
            + "specified by the given index.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + CommandUtil.formatCommandWord(COMMAND_WORD) + " 1";

    public static final String MESSAGE_CUSTOMER_RESERVING_LISTED =
            "Corresponding customer who made the reservation is listed!";

    private final Index targetIndex;

    public GetCustomerReservingCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Reservation> lastShownList = model.getFilteredReservationList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RESERVATION_DISPLAYED_INDEX);
        }

        Reservation reservation = lastShownList.get(targetIndex.getZeroBased());
        assert reservation != null;

        CustomerContainsPhonePredicate predicate = new CustomerContainsPhonePredicate(reservation.getPhone());
        model.updateFilteredCustomerList(predicate);
        return new CommandResult(
                MESSAGE_CUSTOMER_RESERVING_LISTED,
                false, false, true, false, false, false
        );
    }
}
