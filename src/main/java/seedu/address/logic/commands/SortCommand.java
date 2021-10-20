package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAST_VISIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT;

import java.util.Comparator;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

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

    public static final String MESSAGE_SUCCESS = "Sorted listed persons successfully";
    public static final String MESSAGE_INVALID_FLAG = "Flags are missing or invalid.";

    private final Comparator<Person> comparator;
    private final boolean isAscending;

    /**
     * @param comparator which is used for sorting
     * @param isAscending true if the sort is of ascending order
     */
    public SortCommand(Comparator<Person> comparator, boolean isAscending) {
        this.comparator = comparator;
        this.isAscending = isAscending;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredPersonList(comparator, isAscending);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
