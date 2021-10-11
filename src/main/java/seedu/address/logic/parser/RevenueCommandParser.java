package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REVENUE;

import seedu.address.commons.core.Money;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.RevenueCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Revenue;

public class RevenueCommandParser implements Parser<RevenueCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RevenueCommand
     * and returns a RevenueCommand object for execution.
     *
     * @param args Command input by the user to be parsed.
     * @return RevenueCommand object for execution based on the command input given.
     * @throws ParseException If the user input does not conform the expected format
     */
    public RevenueCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REVENUE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RevenueCommand.COMMAND_WORD), ive);
        }

        Money revenue;
        try {
            String revenueText = argMultimap.getValue(PREFIX_REVENUE).orElse("");
            revenue = new Money(Float.parseFloat(revenueText));
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RevenueCommand.COMMAND_WORD), e);
        }
        return new RevenueCommand(index, new Revenue(revenue));
    }
}
