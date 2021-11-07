package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_VENUE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;


/**
 * Adds tasks to a person in the address book
 */
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_SUCCESS = "Added %1$d %2$s to Person: %3$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add a task to the task list of the person identified "
            + "by the index number used in the last person listing.\n"
            + "Parameters: INDEX (must be a positive integer less than or equal to " + Integer.MAX_VALUE + ")\n"
            + PREFIX_TASK_DESCRIPTION + " TASK_NAME "
            + "[" + PREFIX_TASK_DATE + " TASK_DATE] "
            + "[" + PREFIX_TASK_TIME + " TASK_TIME] "
            + "[" + PREFIX_TASK_VENUE + " TASK_VENUE] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TASK_DESCRIPTION + " Meeting "
            + PREFIX_TASK_DATE + " 2022-01-01 "
            + PREFIX_TASK_TIME + " 13:29 "
            + PREFIX_TASK_VENUE + " Board Room";

    public static final String DESCRIPTION = "Add to the task list of the person specified by INDEX";

    private final Index index;
    private final List<Task> newTasks;

    /**
     * Constructor for an AddTaskCommand to add a new task to a person.
     *
     * @param index The {@code Index} of the person in the filtered person list whose task list is to be edited.
     * @param newTasks A {@code List<Task>} to add to the person's task list.
     */
    public AddTaskCommand(Index index, List<Task> newTasks) {
        requireAllNonNull(index, newTasks);
        this.index = index;
        this.newTasks = newTasks;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        //Make new copy for defensive programming.
        List<Task> tasks = new ArrayList<>();
        tasks.addAll(personToEdit.getTasks());
        tasks.addAll(newTasks);
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(), tasks, personToEdit.getDescription(),
                personToEdit.isImportant()
        );

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        CommandResult commandResult = new CommandResult(generateSuccessMessage(editedPerson));
        commandResult.setWriteCommand();
        return commandResult;
    }

    /**
     * Generates a command execution success message based on the number
     * of tasks given and person name.
     */
    private String generateSuccessMessage(Person personToEdit) {
        int size = newTasks.size();
        String taskOrTasks = StringUtil.singularOrPlural("task", size);
        return String.format(MESSAGE_SUCCESS, size, taskOrTasks, personToEdit.getName());
    }

    @Override
    public boolean equals(Object other) {
        //Return true if same object
        if (other == this) {
            return true;
        }

        //False if not instance of RemarkCommand (including null)
        if (!(other instanceof AddTaskCommand)) {
            return false;
        }

        AddTaskCommand e = (AddTaskCommand) other;
        return index.equals(e.index)
                && newTasks.equals(e.newTasks);
    }

    public String getCommand() {
        return COMMAND_WORD;
    }

    public String getDescription() {
        return DESCRIPTION;
    }
}
