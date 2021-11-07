package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

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
 * Marks task(s) identified using its displayed index in the task list as not done.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undotask";
    public static final String MESSAGE_SUCCESS = "Marked %1$d %2$s of %3$s as not done.";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task, specified by the TASKINDEX, from person "
            + "identified by the index number used in the displayed person list as not done.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TASK_INDEX + " TaskIndex (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_TASK_INDEX + " 2";

    public static final String DESCRIPTION = "Marks the task(s), specified by the TASK_INDEX, "
            + "from person specified by the INDEX as not done";

    private final Index targetPersonIndex;
    private final List<Index> targetTaskIndexes;

    /**
     * Constructor for a DoneCommand to mark a task of a person as done.
     *
     * @param targetPersonIndex The Index of the target person.
     * @param targetTaskIndexes The Index of the target Task that belongs to target person.
     */
    public UndoCommand(Index targetPersonIndex, List<Index> targetTaskIndexes) {
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

        //Make new copy for defensive programming.
        List<Task> tasks = new ArrayList<>(personToEdit.getTasks());
        List<Index> copyOfIndexList = new ArrayList<>(targetTaskIndexes);

        copyOfIndexList.sort(Collections.reverseOrder());

        for (Index targetTaskIndex : targetTaskIndexes) {
            if (targetTaskIndex.getZeroBased() >= tasks.size()) {
                throw new CommandException(String.format(Messages.MESSAGE_INVALID_TASK, personToEdit.getName()));
            }
        }

        int notDone = 0;
        for (Index targetTaskIndex : copyOfIndexList) {
            Task taskDone = tasks.get(targetTaskIndex.getZeroBased());
            if (taskDone.getDone()) {
                taskDone.setNotDone();
            } else {
                notDone++;
            }
        }

        Person editedPerson = new Person (
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(), tasks, personToEdit.getDescription(),
                personToEdit.getImportance()
        );

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson, targetTaskIndexes.size(), notDone));
    }

    public String getCommand() {
        return COMMAND_WORD;
    }

    public String getDescription() {
        return DESCRIPTION;
    }

    /**
     * Generates a command execution success message based on
     * the task removed.
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit, int amount, int notDone) {
        int tasksMarked = amount - notDone;
        String taskOrTasks = StringUtil.singularOrPlural("task", tasksMarked);
        String taskOrTasksNotMarked = StringUtil.singularOrPlural("task", notDone);
        String markedTasks = String.format(MESSAGE_SUCCESS, tasksMarked, taskOrTasks, personToEdit.getName());
        String notMarkedTasks = String.format("%1$d %2$s are already not done.", notDone, taskOrTasksNotMarked);
        return notDone == 0 ? markedTasks : markedTasks + "\n" + notMarkedTasks;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UndoCommand // instanceof handles nulls
                && targetPersonIndex.equals(((UndoCommand) other).targetPersonIndex)
                && targetTaskIndexes.equals(((UndoCommand) other).targetTaskIndexes)); // state check
    }

}
