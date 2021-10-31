package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;
import seedu.address.model.summary.Summary;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all contacts";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays all contacts in your Contact Book "
            + "\nParameters: No parameters";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        Summary summary = new Summary(model.getAddressBook());
        return new CommandResult(MESSAGE_SUCCESS, summary);
    }
}
