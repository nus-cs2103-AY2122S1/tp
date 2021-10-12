package seedu.address.logic.commands.tasks;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.id.UniqueId;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

public class AssignTaskToPersonCommand extends Command {

    public static final String COMMAND_WORD = "-ass";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assigns a task to the person identified "
            + "by the index numbers used in the displayed person list and the displayed task list. "
            + "Parameters: STUDENT INDEX (must be a positive integer) "
            + "TASK INDEX (must be a positive integer) ";

    public static final String OVERLAPPING_TASK = "Task is already assigned to this student!";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";
    public static final String MESSAGE_SUCCESS = "Task %1$s assigned to student %2$s";

    private final Index personIndex;
    private final Index taskIndex;

    /**
     * Constructs a {@code AddLessonToPersonCommand}
     *
     * @param personIndex of the person in the filtered person list to assign to
     * @param taskIndex of the task in the filtered task list to assign
     */
    public AssignTaskToPersonCommand(Index personIndex, Index taskIndex) {
        requireNonNull(personIndex);
        requireNonNull(taskIndex);

        this.personIndex = personIndex;
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();
        List<Task> lastShownTaskList = model.getFilteredTaskList();

        if (personIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (taskIndex.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownPersonList.get(personIndex.getZeroBased());
        Task taskToAssign = lastShownTaskList.get(taskIndex.getZeroBased());
        Set<UniqueId> previousAssignedTaskSet = personToEdit.getAssignedTaskIds();
        UniqueId taskId = taskToAssign.getId();

        if (previousAssignedTaskSet.contains(taskId)) {
            throw new CommandException(OVERLAPPING_TASK);
        }
        Set<UniqueId> newAssignedTaskSet = new HashSet<>(previousAssignedTaskSet);
        newAssignedTaskSet.add(taskId);
        Person newPerson = personToEdit.updateAssignedTaskIds(newAssignedTaskSet);

        if (!personToEdit.isSamePerson(newPerson) && model.hasPerson(newPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, newPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, taskToAssign.getName(), newPerson.getName()));
    }

}
