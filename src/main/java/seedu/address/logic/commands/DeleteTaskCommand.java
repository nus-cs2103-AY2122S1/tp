package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
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
 * Deletes the task(s) identified using its displayed index in the task list.
 */
public class DeleteTaskCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Removed %1$d %2$s from %3$s";
    public static final String COMMAND_WORD = "rm";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task, specified by the TASKINDEX, from person "
            + "identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer less than or equal to " + Integer.MAX_VALUE + ")\n"
            + PREFIX_TASK_INDEX + " TaskIndex (must be a positive integer less than or equal to " + Integer.MAX_VALUE
            + ")\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_TASK_INDEX + " 2";

    public static final String DESCRIPTION = "Deletes the task, specified by the TASK_INDEX, "
            + "from person specified by the INDEX";

    private final Index targetPersonIndex;
    private final List<Index> targetTaskIndexes;

    /**
     * Constructor for a DeleteTaskCommand to delete a task from a person.
     *
     * @param targetPersonIndex The {@code Index} of the target person.
     * @param targetTaskIndexes The {@code Index} of the target Task that belongs to target person.
     */
    public DeleteTaskCommand(Index targetPersonIndex, List<Index> targetTaskIndexes) {
        requireNonNull(targetPersonIndex);
        requireNonNull(targetTaskIndexes);
        this.targetPersonIndex = targetPersonIndex;
        this.targetTaskIndexes = targetTaskIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = getLastShownList(model);

        if (targetPersonIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = model.getFilteredPersonList().get(targetPersonIndex.getZeroBased());
        checkPersonToEditTasksDisplayed(model, personToEdit);

        //Make new copy for defensive programming.
        List<Task> tasks = new ArrayList<>(personToEdit.getTasks());
        List<Index> copyOfIndexList = new ArrayList<>(targetTaskIndexes);

        copyOfIndexList.sort(Collections.reverseOrder());

        List<Task> taskListToModify = getTaskListToModify(model);
        checkTargetIndexesValidity(personToEdit, taskListToModify);
        List<Task> tasksToRemove = extractTaskToRemove(copyOfIndexList, taskListToModify);
        removeSelectedTasks(tasks, tasksToRemove);

        Person editedPerson = createEditedPerson(personToEdit, tasks);
        model.setPerson(personToEdit, editedPerson);

        return generateWriteCommandResult(tasksToRemove, editedPerson);
    }

    private void checkTargetIndexesValidity(Person personToEdit, List<Task> taskListToModify)
            throws CommandException {
        for (Index targetTaskIndex : targetTaskIndexes) {
            if (targetTaskIndex.getZeroBased() >= taskListToModify.size()) {
                throw new CommandException(String.format(Messages.MESSAGE_INVALID_TASK, personToEdit.getName()));
            }
        }
    }

    /**
     * Checks if the person whose task(s) is selected for modification has their task list displayed
     * on the task list panel.
     */
    private void checkPersonToEditTasksDisplayed(Model model, Person personToEdit) throws CommandException {
        boolean isPersonToEditTaskDisplayed = personToEdit.getName()
                .equals(model.getTaskListManager().getNameOfChosenPerson());
        if (!isPersonToEditTaskDisplayed && !model.getIsViewAllTasks()) {
            throw new CommandException(Messages.MESSAGE_PERSON_TO_EDIT_TASK_NOT_DISPLAYED);
        }
    }


    private CommandResult generateWriteCommandResult(List<Task> tasksToRemove, Person editedPerson) {
        CommandResult commandResult = new CommandResult(generateSuccessMessage(editedPerson, tasksToRemove.size()));
        commandResult.setWriteCommand();
        return commandResult;
    }

    private Person createEditedPerson(Person personToEdit, List<Task> tasks) {
        return new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(), tasks, personToEdit.getDescription(),
                personToEdit.isImportant()
        );
    }

    /**
     * If every person's task list is being displayed, the person list used to facilitate
     * that is returned. Otherwise the person list used for display on the person list panel
     * is returned.
     */
    private List<Task> getTaskListToModify(Model model) {
        List<Task> taskListToModify;
        if (model.getIsViewAllTasks()) {
            taskListToModify = model.getViewAllTaskListPersons().get(targetPersonIndex.getZeroBased()).getTasks();
        } else {
            taskListToModify = model.getDisplayTaskList();
        }
        return taskListToModify;
    }

    private List<Task> extractTaskToRemove(List<Index> copyOfIndexList, List<Task> taskListToModify) {
        List<Task> tasksToRemove = new ArrayList<>();
        for (Index targetTaskIndex : copyOfIndexList) {
            Task taskToRemove = taskListToModify.get(targetTaskIndex.getZeroBased());
            tasksToRemove.add(taskToRemove);
        }
        return tasksToRemove;
    }

    private List<Person> getLastShownList(Model model) {
        List<Person> lastShownList;
        if (model.getIsViewAllTasks()) {
            lastShownList = model.getViewAllTaskListPersons();
        } else {
            lastShownList = model.getFilteredPersonList();
        }
        return lastShownList;
    }

    /**
     * Generates a command execution success message based on
     * the task removed.
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit, int amount) {
        String taskOrTasks = StringUtil.singularOrPlural("task", amount);
        return String.format(MESSAGE_SUCCESS, amount, taskOrTasks, personToEdit.getName());
    }

    private void removeSelectedTasks(List<Task> taskListToRemoveFrom, List<Task> tasksToRemove) {
        for (Task taskToRemove : tasksToRemove) {
            for (int i = 0; i < taskListToRemoveFrom.size(); i++) {
                if (taskToRemove == taskListToRemoveFrom.get(i)) {
                    taskListToRemoveFrom.remove(i);
                    break;
                }
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTaskCommand // instanceof handles nulls
                && targetPersonIndex.equals(((DeleteTaskCommand) other).targetPersonIndex)
                && targetTaskIndexes.equals(((DeleteTaskCommand) other).targetTaskIndexes)); // state check
    }

    public String getCommand() {
        return COMMAND_WORD;
    }

    public String getDescription() {
        return DESCRIPTION;
    }
}
