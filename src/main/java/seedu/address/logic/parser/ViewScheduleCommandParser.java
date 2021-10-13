package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ParserUtil.testByAllFields;

import seedu.address.logic.commands.ViewScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonContainsFieldsPredicate;

/**
 * Class representing the view schedule command parser.
 */
public class ViewScheduleCommandParser implements Parser<ViewScheduleCommand> {

    @Override
    public ViewScheduleCommand parse(String userInput) throws ParseException {
        //currently defined for name prefix, undefined behaviour
        requireNonNull(userInput);
        PersonContainsFieldsPredicate predicate = testByAllFields(userInput);
        return new ViewScheduleCommand(predicate);
    }
}
