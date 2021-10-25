package seedu.notor.logic.parser.person;

import static seedu.notor.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.notor.logic.commands.person.PersonCommand;
import seedu.notor.logic.commands.person.PersonFindCommand;
import seedu.notor.logic.parser.exceptions.ParseException;
import seedu.notor.model.person.NameContainsSubstringPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class PersonFindCommandParser extends PersonCommandParser {
    public PersonFindCommandParser(String arguments) throws ParseException {
        super(null, arguments);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the PersonFindCommand
     * and returns a PersonFindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public PersonFindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PersonFindCommand.MESSAGE_USAGE));
        }

        return new PersonFindCommand(new NameContainsSubstringPredicate(trimmedArgs));
    }

    /**
     * TODO: Stub command right now. Needs to have functionality moved over and converted to new command structure.
     */
    @Override
    public PersonCommand parse() throws ParseException {
        return parse(arguments);
    }
}
