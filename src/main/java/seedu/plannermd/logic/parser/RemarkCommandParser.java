package seedu.plannermd.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.Optional;

import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.commons.exceptions.IllegalValueException;
import seedu.plannermd.logic.commands.remarkcommand.RemarkCommand;
import seedu.plannermd.logic.parser.exceptions.ParseException;
import seedu.plannermd.model.person.Remark;

/**
 * Ab abstract class to parse input arguments and create a new RemarkCommand
 * object.
 */
public abstract class RemarkCommandParser implements Parser<RemarkCommand> {

    protected abstract String getUsageMessage();
    protected abstract RemarkCommand getPersonRemarkCommand(Index index, Remark remark);

    @Override
    public RemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_REMARK);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    getUsageMessage()), ive);
        }

        Optional<String> remark = argMultimap.getValue(PREFIX_REMARK);

        //guard clause for missing delimitter
        if (remark.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, getUsageMessage()));
        }

        return getPersonRemarkCommand(index, new Remark(remark.get()));
    }
}
