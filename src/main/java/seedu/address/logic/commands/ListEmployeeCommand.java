package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;

import seedu.address.logic.commands.util.CommandUtil;
import seedu.address.model.Model;

/**
 * Switches to the employee view in the application.
 */
public class ListEmployeeCommand extends Command {

    public static final String COMMAND_WORD = "liste";

    public static final String MESSAGE_USAGE = CommandUtil.formatCommandWord(COMMAND_WORD)
            + ": Switches to employee view and shows all employees.\n"
            + "Example: " + CommandUtil.formatCommandWord(COMMAND_WORD);

    public static final String SHOWING_SWITCH_MESSAGE = "Switched to Employee View.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
        return new CommandResult(SHOWING_SWITCH_MESSAGE, false, false, false,
                true, false, false);
    }
}

