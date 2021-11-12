package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NO_PARAMS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;


/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_ACTION = "List All Students";

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Displayed list with %1$d students!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the list of all students.\n" + MESSAGE_NO_PARAMS;

    @Override
    public CommandResult execute() {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getFilteredPersonList().size()));
    }
}
