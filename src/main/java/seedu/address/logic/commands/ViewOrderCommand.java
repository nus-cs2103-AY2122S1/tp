package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.DisplayMode.DISPLAY_OPEN_ORDER;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;

/**
 * Lists all items in the current order to the user.
 */
public class ViewOrderCommand extends Command {

    public static final String COMMAND_WORD = "vieworder";

    public static final String MESSAGE_SUCCESS = "Listed all items in current order";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": lists all the items in the current order. ";
    public static final String MESSAGE_NO_UNCLOSED_ORDER = "Please use `sorder` to enter ordering mode first.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasUnclosedOrder()) {
            throw new CommandException(MESSAGE_NO_UNCLOSED_ORDER);
        }

        model.updateFilteredItemList(DISPLAY_OPEN_ORDER, PREDICATE_SHOW_ALL_ITEMS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
