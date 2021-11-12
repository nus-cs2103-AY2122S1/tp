package seedu.address.logic.commands.tasks;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ViewingType;
import seedu.address.model.group.Group;
import seedu.address.model.id.UniqueId;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

public class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "-d";

    public static final String MESSAGE_USAGE = "task " + COMMAND_WORD
            + ": Deletes the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + "task " + COMMAND_WORD + " 1";
    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task: %1$s";

    private final Index targetIndex;

    public DeleteTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownTaskList = model.getFilteredTaskList();
        ReadOnlyAddressBook addressBook = model.getAddressBook();
        List<Person> allPersonList = addressBook.getPersonList();
        List<Group> allGroupList = addressBook.getGroupList();

        if (targetIndex.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToDelete = lastShownTaskList.get(targetIndex.getZeroBased());
        UniqueId taskId = taskToDelete.getId();

        // Iterate through all persons in the model to find and unassign task
        allPersonList.forEach((person) -> {
            Set<UniqueId> previousAssignedTaskIds = person.getAssignedTaskIds();
            Set<UniqueId> newAssignedTaskIds = new HashSet<>(previousAssignedTaskIds);
            Map<UniqueId, Boolean> previousTaskCompletion = person.getTasksCompletion();
            Map<UniqueId, Boolean> newTaskCompletion = new HashMap<>(previousTaskCompletion);

            if (previousAssignedTaskIds.contains(taskId)) {
                newAssignedTaskIds.remove(taskId);
                newTaskCompletion.remove(taskId);
            }

            Person newPerson = person.updateAssignedTaskIds(newAssignedTaskIds);
            newPerson = newPerson.updateTasksCompletion(newTaskCompletion);
            model.setPerson(person, newPerson);
        });

        // Iterate through all groups in the model to find and unassign task
        allGroupList.forEach((group) -> {
            Set<UniqueId> previousAssignedTaskIds = group.getAssignedTaskIds();
            Set<UniqueId> newAssignedTaskIds = new HashSet<>(previousAssignedTaskIds);

            if (previousAssignedTaskIds.contains(taskId)) {
                newAssignedTaskIds.remove(taskId);
            }

            Group newGroup = group.updateAssignedTaskIds(newAssignedTaskIds);
            model.setGroup(group, newGroup);
        });

        model.deleteTask(taskToDelete);
        model.updateLessonWithAttendeesList();
        model.setViewingType(ViewingType.SCHEDULE);
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTaskCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTaskCommand) other).targetIndex)); // state check
    }
}
