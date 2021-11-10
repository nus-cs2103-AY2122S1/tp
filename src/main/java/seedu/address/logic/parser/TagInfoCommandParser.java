package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.TagInfoCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new TagInfoCommand object
 */
public class TagInfoCommandParser implements Parser<TagInfoCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TagInfoCommand
     * and returns an TagInfoCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public TagInfoCommand parse(String userInput) throws ParseException {
        if (userInput.trim().equals("list")) {
            return new TagInfoCommand(userInput, false);
        } else {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(userInput, PREFIX_TAG);

            if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
                try {
                    return new TagInfoCommand(argMultimap.getValue(PREFIX_TAG).get().split(" "), false);
                } catch (CommandException e) {
                    throw new ParseException(e.getMessage());
                }
            } else {
                throw new ParseException(TagInfoCommand.MESSAGE_NO_INPUT);
            }
        }
    }
}
