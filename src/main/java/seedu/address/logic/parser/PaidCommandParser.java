package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PaidCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.Money;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAID_AMOUNT;

public class PaidCommandParser implements Parser<PaidCommand> {

    public PaidCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PAID_AMOUNT);

        if (!argumentMultimap.getValue(PREFIX_PAID_AMOUNT).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PaidCommand.MESSAGE_USAGE));
        }

        Index[] indices;

        try {
            indices = ParserUtil.parseIndices(argumentMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PaidCommand.MESSAGE_USAGE), pe);
        }

        assert indices.length == 2;

        Money payment = ParserUtil.parseMoney(argumentMultimap.getValue(PREFIX_PAID_AMOUNT).get());

        return new PaidCommand(indices[0], indices[1], payment);
    }
}
