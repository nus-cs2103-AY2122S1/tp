package seedu.tracker.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_ACADEMIC_YEAR;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_MC;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_SEMESTER;

import seedu.tracker.logic.commands.exceptions.CommandException;
import seedu.tracker.model.Model;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.module.Mc;

/**
 * Sets current semester in Module Tracker.
 */
public class SetCommand extends Command {
    public static final String COMMAND_WORD = "set";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets current semester or mc goal. \n"
            + "Only one set of field should be specified\n"
            + "Parameters: "
            + "[" + PREFIX_ACADEMIC_YEAR + "YEAR"
            + PREFIX_SEMESTER + "SEMESTER]"
            + "[" + PREFIX_MC + "MC]\n"
            + "Example 1: " + COMMAND_WORD + " "
            + PREFIX_ACADEMIC_YEAR + "2 "
            + PREFIX_SEMESTER + "1\n"
            + "Example 2: " + COMMAND_WORD + " "
            + PREFIX_MC + "188";
    public static final String MESSAGE_SUCCESS_SEM = "Current semester set:\n %1$s";
    public static final String MESSAGE_SUCCESS_MC = "Mc goal set:\n %1$s";
    private boolean sem = false;
    private AcademicCalendar currentSemester;
    private Mc mcGoal;

    /**
     * Creates a SetCommand.
     *
     * @param academicCalendar the specific semester that is to set as current semester
     */
    public SetCommand(AcademicCalendar academicCalendar) {
        sem = true;
        requireNonNull(academicCalendar);

        this.currentSemester = academicCalendar;
    }
    /**
     * Creates a SetCommand.
     *
     * @param mcGoal the specific mc that is to set as mc goal
     */
    public SetCommand(Mc mcGoal) {
        requireNonNull(mcGoal);

        this.mcGoal = mcGoal;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (sem) {
            //model.setCurrentSemester(currentSemester);
            return new CommandResult(
                    String.format(MESSAGE_SUCCESS_SEM, currentSemester));
        } else {
            //model.setMcGoal(mcGoal);
            return new CommandResult(
                    String.format(MESSAGE_SUCCESS_MC, mcGoal));
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SetCommand)) {
            return false;
        }

        // state check
        SetCommand t = (SetCommand) other;

        return sem ? currentSemester.equals(t.currentSemester)
                   : mcGoal.equals(t.mcGoal);
    }
}
