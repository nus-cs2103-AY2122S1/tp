package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteClassCommand;
import seedu.address.logic.commands.DeleteStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutorialclass.TutorialClass;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class DeleteClassCommandParser implements Parser<DeleteClassCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteClassCommand
     * and returns a DeleteClassCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     * @return
     */
    @Override
    public DeleteClassCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteClassCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteClassCommand.MESSAGE_USAGE), pe);
        }
    }
}
