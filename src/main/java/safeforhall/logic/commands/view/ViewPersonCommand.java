package safeforhall.logic.commands.view;

import static java.util.Objects.requireNonNull;
import static safeforhall.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import safeforhall.commons.core.Messages;
import safeforhall.commons.core.index.Index;
import safeforhall.logic.commands.Command;
import safeforhall.logic.commands.CommandResult;
import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.model.Model;
import safeforhall.model.person.Person;

/**
 * Lists all persons in the address book to the user.
 */
public class ViewPersonCommand extends Command {

    public static final String COMMAND_WORD = "view";
    public static final String PARAMETERS = "[INDEX]";
    public static final String MESSAGE_ALL_RESIDENTS_SHOWN = "All residents shown";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views the additional details of the "
            + "identified resident by the index numbers used in the displayed resident list, "
            + "or views all residents when the index is not specified. "
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_VIEW_RESIDENT_SUCCESS = "Resident details displayed in sidebar";

    private Index targetIndex;

    public ViewPersonCommand() {
    }

    /**
     * @param targetIndex Index of Resident in the filtered resident list to edit
     */
    public ViewPersonCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (targetIndex == null) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            model.setNoPerson();
            model.updateSortedPersonList(null);
            return new CommandResult(MESSAGE_ALL_RESIDENTS_SHOWN);
        } else {
            setSinglePerson(model);
            return new CommandResult(MESSAGE_VIEW_RESIDENT_SUCCESS);
        }
    }

    public void setSinglePerson(Model model) throws CommandException {
        List<Person> filteredPersonList = model.getFilteredPersonList();
        if (targetIndex.getZeroBased() >= filteredPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        model.setSinglePerson(filteredPersonList.get(targetIndex.getZeroBased()));
    }
}
