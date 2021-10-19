package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Executes a TimetableCommand to display a timetable on screen.
 */
public class TimetableCommand extends Command {
    public static final String COMMAND_WORD = "timetable";
    public static final String SHORTCUT = "tt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": show timetable\n"
            + "Parameters: Null\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("Timetable shown.",
                CommandResult.UiAction.SHOW_TIMETABLE);
    }
}
