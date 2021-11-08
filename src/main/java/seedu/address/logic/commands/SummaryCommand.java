package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Formats the summary of members and transactions related information
 * for display in text form.
 */
public class SummaryCommand extends Command {

    /**
     * Stands for summary command.
     */
    public static final String COMMAND_WORD = "summary";

    /**
     * Stands for the message of summary command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows summary for members and transactions.\n"
            + "Example: " + COMMAND_WORD;

    /**
     * Stands for the message of open summary window successfully.
     */
    public static final String SHOWING_SUMMARY_MESSAGE = "Opened summary window.";

    /**
     * Overrides and executes model.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult related to summary command.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(SHOWING_SUMMARY_MESSAGE,
                false, false, false, true);
    }

    /**
     * Override the equals method.
     */
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        return (other instanceof SummaryCommand); // instanceof handles nulls
    }
}
