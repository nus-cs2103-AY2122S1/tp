package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAST_VISIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT;

import java.util.Comparator;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.LastVisit;
import seedu.address.model.person.Person;
import seedu.address.model.person.Visit;
import seedu.address.model.util.SortUtil;

/*
 * Sorts all person in the address book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the displayed elderly lists either by increasing order of next visit using flag " + PREFIX_VISIT
            + " or by decreasing order of last visit using flag " + PREFIX_LAST_VISIT + "\n"
            + "Parameters: [" + PREFIX_VISIT + "] or [" + PREFIX_LAST_VISIT + "]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_VISIT + " or " + COMMAND_WORD + " " + PREFIX_LAST_VISIT + "\n"
            + "Note: No additional input after the flag is allowed.";

    public static final String MESSAGE_SUCCESS = "Sorted listed elderly successfully by %1$s";

    private final Comparator<Person> comparator;
    private final boolean isAscending;

    /**
     * @param comparator Comparator used for sorting
     * @param isAscending True if the sort is of ascending order, false if descending order
     */
    public SortCommand(Comparator<Person> comparator, boolean isAscending) {
        this.comparator = comparator;
        this.isAscending = isAscending;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortFilteredPersonList(comparator, isAscending);

        if (comparator.equals(SortUtil.SORT_BY_NEXT_VISIT)) {
            return new CommandResult(String.format(MESSAGE_SUCCESS, Visit.class.getSimpleName()));

        } else if (comparator.equals(SortUtil.SORT_BY_LAST_VISIT)) {
            return new CommandResult(String.format(MESSAGE_SUCCESS, LastVisit.class.getSimpleName()));

        } else {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        // state check
        SortCommand e = (SortCommand) other;
        return comparator.equals(e.comparator)
                && isAscending == e.isAscending;
    }
}
