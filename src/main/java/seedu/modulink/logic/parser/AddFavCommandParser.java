package seedu.modulink.logic.parser;

import static seedu.modulink.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.modulink.commons.core.Messages;
import seedu.modulink.logic.commands.AddFavCommand;
import seedu.modulink.logic.parser.exceptions.ParseException;
import seedu.modulink.model.person.StudentId;

/**
 * Parses input arguments and creates a new AddFavCommand object
 */
public class AddFavCommandParser implements Parser<AddFavCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddFavCommand
     * and returns a AddFavCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddFavCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddFavCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");
        if (!nameKeywords[0].matches(StudentId.VALIDATION_REGEX)) {
            throw new ParseException(String.format(Messages.MESSAGE_UNEXPECTED_INPUT_FORMAT,
                    nameKeywords[0], AddFavCommand.MESSAGE_USAGE));
        }
        if (nameKeywords.length > 1) {
            if (nameKeywords[1].matches(StudentId.VALIDATION_REGEX)) {
                throw new ParseException(AddFavCommand.MULTIPLE_ID_ERROR);
            } else {
                throw new ParseException(String.format(Messages.MESSAGE_UNEXPECTED_INPUT_FORMAT,
                        nameKeywords[1], AddFavCommand.MESSAGE_USAGE));
            }
        }

        return new AddFavCommand(nameKeywords[0]);
    }

}
