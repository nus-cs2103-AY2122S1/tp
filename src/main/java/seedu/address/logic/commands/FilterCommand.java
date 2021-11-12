package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.comparators.PersonComparator;
import seedu.address.logic.comparators.exceptions.ComparatorException;
import seedu.address.model.Model;
import seedu.address.model.category.Category;
import seedu.address.model.person.Person;

/**
 * Filters the displayed list in the GUI of CallMeMaybe
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters the displayed list of people "
            + "by the category given by the user. "
            + "List will be shown in ascending alphabetical order.\n"
            + "Parameters: CATEGORY "
            + "COUNT (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " Called 5";

    public static final String MESSAGE_SUCCESS = "Filtered by: %1$s";

    private final Integer count;
    private final Category category;

    /**
     * @param category of the person to be updated to
     * @param count of the person in the filtered person list to edit the remark
     */
    public FilterCommand(Category category, Integer count) {
        requireAllNonNull(category, count);

        this.category = category;
        this.count = count;
    }

    @Override
    public CommandResult execute(Model model) throws ComparatorException, CommandException {
        requireNonNull(model);

        // Get Comparator based on category
        Comparator<Person> comparator;
        try {
            comparator = PersonComparator.getComparator(category);
        } catch (ComparatorException e) {
            throw new ComparatorException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, e.getMessage() + FilterCommand.MESSAGE_USAGE));
        }
        assert(comparator != null);
        model.sortFilteredPersonList(comparator);

        // Limit displayed Persons using count
        model.limitFilteredPersonList(count);

        DisplayCommand displayCommand = new DisplayCommand(Index.fromOneBased(1));
        displayCommand.execute(model);

        return new CommandResult(String.format(MESSAGE_SUCCESS, category));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        // state check
        FilterCommand e = (FilterCommand) other;
        return category.equals(e.category)
                && count.equals(e.count);
    }
}
