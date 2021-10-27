package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import seedu.address.logic.commands.SchedulePeriodChangeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class SchedulePeriodChangeCommandParser implements Parser<SchedulePeriodChangeCommand> {

    @Override
    public SchedulePeriodChangeCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_DATE);
        if (argMultimap.getValue(PREFIX_DATE).isEmpty()) {
            return new SchedulePeriodChangeCommand(ParserUtil.initializePeriodToThisWeek());
        }
        return new SchedulePeriodChangeCommand(ParserUtil.extractPeriodDates(argMultimap));

    }
}
