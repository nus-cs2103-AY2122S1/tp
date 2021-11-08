package seedu.address.logic.commands.tasks;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ViewingType;
import seedu.address.model.id.UniqueId;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

public class MarkTaskDoneStudentCommand extends Command {

    public static final String COMMAND_WORD = "-do";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks that a task has been done by a student. "
            + "The student and task are indicated"
            + "by the index numbers used in the displayed student list and the displayed task list. "
            + "Parameters: STUDENT INDEX (must be a positive integer) "
            + "TASK INDEX (must be a positive integer) ";
    public static final String MISSING_TASK = "Specified task is not assigned to this student!";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_SUCCESS = "Task %1$s marked as done by student %2$s";

    private final Index personIndex;
    private final Index taskIndex;

    /**
     * Constructs a {@code MarkTaskDoneStudentCommand}
     *
     * @param personIndex the index of the {@code Student} who did the task
     * @param taskIndex the index of the task in the filtered task list to mark as done
     */
    public MarkTaskDoneStudentCommand(Index personIndex, Index taskIndex) {
        requireAllNonNull(personIndex, taskIndex);

        this.personIndex = personIndex;
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();
        List<Task> taskList = model.getFilteredTaskList();

        if (personIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (taskIndex.getZeroBased() >= taskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownPersonList.get(personIndex.getZeroBased());
        Task taskAssigned = taskList.get(taskIndex.getZeroBased());
        Map<UniqueId, Boolean> previousTasksCompletion = personToEdit.getTasksCompletion();
        UniqueId taskId = taskAssigned.getId();

        if (!previousTasksCompletion.containsKey(taskId)) {
            throw new CommandException(MISSING_TASK);
        }

        Map<UniqueId, Boolean> newTasksCompletion = new HashMap<>(previousTasksCompletion);
        newTasksCompletion.replace(taskId, true);
        Person newPerson = personToEdit.updateTasksCompletion(newTasksCompletion);

        if (!personToEdit.isSamePerson(newPerson)
                && model.hasPerson(newPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, newPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        model.setPersonToView(newPerson);
        model.setViewingType(ViewingType.PERSON);

        return new CommandResult(String.format(MESSAGE_SUCCESS, taskAssigned.getDescription(), newPerson.getName()));
    }

}
