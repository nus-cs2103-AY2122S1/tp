package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_SHIFT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_DAY_SHIFT, PREFIX_INDEX);
        //created to test if there are
        List<String> periods = argMultimap.getAllValues(PREFIX_DAY_SHIFT);
        if ((periods.size() != 1 && periods.size() != 2)) {
            throw NO_FIELD_EXCEPTION;
        }
        Period period = parsePeriod(periods);
        argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_STATUS, PREFIX_ROLE, PREFIX_SALARY);
        if (argMultimap.isEmpty()) {
            throw NO_FIELD_EXCEPTION;
        }
        PersonContainsFieldsPredicate predicate = ParserUtil.testByAllFields(argMultimap);

        if (argMultimap.getValue(PREFIX_INDEX).isPresent()) {
            Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
            return new MarkCommand(index, period, predicate);
        }

        return new MarkCommand(predicate, period);

    }



}
