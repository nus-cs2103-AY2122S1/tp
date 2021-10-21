package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Comparator;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Sorts a person identified using it's displayed index from the address book.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the contacts by the person property in descending order.\n"
            + "Parameters: - \n"
            + "[" + PREFIX_NAME + "] "
            + "[" + PREFIX_GENDER + "] "
            + "[" + PREFIX_PHONE + "] "
            + "[" + PREFIX_EMAIL + "] "
            + "[" + PREFIX_NATIONALITY + "] "
            + "[" + PREFIX_TUTORIAL_GROUP + "] "
            + "[" + PREFIX_SOCIAL_HANDLE + "] "
            + "[" + PREFIX_REMARK + "] "
            + "[" + PREFIX_TAG + "]\n"
            + "Example: " + COMMAND_WORD + PREFIX_NAME;

    public static final String MESSAGE_SUCCESS = "Sorted all persons by %s in descending order";

    private final Comparator<Person> comparator;

    public SortCommand(Comparator<Person> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateSortedPersonList(comparator);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, comparator.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && comparator.equals(((SortCommand) other).comparator)); // state check
    }
}
