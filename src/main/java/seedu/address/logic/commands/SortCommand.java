package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

/**
 * Sorts all persons in address book whose according to the specified attribute in either ascending or descending
 * order.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts leads according to "
            + "the specified attribute and in either an ascending or descending order\n"
            + "Parameters: <attribute>/{ASC/DESC}\n"
            + "Example: " + COMMAND_WORD + " ra/ ASC";

    public String MESSAGE_SUCCESS;

    private final Comparator<Person> sorter;

    private final String field;

    public SortCommand(Comparator<Person> sorter, String field) {
        this.sorter = sorter;
        this.field = field;
        this.MESSAGE_SUCCESS = "list sorted by " + field;

    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredPersonList(sorter);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof SortCommand; // instanceof handles nulls
                //&& predicate.equals(((SortCommand) other).predicate)); // state check
    }
}
