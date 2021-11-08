package seedu.fast.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_LENGTH;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.fast.commons.core.index.Index;
import seedu.fast.commons.exceptions.IllegalValueException;
import seedu.fast.logic.commands.RemarkCommand;
import seedu.fast.logic.parser.exceptions.ParseException;
import seedu.fast.model.person.Remark;

public class RemarkCommandParser implements Parser<RemarkCommand> {
    @Override
    public RemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REMARK);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RemarkCommand.MESSAGE_USAGE), ive);
        }

        String remark = argMultimap.getValue(PREFIX_REMARK).orElse("");
        if (!Remark.isValidRemark(remark)) {
            throw new ParseException(String.format(MESSAGE_INVALID_LENGTH,
                    RemarkCommand.MESSAGE_USAGE));
        }
        return new RemarkCommand(index, new Remark(remark));
    }
}
