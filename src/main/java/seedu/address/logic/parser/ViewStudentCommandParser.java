package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ViewStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Name;

public class ViewStudentCommandParser implements Parser<ViewStudentCommand> {
    @Override
    public ViewStudentCommand parse(String args) throws ParseException {
        try {
            Name name = ParserUtil.parseName(args);
            return new ViewStudentCommand(name);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewStudentCommand.MESSAGE_USAGE), pe);
        }
    }
}
