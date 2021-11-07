package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;
import static seedu.address.model.display.DisplayMode.DISPLAY_TRANSACTION_LIST;

import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ListTransactionCommand extends ListCommand {

    public static final String MESSAGE_SUCCESS_ALL = "Listed all past transactions";
    public static final String MESSAGE_SUCCESS = "Listed past transaction %s";
    public static final String MESSAGE_TXN_NOT_FOUND = "Transaction %s not found";

    public static final String TRANSACTIONS_KEYWORD = "txns";

    public static final String MESSAGE_USAGE =
        ListInventoryCommand.COMMAND_WORD + " " + TRANSACTIONS_KEYWORD + ": lists all past transactions.\n"
        + ListInventoryCommand.COMMAND_WORD + " " + TRANSACTIONS_KEYWORD
        + " ID: list items in the specified transaction.";

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

            String cost = String.format("$%.2f", model.getBookKeeping().getCost());
            String revenue = String.format("$%.2f", model.getBookKeeping().getRevenue());
            String profit = String.format("$%.2f", model.getBookKeeping().getProfit());

            String addMessage = "Total costs: " + cost + ", Total revenue: "
                    + revenue + ", Total profit: " + profit;

            String finalMessage = MESSAGE_SUCCESS_ALL + "\n" + addMessage;

            model.updateFilteredDisplayList(DISPLAY_TRANSACTION_LIST, PREDICATE_SHOW_ALL_ITEMS);
            return new CommandResult(finalMessage);
        }

        // Else, display specific transaction
        Double totalCost = model.openTransaction(transactionId.get());
        if (totalCost == -1.0) {
            // Transaction id not found
            throw new CommandException(
                    String.format(MESSAGE_TXN_NOT_FOUND, transactionId.get())
            );
        }

        String addMessage = transactionId.get() + ", total cost: " + String.format("%.2f", totalCost);

        return new CommandResult(String.format(MESSAGE_SUCCESS, addMessage));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListTransactionCommand) // instanceof handles nulls
                && transactionId.equals(((ListTransactionCommand) other).transactionId); // state check
    }

}
