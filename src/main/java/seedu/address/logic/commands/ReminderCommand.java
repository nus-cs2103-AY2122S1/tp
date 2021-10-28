package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Shows/Sets the number of days before a task's date and time for the task to be
 * reminded as due soon.
 */
public class ReminderCommand extends Command {
    public static final String COMMAND_WORD = "reminder";
    public static final String SET_FLAG = " -s";
    public static final String NO_FLAG_DESCRIPTION = "Shows the number of days before a task's"
            + " date and time\nfor the task to be reminded as due soon.";
    public static final String HAS_FLAG_DESCRIPTION = "Sets the number of days before a task's"
            + " date and time\nfor the task to be reminded as due soon.\n"
            + "Parameters: DAYS (must be a positive integer)\n"
            + "Example: reminder -s 21";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": " + NO_FLAG_DESCRIPTION + "\n"
            + COMMAND_WORD + SET_FLAG + ": " + HAS_FLAG_DESCRIPTION;
    public static final String MESSAGE_SET_REMINDER_SUCCESS = "Days in advance to remind is set to %d";
    public static final String MESSAGE_SHOW_REMINDER_SUCCESS = "Days in advance to remind: %d";

    private final boolean isSet;
    private int reminderDays;

    /**
     * Constructor to use if "-s" flag is not provided.
     */
    public ReminderCommand() {
        this.isSet = false;
    }

    /**
     * Constructor to use if "-s" flag is provided.
     */
    public ReminderCommand(int reminderDays) {
        this.isSet = true;
        this.reminderDays = reminderDays;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (isSet) {
            if (reminderDays <= 0) {
                throw new CommandException(Messages.MESSAGE_INVALID_REMINDER_DAY_VALUE);
            }
            Task.setReminderDays(reminderDays);
            return new CommandResult(String.format(MESSAGE_SET_REMINDER_SUCCESS, reminderDays));
        } else {
            int reminderDays = Task.getReminderDays();
            return new CommandResult(String.format(MESSAGE_SHOW_REMINDER_SUCCESS, reminderDays));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReminderCommand // instanceof handles nulls
                && isSet == ((ReminderCommand) other).isSet
                && (!isSet || reminderDays == ((ReminderCommand) other).reminderDays));
    }
}
