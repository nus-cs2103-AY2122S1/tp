package seedu.modulink.logic.parser;

import static seedu.modulink.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modulink.commons.core.Messages.MESSAGE_UNEXPECTED_INPUT_FORMAT;

import seedu.modulink.logic.commands.RemFavCommand;
import seedu.modulink.logic.parser.exceptions.ParseException;
import seedu.modulink.model.person.StudentId;

/**
 * Parses input arguments and creates a new AddFavCommand object
 */
public class RemFavCommandParser implements Parser<RemFavCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddFavCommand
     * and returns a AddFavCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemFavCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemFavCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");
        if (nameKeywords.length > 1) {
            throw new ParseException(RemFavCommand.MULTIPLE_ID_ERROR);
        }
        if (!nameKeywords[0].matches(StudentId.VALIDATION_REGEX)) {
            throw new ParseException(String.format(MESSAGE_UNEXPECTED_INPUT_FORMAT,
                    nameKeywords[0], RemFavCommand.MESSAGE_USAGE));
        }

        return new RemFavCommand(nameKeywords[0]);
    }

}
