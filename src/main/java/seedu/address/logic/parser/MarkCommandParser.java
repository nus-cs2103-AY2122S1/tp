package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_SHIFT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.ParserUtil.parsePeriod;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Period;
import seedu.address.model.person.PersonContainsFieldsPredicate;


public class MarkCommandParser implements Parser<MarkCommand> {

    private static final ParseException NO_FIELD_EXCEPTION = new ParseException(MarkCommand.MESSAGE_USAGE);

    @Override
    public MarkCommand parse(String userInput) throws ParseException {
        //created to test if there are any identifiers
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_INDEX, PREFIX_DAY_SHIFT);
        //created to test if there are
        List<String> periods = argMultimap.getAllValues(PREFIX_DAY_SHIFT);
        if ((periods.size() != 1 && periods.size() != 2)) {
            throw NO_FIELD_EXCEPTION;
        }
        Period period;
        period = parsePeriod(periods);
        PersonContainsFieldsPredicate predicate = ParserUtil.testByAllFields(userInput, NO_FIELD_EXCEPTION);
        if (argMultimap.getValue(PREFIX_INDEX).isPresent()) {
            Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
            return new MarkCommand(index, period, predicate);
        } else {
            return new MarkCommand(predicate, period);
        }
    }



}
