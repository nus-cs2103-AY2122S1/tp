package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.display.DisplayList;

import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;
import static seedu.address.model.display.DisplayMode.DISPLAY_TRANSACTIONS;

public class ListTransactionCommand extends ListCommand {

    public static final String MESSAGE_SUCCESS_ALL = "Listed all past transactions";
    public static final String MESSAGE_SUCCESS = "Listed past transaction %s";

    public static final String TRANSACTIONS_KEYWORD = "txns";

    public static final String MESSAGE_USAGE =
        ListInventoryCommand.COMMAND_WORD + " " + TRANSACTIONS_KEYWORD + ": lists all past transactions.\n"
        + ListInventoryCommand.COMMAND_WORD + " " + TRANSACTIONS_KEYWORD
        + " ID: list items in the specified transaction.\n";

    private final Optional<String> transactionId;

    /**
     * Creates a ListTransactionCommand that will display a specific transaction.
     * If transactionId is an empty string, display all transactions instead.
     *
     * {@code transactionId} must not be null.
     */
    public ListTransactionCommand(String transactionId) {
        requireNonNull(transactionId);

        this.transactionId = transactionId.equals("")
            ? Optional.empty()
            : Optional.of(transactionId);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // If no id specified, display all past transactions
        if (transactionId.isEmpty()) {
            model.updateFilteredDisplayList(DISPLAY_TRANSACTIONS, PREDICATE_SHOW_ALL_ITEMS);
            return new CommandResult(MESSAGE_SUCCESS_ALL);
        }

        // Else, display specific transaction

        return new CommandResult(String.format(MESSAGE_SUCCESS, transactionId.get()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListTransactionCommand) // instanceof handles nulls
                && transactionId.equals(((ListTransactionCommand) other).transactionId); // state check
    }

}
