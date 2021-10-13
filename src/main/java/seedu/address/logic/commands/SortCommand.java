package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Sorts all persons in address book whose according to the specified attribute in either ascending or descending
 * order.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts leads according to "
            + "the specified attribute and in either an ascending or descending order\n"
            + "Parameters: <attribute>/{ASC/DESC}\n"
            + "Example: " + COMMAND_WORD + " ra/ asc";

    private final Comparator<Person> sorter;

    private final String field;

    /**
     * @param sorter to sort the persons list with.
     * @param field which is the field that the list is sorted by.
     */
    public SortCommand(Comparator<Person> sorter, String field) {
        this.sorter = sorter;
        this.field = field;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredPersonList(sorter);
        return new CommandResult(String.format(Messages.MESSAGE_SORT_SUCCESS, field));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && sorter.equals(((SortCommand) other).sorter)
                && field.equals(((SortCommand) other).field)); // state check
    }
}
