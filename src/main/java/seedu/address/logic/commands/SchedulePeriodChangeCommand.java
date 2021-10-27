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



    private final Period period;

    public SchedulePeriodChangeCommand(Period period) {
        this.period = period;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {


        return new CommandResult();
    }
}
