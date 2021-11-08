package seedu.address.logic.parser.person;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.person.PersonFindCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new PersonFindCommand object
 */
public class PersonFindCommandParser implements Parser<PersonFindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PersonFindCommand
     * and returns a PersonFindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PersonFindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PersonFindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new PersonFindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
