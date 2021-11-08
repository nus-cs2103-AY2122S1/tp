package seedu.awe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.core.Messages.MESSAGE_DELETECONTACTCOMMAND_CANNOT_BE_DELETED;
import static seedu.awe.commons.core.Messages.MESSAGE_DELETECONTACTCOMMAND_DELETE_PERSON_SUCCESS;

import java.util.List;

import seedu.awe.commons.core.Messages;
import seedu.awe.commons.core.index.Index;
import seedu.awe.logic.commands.exceptions.CommandException;
import seedu.awe.model.Model;
import seedu.awe.model.person.Person;
import seedu.awe.ui.MainWindow;
import seedu.awe.ui.UiView;

/**
 * Deletes a person identified using it's displayed index from the awe book.
 */
public class DeleteContactCommand extends Command {

    public static final String COMMAND_WORD = "deletecontact";

    private final Index targetIndex;

    public DeleteContactCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (MainWindow.getViewEnum() != UiView.CONTACT_PAGE) {
            throw new CommandException(MESSAGE_DELETECONTACTCOMMAND_CANNOT_BE_DELETED);
        }

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(personToDelete);

        return new CommandResult(String.format(MESSAGE_DELETECONTACTCOMMAND_DELETE_PERSON_SUCCESS, personToDelete),
                false, false, false, true, false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteContactCommand)) { // instanceof handles nulls
            return false;
        }
        return targetIndex.equals(((DeleteContactCommand) other).targetIndex); // state check
    }
}
