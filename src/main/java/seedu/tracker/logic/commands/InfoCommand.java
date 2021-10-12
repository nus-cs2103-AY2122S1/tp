package seedu.tracker.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.tracker.model.Model;
import seedu.tracker.model.calendar.AcademicCalendar;

/**
 * Displays all information in the app,
 * including current semester, MCs(Coming soon), etc.
 */
public class InfoCommand extends Command {
    public static final String COMMAND_WORD = "info";

    public static final String MESSAGE_SUCCESS = COMMAND_WORD
        + "\n- Current semester is %1$s";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        AcademicCalendar currentSemester = model.getCurrentSemester();
        return new CommandResult(String.format(MESSAGE_SUCCESS, currentSemester));
    }
}
