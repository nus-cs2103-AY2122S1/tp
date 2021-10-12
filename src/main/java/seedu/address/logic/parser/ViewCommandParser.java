package seedu.address.logic.parser;


import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Supplier;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonContainsFieldsPredicate;

/**
 * Class representing the parser for the view command.
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    private static final Supplier<Boolean> ALWAYS_TRUE = () -> true;

    /**
     * Parser for the view command.
     *
     * @param args The input search fields
     * @throws ParseException If the use input does not conform to the expected format.
     */
    @Override
    public ViewCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_STATUS, PREFIX_ROLE, PREFIX_SALARY);
        //when no argument is given to the argMultiMap
        if (argMultimap.isEmpty()) {
            throw new ParseException(ViewCommand.HELP_MESSAGE);
        }
        PersonContainsFieldsPredicate predicate = ParserUtil.testByAllFields(args);
        return new ViewCommand(predicate);
    }

}
