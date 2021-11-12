package seedu.teachbook.logic.parser;

import static seedu.teachbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.teachbook.logic.commands.EditClassCommand;
import seedu.teachbook.logic.parser.exceptions.ParseException;
import seedu.teachbook.model.classobject.ClassName;

public class EditClassCommandParser implements Parser<EditClassCommand> {

    @Override
    public EditClassCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditClassCommand.MESSAGE_USAGE));
        }

        ClassName newClassName = ParserUtil.parseClassName(trimmedArgs);

        return new EditClassCommand(newClassName);
    }

}
