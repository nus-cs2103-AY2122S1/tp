package seedu.unify.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_DATE;

import java.util.Calendar;

import seedu.unify.logic.commands.exceptions.CommandException;
import seedu.unify.model.Model;
import seedu.unify.model.task.Date;

/**
 * Adds a task to the Uni-Fy app.
 */
public class ShowCommand extends Command {

    public static final Integer WEEKS =Calendar
            .getInstance()
            .getActualMaximum(Calendar.WEEK_OF_YEAR);
    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": shows task for the week \n"
            + "Parameters: "
            + "week number "
            + "OR "
            + "date \n"
            + "Example: " + COMMAND_WORD + " "
            + "1"
            + "OR"
            + PREFIX_DATE + "2021-04-01";

    public static final String MESSAGE_FLOW_ERROR = "%d is an invalid week number input! \n"
            + "The Week Number should be between 1 and "
            + WEEKS;
    public static final String MESSAGE_SUCCESS_1 = "Week %d is shown";
    public static final String MESSAGE_SUCCESS_2 = "Week %d is shown. \n"
            + "On this week date %s falls on a %s";
    public static final String MESSAGE_DUPLICATE_COMMAND = "Week %d is already being shown";

    private String suppliedDate = null;
    private String dayOfDate = null;
    private final int weekNumber;
    private final boolean isValidWeekNumber;
    private final int commandType;

    /**
     * Creates a ShowCommand to show {@code Task} in the given Week Number
     */
    public ShowCommand(int number, int typeNum) {
        weekNumber = number;
        isValidWeekNumber = validWeek(number);
        commandType = typeNum;
    }

    /**
     * Creates a ShowCommand to show {@code Task} in the given Week Number
     */
    public ShowCommand(Date date, int typeNum) {
        this(dateToWeekNum(date), typeNum);
        suppliedDate = date.toString();
        dayOfDate = date.getLocalDate().getDayOfWeek().toString();
    }

    private static int dateToWeekNum(Date date) {
        requireNonNull(date);
        return date.getWeek();
    }

    private static boolean validWeek(int suppliedNum) {
        if (suppliedNum < 1) {
            return false;
        } else {
            return suppliedNum <= WEEKS;
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!isValidWeekNumber) {
            throw new CommandException(String.format(MESSAGE_FLOW_ERROR, weekNumber));
        }
        if (model.getWeeklyTasks().getWeekNumber() == weekNumber) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_COMMAND, weekNumber));
        }
        model.updateWeeklyTaskList(weekNumber);
        return (commandType == 1)
                ? new CommandResult(String.format(MESSAGE_SUCCESS_1, weekNumber))
                : new CommandResult(String.format(MESSAGE_SUCCESS_2, weekNumber, suppliedDate, dayOfDate));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && weekNumber == ((ShowCommand) other).weekNumber);
    }
}

