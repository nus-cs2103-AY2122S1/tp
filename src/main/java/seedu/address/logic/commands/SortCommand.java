package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAST_VISIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT;

import seedu.address.model.Model;

/*
 * Sorts all persons in the address book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the displayed person lists either by increasing order of next visit using flag " + PREFIX_VISIT
            + " or by decreasing order of last visit using flag " + PREFIX_LAST_VISIT
            + "Parameters: [" + PREFIX_VISIT + "] or [" + PREFIX_LAST_VISIT + "]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_VISIT + " or " + COMMAND_WORD + " " + PREFIX_LAST_VISIT;

    public static final String MESSAGE_SUCCESS = "Sorted all persons";
    public static final String MESSAGE_INVALID_FLAG = "Flags are missing or invalid.";

    private final boolean isLastVisit;

    /**
     * @param isLastVisit whether it is to sort by last visit.
     */
    public SortCommand(boolean isLastVisit) {
        this.isLastVisit = isLastVisit;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (isLastVisit) {
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            model.sortByNextVisitList();
            return new CommandResult(MESSAGE_SUCCESS);
        }
    }
}
