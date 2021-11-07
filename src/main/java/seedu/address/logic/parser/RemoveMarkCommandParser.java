package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.RemoveMarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Period;
import seedu.address.model.person.predicates.PersonContainsFieldsPredicate;

/**
 * Class representing the parser for the remove mark command.
 */
public class RemoveMarkCommandParser implements Parser<RemoveMarkCommand> {

    private static final ParseException NO_FIELD_EXCEPTION = new ParseException(RemoveMarkCommand.MESSAGE_USAGE);


    @Override
    public RemoveMarkCommand parse(String userInput) throws ParseException {
        //created to test if there are any identifiers
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_DASH_NAME,
                        PREFIX_DASH_PHONE, PREFIX_DASH_INDEX, PREFIX_DATE,
                        PREFIX_DASH_EMAIL, PREFIX_DASH_TAG, PREFIX_DASH_STATUS,
                        PREFIX_DASH_ROLE, PREFIX_DASH_SALARY);
        //created to test if there are
        Period period = DateTimeUtil.getDisplayedPeriod();
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            period = ParserUtil.extractPeriodDates(argMultimap);
        }

        PersonContainsFieldsPredicate predicate = ParserUtil.testByAllFields(argMultimap);
        //checks for index
        if (argMultimap.getValue(PREFIX_DASH_INDEX).isPresent()) {
            Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_DASH_INDEX).get());
            return new RemoveMarkCommand(predicate, index, period);
        }
        //checks for empty
        if (predicate.isEmpty()) {
            throw NO_FIELD_EXCEPTION;
        }
        return new RemoveMarkCommand(predicate, period);
    }
}
