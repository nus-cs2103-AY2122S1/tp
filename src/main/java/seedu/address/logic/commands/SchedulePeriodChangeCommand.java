package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Period;

/**
 * Class representing the command to change the schedule view
 * by the period.
 */
public class SchedulePeriodChangeCommand extends Command {

    public static final String COMMAND_WORD = "change";
    public static final String DEFAULT_MESSAGE = "Changed schedule to period: %1$s";

    private final Period period;

    public SchedulePeriodChangeCommand(Period period) {
        this.period = period;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(String.format(DEFAULT_MESSAGE, period), period);
    }
}
