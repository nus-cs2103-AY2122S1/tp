package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;

import seedu.address.model.Model;

/**
 * Switches to the customer view in the application.
 */
public class CustomerCommand extends Command {

    public static final String COMMAND_WORD = "customer";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches to customer view and shows all customers.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_SWITCH_MESSAGE = "Switched to Customer View.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
        return new CommandResult(SHOWING_SWITCH_MESSAGE, false, false, true, false, false, false);
    }
}

