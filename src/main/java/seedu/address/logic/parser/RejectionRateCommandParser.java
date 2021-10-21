package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;

import java.util.stream.Stream;

import seedu.address.logic.commands.RejectionRateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.PositionBook;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;

/**
 * Parses input arguments and creates a new RejectionRateCommand object
 */
public class RejectionRateCommandParser implements Parser<RejectionRateCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the RejectionRateCommand
     * and returns a RejectionRateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RejectionRateCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_POSITION);

        if (!arePrefixesPresent(argumentMultimap, PREFIX_POSITION)
                || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RejectionRateCommand.MESSAGE_USAGE));
        }

        Title positionTitle = ApplicantParserUtil.parseTitle(argumentMultimap.getValue(PREFIX_POSITION).get());
        Position position = PositionBook.getPositionByTitle(positionTitle);

        return new RejectionRateCommand(position);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
