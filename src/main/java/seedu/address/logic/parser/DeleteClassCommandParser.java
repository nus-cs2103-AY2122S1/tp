package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteClassCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteClassCommand object.
 */
public class DeleteClassCommandParser implements Parser<DeleteClassCommand> {
    /**
     * Parses {@code userInput} into a DeleteClassCcommand and returns it.
     *
     * @param userInput The indices input by user.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public DeleteClassCommand parse(String userInput) throws ParseException {
        try {
            List<String> argList = new ArrayList<>();
            argList.add(userInput.trim());
            List<Index> classes = ParserUtil.parseIndices(argList);
            return new DeleteClassCommand(classes);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteClassCommand.MESSAGE_USAGE), pe);
        }
    }
}
