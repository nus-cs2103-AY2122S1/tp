package socialite.logic.parser;

import static java.util.Objects.requireNonNull;
import static socialite.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static socialite.commons.core.Messages.MESSAGE_REMARK_EXCEED_LIMIT;
import static socialite.logic.parser.CliSyntax.PREFIX_REMARK;

import socialite.commons.core.index.Index;
import socialite.logic.commands.RemarkCommand;
import socialite.logic.parser.exceptions.ParseException;
import socialite.model.person.Remark;

/**
 * Parses input arguments and creates a new {@code RemarkCommand} object
 */
public class RemarkCommandParser implements Parser<RemarkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REMARK);

        String indexField = argMultimap.getPreamble().trim();
        if (!indexField.matches("\\d+")) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_HELP_GUIDE));
        }
        Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
        Remark remark = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).orElse(null));

        if (!remark.value.isEmpty() && remark.get().length() > 150) {
            throw new ParseException(MESSAGE_REMARK_EXCEED_LIMIT);
        }

        return new RemarkCommand(index, remark);
    }
}
