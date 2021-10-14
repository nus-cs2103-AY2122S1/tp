package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

public class DeleteTaskCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Removed %1$s task from %2$s";
    public static final String COMMAND_WORD = "deletetask";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task, specified by the TASKINDEX, from person"
            + "identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + PREFIX_TASK_INDEX + "TaskIndex\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_TASK_INDEX + "2";

    public static final String DESCRIPTION = "Deletes the task, specified by the TASK_INDEX, "
            + "from person specified by the INDEX";

    private final Index targetPersonIndex;
    private final Index targetTaskIndex;

    /**
     * Constructor for a DeleteTaskCommand to delete a task from a person.
     * @param targetPersonIndex The Index of the target person.
     * @param targetTaskIndex The Index of the target Task that belongs to target person.
     */
    public DeleteTaskCommand(Index targetPersonIndex, Index targetTaskIndex) {
        requireNonNull(targetPersonIndex);
        requireNonNull(targetTaskIndex);
        this.targetPersonIndex = targetPersonIndex;
        this.targetTaskIndex = targetTaskIndex;
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
        List<Task> tasks = new ArrayList<>();
        tasks.addAll(personToEdit.getTasks());

        if (targetTaskIndex.getZeroBased() >= tasks.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_TASK, personToEdit.getName()));
        }

        Task taskToRemove = tasks.get(targetTaskIndex.getZeroBased());
        tasks.remove(targetTaskIndex.getZeroBased());

        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(), tasks);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson, taskToRemove));
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
    private String generateSuccessMessage(Person personToEdit, Task taskRemoved) {
        return String.format(MESSAGE_SUCCESS, taskRemoved.getTaskName(), personToEdit.getName());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTaskCommand // instanceof handles nulls
                && targetPersonIndex.equals(((DeleteTaskCommand) other).targetPersonIndex)
                && targetTaskIndex.equals(((DeleteTaskCommand) other).targetTaskIndex)); // state check
    }
}
