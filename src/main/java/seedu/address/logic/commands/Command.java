package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

    /**s
     * Retrieves the appropriate task list to modify depending on whether the user is viewing all
     * person's task list or a specified person's task list.
     * @throws CommandException If the user is not viewing any task list at all.
     */
    protected List<Task> getTaskListToModify(Model model, Person personToEdit) throws CommandException {
        List<Task> taskListToModify = null;
        if (model.getIsViewAllTasks()) {
            for (Person person : model.getViewAllTaskListPersons()) {
                if ((person.getName()).equals(personToEdit.getName())) {
                    taskListToModify = person.getTasks();
                    break;
                }
            }
        } else {
            checkPersonToEditTasksDisplayed(model, personToEdit);
            taskListToModify = model.getDisplayTaskList();
        }
        return taskListToModify;
    }

    /**
     * Checks if the person whose task(s) is selected for modification has their task list displayed
     * on the task list panel.
     */
    protected void checkPersonToEditTasksDisplayed(Model model, Person personToEdit) throws CommandException {
        boolean isPersonToEditTaskDisplayed = personToEdit.getName()
                .equals(model.getTaskListManager().getNameOfChosenPerson());
        if (!isPersonToEditTaskDisplayed && !model.getIsViewAllTasks()) {
            throw new CommandException(Messages.MESSAGE_PERSON_TO_EDIT_TASK_NOT_DISPLAYED);
        }
    }
}
