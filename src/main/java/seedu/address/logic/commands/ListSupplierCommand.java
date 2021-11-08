package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SUPPLIERS;

import seedu.address.logic.commands.util.CommandUtil;
import seedu.address.model.Model;

/**
 * Switches to the supplier view in the application.
 */
public class ListSupplierCommand extends Command {

    public static final String COMMAND_WORD = "lists";

    public static final String MESSAGE_USAGE = CommandUtil.formatCommandWord(COMMAND_WORD)
            + ": Switches to supplier view and shows all suppliers.\n"
            + "Example: " + CommandUtil.formatCommandWord(COMMAND_WORD);

    public static final String SHOWING_SWITCH_MESSAGE = "Switched to Supplier View.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredSupplierList(PREDICATE_SHOW_ALL_SUPPLIERS);
        return new CommandResult(SHOWING_SWITCH_MESSAGE, false, false, false, false, true, false);
    }
}

