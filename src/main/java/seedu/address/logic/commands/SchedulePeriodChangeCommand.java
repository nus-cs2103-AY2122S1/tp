package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import seedu.address.commons.util.DateTimeUtil;
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
    public static final String HELP_MESSAGE = COMMAND_WORD
            + ":Changes the week that the schedule displays. Takes in a single "
            + "date within the week and outputs a full week starting from that date. Date is expected in "
            + "YYYY-MM-DD format.\n\n"
            + "Parameters:\n"
            + PREFIX_DATE + "DATE"
            + "Examples:\n" + COMMAND_WORD + " " + PREFIX_DATE + "2021-12-25";

    private final Period period;

    public SchedulePeriodChangeCommand(Period period) {
        this.period = period;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        DateTimeUtil.updateDisplayedPeriod(period);
        return new CommandResult(String.format(DEFAULT_MESSAGE, period), period);
    }
}
