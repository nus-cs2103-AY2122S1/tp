package seedu.address.logic.commands;

import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

/**
 * Format full help instructions for every command for display.
 */
public class SummaryCommand extends Command {


    public static final String COMMAND_WORD = "sum";

    public static final String MESSAGE_SUCCESS = "Displaying Summary";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
