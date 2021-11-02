package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_TAG;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.predicates.PersonContainsFieldsPredicate;

/**
 * Class representing the parser for the view command.
 */
public class ViewCommandParser implements Parser<ViewCommand> {
    private static final ParseException NO_FIELD_EXCEPTION = new ParseException(ViewCommand.MESSAGE_USAGE);

    /**
     * Parser for the view command.
     *
     * @param args The input search fields
     * @throws ParseException If the use input does not conform to the expected format.
     */
    @Override
    public ViewCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DASH_NAME, PREFIX_DASH_PHONE,
                        PREFIX_DASH_EMAIL, PREFIX_DASH_ADDRESS, PREFIX_DASH_TAG,
                        PREFIX_DASH_STATUS, PREFIX_DASH_ROLE, PREFIX_DASH_SALARY);
        if (argMultimap.isEmpty()) {
            throw NO_FIELD_EXCEPTION;
        }
        PersonContainsFieldsPredicate predicate = ParserUtil.testByAllFields(argMultimap);
        return new ViewCommand(predicate);
    }
}
