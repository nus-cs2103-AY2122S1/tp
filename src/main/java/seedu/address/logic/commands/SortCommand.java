package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NATIONALITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;
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

    public static final String MESSAGE_USAGE = "\n" + COMMAND_WORD
            + ": Sorts all contacts by a specified property in ascending order.\n\n"
            + "Parameter: [FIELD_PREFIX/]\n"
            + "FIELD_PREFIX/ can be any of the following:\n"
            + "[" + PREFIX_NAME + "] "
            + "[" + PREFIX_GENDER + "] "
            + "[" + PREFIX_PHONE + "] "
            + "[" + PREFIX_EMAIL + "] "
            + "[" + PREFIX_NATIONALITY + "] "
            + "[" + PREFIX_TUTORIAL_GROUP + "] "
            + "[" + PREFIX_REMARK + "] "
            + "\n\n"
            + "Example: " + COMMAND_WORD + " n/";

    public static final String MESSAGE_SUCCESS = "Sorted all persons by %s in ascending order";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT_PREFIX_ABSENT = String.format(
            MESSAGE_INVALID_COMMAND_FORMAT, "No Prefix was entered. \n%1$s");

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
