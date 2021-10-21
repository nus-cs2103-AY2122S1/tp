package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.done.Done;
import seedu.address.model.person.Person;


/**
 * Deletes all applicants in the address book that are marked as done.
 */
public class DeleteMarkedCommand extends Command {

    public static final String COMMAND_WORD = "delete_done";

    public static final String MESSAGE_SUCCESS = "Deleted all applicants that were marked as done";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        //TODO: MIGHT NEED CHANGE IF IMPLEMENT ISDONE METHOD IN PERSON CLASS
        model.updateFilteredPersonList((person) -> person.getDone().getDoneStatus().equals(Done.STATUS_DONE));
        List<Person> doneList = model.getFilteredPersonList();

        while (!doneList.isEmpty()) {
            model.deletePerson(doneList.get(0));
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
