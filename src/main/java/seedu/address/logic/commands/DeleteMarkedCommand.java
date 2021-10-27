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

    public static final String COMMAND_WORD = "delete_marked";

    public static final String MESSAGE_SUCCESS = "Deleted all applicants that were marked as done: \n%1$s";

    public static final String MESSAGE_NONE_DELETED = "\nNO APPLICANTS WERE MARKED AS DONE; NONE DELETED";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredPersonList((person) -> person.getDone().getDoneStatus().equals(Done.STATUS_DONE));
        List<Person> doneList = model.getFilteredPersonList();

        StringBuilder result = new StringBuilder();

        while (!doneList.isEmpty()) {
            Person personToDelete = doneList.get(0);
            result.append(personToDelete);
            model.deletePerson(personToDelete);
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS,
                result.toString().isEmpty() ? MESSAGE_NONE_DELETED : result));
    }
}
