package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import java.time.LocalDate;

import seedu.address.logic.commands.SchedulePeriodChangeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Period;

public class SchedulePeriodChangeCommandParser implements Parser<SchedulePeriodChangeCommand> {

    @Override
    public SchedulePeriodChangeCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_DATE);
        if (argMultimap.getValue(PREFIX_DATE).isEmpty()
                || argMultimap.getAllValues(PREFIX_DATE).size() != 1) {
            throw new ParseException(SchedulePeriodChangeCommand.HELP_MESSAGE);
        }
        LocalDate firstDay = ParserUtil.parseLocalDate(argMultimap.getValue(PREFIX_DATE).get());
        return new SchedulePeriodChangeCommand(Period.oneWeekFrom(firstDay));
    }
}
