package seedu.address.logic.parser.modulelesson;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.modulelesson.DeleteModuleLessonCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteModuleLessonCommandParser implements Parser<DeleteModuleLessonCommand> {
    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param userInput
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public DeleteModuleLessonCommand parse(String userInput) throws ParseException {
        try {
            return new DeleteModuleLessonCommand(ParserUtil.parseIndex(userInput));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                            DeleteModuleLessonCommand.MESSAGE_USAGE), pe);
        }
    }
}
