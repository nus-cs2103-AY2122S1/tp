package seedu.address.logic.parser.tasks;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tasks.UnassignTaskFromPersonCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class UnassignTaskFromPersonCommandParser implements Parser<UnassignTaskFromPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AssignTaskToPersonCommand
     * and returns an AssignTaskToPersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnassignTaskFromPersonCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Index personIndex;
        Index taskIndex;

        try {
            Index[] indices = ParserUtil.parseTwoIndices(args);
            personIndex = indices[0];
            taskIndex = indices[1];
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UnassignTaskFromPersonCommand.MESSAGE_USAGE), pe);
        }

        return new UnassignTaskFromPersonCommand(personIndex, taskIndex);
    }
}
