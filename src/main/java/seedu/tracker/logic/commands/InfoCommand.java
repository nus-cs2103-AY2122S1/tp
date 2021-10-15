package seedu.tracker.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.tracker.model.Model;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.module.Mc;

/**
 * Displays all information in the app,
 * including current semester, MCs(Coming soon), etc.
 */
public class InfoCommand extends Command {
    public static final String COMMAND_WORD = "info";

    public static final String MESSAGE_SUCCESS = COMMAND_WORD
        + "\n- Current semester is %1$s"
        + "\n- Mc goal is %2$s";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        AcademicCalendar currentSemester = model.getCurrentSemester();
        Mc mcGoal = model.getMcGoal();
        return new CommandResult(String.format(MESSAGE_SUCCESS, currentSemester, mcGoal));
    }
}
