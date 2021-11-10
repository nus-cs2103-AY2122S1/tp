package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";


    /**
     * This method attempts to make a list from the existing contacts.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult which holds the outcome of this method.
     * @throws CommandException if there are any errors during execution.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (model.getPersonListControl() != null) {
            model.setTabIndex(0);
        }
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
