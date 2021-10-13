package seedu.address.logic.parser;


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
        PersonContainsFieldsPredicate predicate = ParserUtil.testByAllFields(args);
        return new ViewCommand(predicate);
    }


}
