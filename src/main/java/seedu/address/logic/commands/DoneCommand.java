package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * Marks task(s) identified using its displayed index in the task list as done.
 */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "donetask";
    public static final String MESSAGE_SUCCESS = "Marked %1$d %2$s of %3$s as done.";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task, specified by the TASKINDEX, from person "
            + "identified by the index number used in the displayed person list as done.\n"
            + "Parameters: INDEX (must be a positive integer less than or equal to " + Integer.MAX_VALUE + ")\n"
            + PREFIX_TASK_INDEX + "TaskIndex (must be a positive integer less than or equal to " + Integer.MAX_VALUE
            + ")\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_TASK_INDEX + "2";

    public static final String DESCRIPTION = "Marks the task(s) from a specified person as done.";

    private final Index targetPersonIndex;
    private final List<Index> targetTaskIndexes;

    /**
     * Constructor for a DoneCommand to mark a task of a person as done.
     *
     * @param targetPersonIndex The {@code Index} of the target person.
     * @param targetTaskIndexes The {@code Index} of the target Task that belongs to target person.
     */
    public DoneCommand(Index targetPersonIndex, List<Index> targetTaskIndexes) {
        requireAllNonNull(targetPersonIndex, targetTaskIndexes);
        this.targetPersonIndex = targetPersonIndex;
        this.targetTaskIndexes = targetTaskIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetPersonIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(targetPersonIndex.getZeroBased());
        List<Task> taskListToModify = getTaskListToModify(model, personToEdit);
        assert taskListToModify != null : "view all task list functionality not implemented correctly!";

        //Make new copy for defensive programming.
        List<Task> tasks = new ArrayList<>(personToEdit.getTasks());
        List<Index> copyOfIndexList = new ArrayList<>(targetTaskIndexes);

        copyOfIndexList.sort(Collections.reverseOrder());

        checkTargetIndexesValidity(personToEdit, taskListToModify);
        List<Task> tasksToBeMarkedAsDone = extractTaskToBeMarkedAsDone(taskListToModify, copyOfIndexList);
        int alreadyDone = markTargetTasksAsDone(tasks, tasksToBeMarkedAsDone);
        Person editedPerson = createEditedPersonWithUpdatedTasks(personToEdit, tasks);

        model.setPerson(personToEdit, editedPerson);

        return generateWriteCommandResult(alreadyDone, editedPerson);
    }

    private CommandResult generateWriteCommandResult(int alreadyDone, Person editedPerson) {
        CommandResult commandResult = new CommandResult(
                generateSuccessMessage(editedPerson, targetTaskIndexes.size(), alreadyDone));
        commandResult.setWriteCommand();
        return commandResult;
    }

    private Person createEditedPersonWithUpdatedTasks(Person personToEdit, List<Task> tasks) {
        return new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(), tasks, personToEdit.getDescription(),
                personToEdit.getImportance());
    }

    private int markTargetTasksAsDone(List<Task> tasks, List<Task> taskToBeMarkedAsDone) {
        int alreadyDone = 0;
        for (Task taskTobeMarkedAsDone : taskToBeMarkedAsDone) {
            for (int i = 0; i < tasks.size(); i++) {
                if (!(tasks.get(i) == taskTobeMarkedAsDone)) {
                    continue;
                }
                if (tasks.get(i).getDone()) {
                    alreadyDone++;
                } else {
                    Task editedTask = new Task(tasks.get(i).getTaskName(),
                            tasks.get(i).getDate(), tasks.get(i).getTime(), tasks.get(i).getVenue());
                    editedTask.updateDueDate();
                    editedTask.setDone();
                    tasks.set(i, editedTask);
                }
            }
        }
        return alreadyDone;
    }

    private void checkTargetIndexesValidity(Person personToEdit, List<Task> tasks) throws CommandException {
        for (Index targetTaskIndex : targetTaskIndexes) {
            if (targetTaskIndex.getZeroBased() >= tasks.size()) {
                throw new CommandException(String.format(Messages.MESSAGE_INVALID_TASK, personToEdit.getName()));
            }
        }
    }

    private List<Task> extractTaskToBeMarkedAsDone(List<Task> taskList, List<Index> targetTaskIndexes) {
        List<Task> tasksToBeMarkedAsDone = new ArrayList<>();
        for (Index targetIndex : targetTaskIndexes) {
            tasksToBeMarkedAsDone.add(taskList.get(targetIndex.getZeroBased()));
        }
        return tasksToBeMarkedAsDone;
    }

    /**
     * Generates a command execution success message based on
     * the task removed.
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit, int amount, int alreadyDone) {
        int tasksMarked = amount - alreadyDone;
        String taskOrTasks = StringUtil.singularOrPlural("task", tasksMarked);
        String taskOrTasksNotMarked = StringUtil.singularOrPlural("task", alreadyDone);
        String markedTasks = String.format(MESSAGE_SUCCESS, tasksMarked, taskOrTasks, personToEdit.getName());
        String alreadyDoneTasks = String.format("%1$d %2$s are already done.", alreadyDone, taskOrTasksNotMarked);
        return alreadyDone == 0 ? markedTasks : markedTasks + "\n" + alreadyDoneTasks;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DoneCommand // instanceof handles nulls
                && targetPersonIndex.equals(((DoneCommand) other).targetPersonIndex)
                && targetTaskIndexes.equals(((DoneCommand) other).targetTaskIndexes)); // state check
    }

    public String getCommand() {
        return COMMAND_WORD;
    }

    public String getDescription() {
        return DESCRIPTION;
    }

}
