package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": shows information of the client "
            + "by his/her client id. \n"
            + "Parameters: view {client id of the customer} \n"
            + "Example: " + COMMAND_WORD + " 1 ";

    // Results to be printed to the Result Display

    public static final String MESSAGE_VIEW_SUCCESS = "Viewing person: %1$s";
    public static final String MESSAGE_NON_EXISTENT_USER = "There's no user with user ID %d";
    // private final PersonDoesExistsPredicate predicate;

    private final Index index;

    /**
     * @param index of the person in the filtered person list to view
     */
    public ViewCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToView = lastShownList.get(index.getZeroBased());

        CommandResult commandResult = new CommandResult(String.format(MESSAGE_VIEW_SUCCESS, personToView.getName()));
        commandResult.setPersonToView(personToView);

        return commandResult;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewCommand)) {
            return false;
        }

        // state check
        ViewCommand e = (ViewCommand) other;
        return index.equals(e.index);
    }
}
