package manageme.logic.commands.calendar;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import manageme.logic.commands.Command;
import manageme.logic.commands.CommandResult;
import manageme.model.Model;

public class ReadDayCommand extends Command {
    public static final String COMMAND_WORD = "readDay";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the list of tasks on a particular date using the following format: YYYY-MM-DD.\n"
            + "Parameters: DATE (must be a valid date)\n"
            + "Example: " + COMMAND_WORD + " 2021-11-17";

    public static final String SHOWING_TASKS_IN_DAY = "Displayed the list of task(s) in %s";

    private final LocalDate date;

    public ReadDayCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        return new CalendarCommandResult(String.format(SHOWING_TASKS_IN_DAY, date), COMMAND_WORD + " " + date);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadDayCommand // instanceof handles nulls
                && date.equals(((ReadDayCommand) other).date)); // state check
    }
}
