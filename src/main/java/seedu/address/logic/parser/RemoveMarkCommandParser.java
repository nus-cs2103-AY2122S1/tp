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
import seedu.address.logic.commands.RemoveMarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Period;
import seedu.address.model.person.PersonContainsFieldsPredicate;

/**
 * Class representing the parser for the remove mark command.
 */
public class RemoveMarkCommandParser implements Parser<RemoveMarkCommand> {

    private static final ParseException NO_FIELD_EXCEPTION = new ParseException(RemoveMarkCommand.MESSAGE_USAGE);


    @Override
    public RemoveMarkCommand parse(String userInput) throws ParseException {
        //created to test if there are any identifiers
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_PHONE, PREFIX_INDEX, PREFIX_DAY_SHIFT,
                        PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_STATUS, PREFIX_ROLE, PREFIX_SALARY);
        //created to test if there are
        List<String> periods = argMultimap.getAllValues(PREFIX_DAY_SHIFT);
        if ((periods.size() != 1 && periods.size() != 2)) {
            throw NO_FIELD_EXCEPTION;
        }
        Period period = parsePeriod(periods);

        PersonContainsFieldsPredicate predicate = ParserUtil.testByAllFields(argMultimap);
        //checks for index
        if (argMultimap.getValue(PREFIX_INDEX).isPresent()) {
            Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
            return new RemoveMarkCommand(predicate, index, period);
        }
        //checks for empty
        if (predicate.isEmpty()) {
            throw NO_FIELD_EXCEPTION;
        }

        return new RemoveMarkCommand(predicate, period);

    }
}
