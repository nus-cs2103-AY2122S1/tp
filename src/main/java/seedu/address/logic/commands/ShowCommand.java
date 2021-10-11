package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.function.Predicate;

import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Displays details of the the person in address book whose name matches the argument keyword or is at specified index.
 * Keyword matching is case insensitive.
 */
public class ShowCommand extends Command {

    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n1. Displays details of the person with given name, "
            + "the specified keywords (case-insensitive)\n"
            + "Parameters: KEYWORD\n"
            + "Example: " + COMMAND_WORD + " alice\n"
            + "2. Displays details of the person at the given index\n"
            + "Parameters : INDEX\n"
            + "Example: " + COMMAND_WORD + " 2";

    public static final String MESSAGE_NO_NAME = "No User with Given Name could be found in the List";

    public static final String MESSAGE_SUCCESS = "Showing details for %1$s.";

    public static final String MESSAGE_MULTIPLE_NAME = "%1$d Persons with the given keyword in their name exists.\n"
            + "You can try show with index or full name";

    private final Predicate<Person> predicate;
    private final Index index;

    /**
     * Constructor for Show Command when name is given
     *
     * @param predicate contains Persons with name matching keyword
     */
    public ShowCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
        this.index = null;
    }

    /**
     * Constructor for Show Command when index is given
     *
     * @param index of the Person to show
     */
    public ShowCommand(Index index) {
        this.predicate = null;
        this.index = index;
    }

    /**
     * Executes the show command if argument is a string name
     */
    private CommandResult executeWithName(Model model) throws CommandException {
        FilteredList<Person> filteredList = new FilteredList<>(model.getFilteredPersonList());
        filteredList.setPredicate(predicate);

        if (filteredList.isEmpty()) {
            filteredList = new FilteredList<>(model.getAddressBook().getPersonList());
            filteredList.setPredicate(predicate);
            if (filteredList.isEmpty()) {
                throw new CommandException(MESSAGE_NO_NAME);
            } else {
                model.updateFilteredPersonList(model.PREDICATE_SHOW_ALL_PERSONS);
                this.executeWithName(model);
            }
        }

        if (filteredList.size() == 1) {
            int index = model.getFilteredPersonList().indexOf(filteredList.get(0));
            model.setSelectedIndex(index);
            return new CommandResult(String.format(MESSAGE_SUCCESS,
                    model.getFilteredPersonList().get(index).getName()));
        } else {
            model.updateFilteredPersonList(predicate);
            return new CommandResult(String.format(MESSAGE_MULTIPLE_NAME, model.getFilteredPersonList().size()));
        }
    }

    /**
     * Executes the show command if argument is a numeric index
     */
    private CommandResult executeWithIndex(Model model) throws CommandException {
        assert this.index != null;
        int index = this.index.getZeroBased();
        if (index >= model.getFilteredPersonList().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        model.setSelectedIndex(index);
        return new CommandResult(String.format(MESSAGE_SUCCESS,
                model.getFilteredPersonList().get(index).getName()));
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (predicate != null) {
            return executeWithName(model);
        } else if (index != null) {
            return executeWithIndex(model);
        }
        return new CommandResult(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowCommand // instanceof handles nulls
                && ((predicate != null && predicate.equals(((ShowCommand) other).predicate))
                || (index != null && index.equals(((ShowCommand) other).index)))); // state check
    }
}
