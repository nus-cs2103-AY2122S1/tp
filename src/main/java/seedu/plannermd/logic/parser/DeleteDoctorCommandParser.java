package seedu.plannermd.logic.parser;

import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.deletecommand.DeleteDoctorCommand;
import seedu.plannermd.logic.parser.exceptions.ParseException;

import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class DeleteDoctorCommandParser implements Parser<DeleteDoctorCommand> {

    @Override
    public DeleteDoctorCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteDoctorCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDoctorCommand.MESSAGE_USAGE), pe);
        }
    }

}
