package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteMemberCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.member.Id;

/**
 * Parses input arguments and creates a new DeleteMemberCommand object.
 */
public class DeleteMemberCommandParser extends DeleteCommandParser implements Parser<DeleteMemberCommand> {

    private static final int PREFIX_SIZE = 3;

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteMemberCommand
     * and returns a DeleteMemberCommand object for execution.
     *
     * @param args the input arguments related delete member command to be parsed.
     * @return DeleteMemberCommand the class for process input delete member command string.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeleteMemberCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MEMBER, PREFIX_ID, PREFIX_INDEX);

        if (argMultimap.getSize() != PREFIX_SIZE || argMultimap.getValue(PREFIX_MEMBER).isEmpty()
                || (argMultimap.getValue(PREFIX_ID).isEmpty() && argMultimap.getValue(PREFIX_INDEX).isEmpty())
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMemberCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            Id id = ParserUtil.parseMemberId(argMultimap.getValue(PREFIX_ID).get());
            return new DeleteMemberCommand(id);
        }

        if (argMultimap.getValue(PREFIX_INDEX).isPresent()) {
            Index index;
            try {
                index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
            } catch (ParseException pe) {
                throw new ParseException(String.format(pe.getMessage(), DeleteMemberCommand.MESSAGE_USAGE), pe);
            }
            return new DeleteMemberCommand(index);
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMemberCommand.MESSAGE_USAGE));
    }

}
