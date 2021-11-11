package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.order.OrderContainsKeywordsPredicate;

public class FindOrderCommand extends Command {

    public static final String COMMAND_WORD = "findorder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all orders whose "
            + "customer, label, date or sales id "
            + "contain any of the space specified keywords (case-insensitive) and "
            + "displays them as a list with index numbers.\n"
            + "Keywords should be nonempty and consist only of alphanumeric characters\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " August John";


    private final OrderContainsKeywordsPredicate predicate;

    public FindOrderCommand(OrderContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredOrderList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ORDERS_LISTED_OVERVIEW, model.getFilteredOrderList().size()),
                CommandResult.DisplayState.ORDER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindOrderCommand // instanceof handles nulls
                && predicate.equals(((FindOrderCommand) other).predicate)); // state check
    }
}
