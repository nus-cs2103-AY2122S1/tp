package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddScoreCommand;
import seedu.address.logic.commands.AddScoreCommand.ScoreDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddAllocCommand object
 */
public class AddScoreCommandParser implements Parser<AddScoreCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAllocCommand
     * and returns an AddAllocCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddScoreCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ASSESSMENT, PREFIX_NAME, PREFIX_ID, PREFIX_SCORE);

        if (argMultimap.getValue(PREFIX_ASSESSMENT).isEmpty()
                || argMultimap.getValue(PREFIX_SCORE).isEmpty()
                || arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddScoreCommand.MESSAGE_USAGE));
        }

        AddScoreCommand.ScoreDescriptor scoreDescriptor = new ScoreDescriptor();
        scoreDescriptor.setAssessment(ParserUtil.parseAssessment(argMultimap.getValue(PREFIX_ASSESSMENT).get()));
        scoreDescriptor.setScore(ParserUtil.parseScore(argMultimap.getValue(PREFIX_SCORE).get()));
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            scoreDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            scoreDescriptor.setId(ParserUtil.parseID(argMultimap.getValue(PREFIX_ID).get()));
        }

        return new AddScoreCommand(scoreDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
