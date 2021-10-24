package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Displays the summary statistics of elderly and visits.
 */
public class SummaryCommand extends Command {

    public static final String COMMAND_WORD = "summary";

    public static final String MESSAGE_SUCCESS = "Summary displayed";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateStatistics();
        return new CommandResult(MESSAGE_SUCCESS, true, false, false, false);
    }
}
