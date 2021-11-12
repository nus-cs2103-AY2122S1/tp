package seedu.address.logic.commands.tasks;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TaskAssignable;
import seedu.address.model.ViewingType;
import seedu.address.model.person.Person;

public class AssignTaskToPersonCommand extends AssignTaskCommand {

    public static final String COMMAND_WORD = "-as";

    public static final String MESSAGE_USAGE = String.format(
            AssignTaskCommand.MESSAGE_USAGE, COMMAND_WORD, "student", "STUDENT");

    /**
     * Constructs a {@code AssignTaskToPersonCommand}
     *
     * @param personIndex of the person in the filtered person list to assign to
     * @param taskIndex of the task in the filtered task list to assign
     */
    public AssignTaskToPersonCommand(Index personIndex, Index taskIndex) {
        super(personIndex, taskIndex);
    }

    @Override
    protected String invalidDisplayedIndexMessage() {
        return Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
    }

    @Override
    protected List<Person> getTaskAssignableListFromModel(Model model) {
        return model.getFilteredPersonList();
    }

    @Override
    protected void updateModel(Model model, TaskAssignable taskAssignableToEdit, TaskAssignable
            newTaskAssignable) {
        requireAllNonNull(model, taskAssignableToEdit, newTaskAssignable);

        if (!(taskAssignableToEdit instanceof Person && newTaskAssignable instanceof Person)) {
            return;
        }
        Person personToEdit = (Person) taskAssignableToEdit;
        Person newPerson = (Person) newTaskAssignable;

        model.setPerson(personToEdit, newPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        model.setPersonToView(newPerson);
        model.setViewingType(ViewingType.PERSON);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return executeWithGivenMessage(model, "student");
    }
}
