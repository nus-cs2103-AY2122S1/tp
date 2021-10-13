package seedu.academydirectory.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.academydirectory.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

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

        String assessment;
        try {
            assessment = ParserUtil.parseAssessment(args).toUpperCase();
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE), pe);
        }

        return new ShowCommand(assessment);
    }
}
