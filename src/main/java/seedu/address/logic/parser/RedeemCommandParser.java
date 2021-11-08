package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REDEEM;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RedeemCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.member.Id;
import seedu.address.model.member.Point;

/**
 * Parses input arguments and creates a new RedeemPoint object.
 */
public class RedeemCommandParser implements Parser<RedeemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RedeemCommand
     * and returns an RedeemCommand object for execution.
     *
     * @param args the input arguments related redeem point command to be parsed.
     * @return RedeemCommand the class for process input redeem point command string.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public RedeemCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_REDEEM, PREFIX_ID, PREFIX_INDEX);
        if ((argMultimap.getValue(PREFIX_ID).isEmpty() && argMultimap.getValue(PREFIX_INDEX).isEmpty())
                || (argMultimap.getValue(PREFIX_ID).isPresent() && argMultimap.getValue(PREFIX_INDEX).isPresent())
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RedeemCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            List<Point> pointToRedeemList = ParserUtil.parsePoints(argMultimap.getAllValues(PREFIX_REDEEM));
            Id id = ParserUtil.parseMemberId(argMultimap.getValue(PREFIX_ID).get());
            return new RedeemCommand(pointToRedeemList, id);
        } else if (argMultimap.getValue(PREFIX_INDEX).isPresent()) {
            List<Point> pointToRedeemList = ParserUtil.parsePoints(argMultimap.getAllValues(PREFIX_REDEEM));
            Index index;
            try {
                index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
            } catch (ParseException pe) {
                throw new ParseException(String.format(pe.getMessage(), RedeemCommand.MESSAGE_USAGE), pe);
            }
            return new RedeemCommand(pointToRedeemList, index);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RedeemCommand.MESSAGE_USAGE));
        }
    }
}
