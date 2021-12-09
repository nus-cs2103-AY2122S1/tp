package seedu.times.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.times.model.Model;
import seedu.times.model.TimesTable;

/**
 * Clears the Timestable.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "TimesTable has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTimesTable(new TimesTable());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
