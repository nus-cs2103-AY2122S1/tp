package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.summary.Summary;


/**
 * Format full help instructions for every command for display.
 */
public class SummaryCommand extends Command {


    public static final String COMMAND_WORD = "sum";

    public static final String MESSAGE_SUCCESS = "Displaying Summary.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays a summary of your Contact Book "
            + "\nParameters: No parameters";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Summary summary = new Summary(model.getAddressBook());
        return new CommandResult(String.format(MESSAGE_SUCCESS, summary), summary);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || other instanceof SummaryCommand; // instanceof handles nulls
    }
}
