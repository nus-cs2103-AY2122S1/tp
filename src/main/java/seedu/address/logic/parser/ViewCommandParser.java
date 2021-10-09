package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Supplier;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonContainsFieldsPredicate;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

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
        PersonContainsFieldsPredicate predicate = ParserUtil.testByAllFields(args);
        return new ViewCommand(predicate);
    }


}
