package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteClassCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteClassCommandParser implements Parser<DeleteClassCommand> {
    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param userInput
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public DeleteClassCommand parse(String userInput) throws ParseException {
        try {
            Index i = ParserUtil.parseIndex(userInput);
            return new DeleteClassCommand(i);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteClassCommand.MESSAGE_USAGE), pe);
        }
    }
}
