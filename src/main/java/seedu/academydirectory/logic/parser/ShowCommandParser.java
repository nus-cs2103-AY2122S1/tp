package seedu.academydirectory.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.academydirectory.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_ASSESSMENT;

import seedu.academydirectory.commons.exceptions.IllegalValueException;
import seedu.academydirectory.logic.commands.ShowCommand;
import seedu.academydirectory.logic.parser.exceptions.ParseException;

public class ShowCommandParser implements Parser<ShowCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ShowCommand
     * and returns an ShowCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ASSESSMENT);

        String assessment;
        try {
            assessment = ParserUtil.parseAssessment(argMultimap.getValue(PREFIX_ASSESSMENT).get());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ShowCommand.MESSAGE_USAGE), ive);
        }
        return new ShowCommand(assessment);
    }
}
