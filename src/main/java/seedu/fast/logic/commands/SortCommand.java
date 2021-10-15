package seedu.fast.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.fast.model.Model;
import seedu.fast.model.person.Person;

/**
 * Lists all persons in the FAST to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD

            + ": Sorts the people in FAST by the given keyword \n"
            + "Keyword: 'name', 'appointment', 'priority' \n"
            + "Example: " + COMMAND_WORD + " name";

    public static final String MESSAGE_SUCCESS = "Sorted all persons by ";

    private final Comparator<Person> comparator;
    private final String keyword;

    /**
     * Constructor for SortCommand
     *
     * @param comparator Custom comparator that is used to sort.
     * @param keyword Identifier of the sort.
     */
    public SortCommand(Comparator<Person> comparator, String keyword) {
        this.comparator = comparator;
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortPerson(comparator);
        return new CommandResult(MESSAGE_SUCCESS + keyword);
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
        return comparator.equals(e.comparator) && keyword.equals(e.keyword);
    }
}
