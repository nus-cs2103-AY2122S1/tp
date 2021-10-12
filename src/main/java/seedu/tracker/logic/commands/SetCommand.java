package seedu.tracker.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_ACADEMIC_YEAR;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_SEMESTER;

import seedu.tracker.logic.commands.exceptions.CommandException;
import seedu.tracker.model.Model;
import seedu.tracker.model.calendar.AcademicCalendar;

/**
 * Sets current semester in Module Tracker.
 */
public class SetCommand extends Command {
    public static final String COMMAND_WORD = "set";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets current semester. \n"
            + "Parameters: "
            + PREFIX_ACADEMIC_YEAR + "YEAR"
            + PREFIX_SEMESTER + "SEMESTER"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ACADEMIC_YEAR + "2 "
            + PREFIX_SEMESTER + "1";
    public static final String MESSAGE_SUCCESS = "Current semester set:\n %1$s";
    private AcademicCalendar currentSemester;

    /**
     * Creates a SetCommand.
     *
     * @param academicCalendar the specific semester that is to set as current semester
     */
    public SetCommand(AcademicCalendar academicCalendar) {
        requireNonNull(academicCalendar);

        this.currentSemester = academicCalendar;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.setCurrentSemester(currentSemester);
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, currentSemester));
    }
}
