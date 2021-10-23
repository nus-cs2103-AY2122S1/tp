package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class ViewTaskListCommand extends Command {
    public static final String COMMAND_WORD = "cat";

    public static final String DESCRIPTION = "Displays the task list of a person specified by index number."
            + "used in the displayed person list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": " + DESCRIPTION + "\n"
            + "Parameters: "
            + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 2";

    public static final String MESSAGE_VIEW_TASKS_SUCCESS = "Viewing %1$s's tasks";

    public static final String MESSAGE_VIEW_TASKS_ALL_SUCCESS = "Viewing all task list.";

    private final Index targetIndex;

    private final boolean isDisplayAll;

    /** Constructor used if user wants to view all task lists. */
    public ViewTaskListCommand() {
        isDisplayAll = true;
        targetIndex = Index.fromOneBased(1);
    }

    /** Constructor used if user wants to view a specific {@code Person}'s task list . */
    public ViewTaskListCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        isDisplayAll = false;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (isDisplayAll) {
            CommandResult cr = new CommandResult(MESSAGE_VIEW_TASKS_ALL_SUCCESS);
            cr.setDisplayAllTaskList();
            return cr;
        }

        List<Person> lastShownList = model.getFilteredPersonList();

        if ((targetIndex.getZeroBased() >= lastShownList.size())) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToView = lastShownList.get(targetIndex.getZeroBased());

        model.displayPersonTaskList(personToView);

        CommandResult commandResult = new CommandResult(
                String.format(MESSAGE_VIEW_TASKS_SUCCESS, personToView.getName()));
        commandResult.setDisplaySingleTaskList();

        return commandResult;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewTaskListCommand // instanceof handles nulls
                && targetIndex.equals(((ViewTaskListCommand) other).targetIndex)); // state check
    }

    public String getCommand() {
        return COMMAND_WORD;
    }

    public String getDescription() {
        return DESCRIPTION;
    }
}
