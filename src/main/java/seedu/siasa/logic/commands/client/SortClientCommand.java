package seedu.siasa.logic.commands.client;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.siasa.logic.commands.Command;
import seedu.siasa.logic.commands.CommandResult;
import seedu.siasa.model.Model;
import seedu.siasa.model.person.Person;

/**
 * Lists all persons in the address book to the user.
 */
public class SortClientCommand extends Command {

    public static final String COMMAND_WORD = "sortclient";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the client list alphabetically by the order specified.\n"
            + "Parameters: ORDER (asc, dsc)\n"
            + "Example: " + COMMAND_WORD + " dsc";

    public static final String MESSAGE_SUCCESS = "Sorted clients";

    private final Comparator<Person> comparator;

    public SortClientCommand(Comparator<Person> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
