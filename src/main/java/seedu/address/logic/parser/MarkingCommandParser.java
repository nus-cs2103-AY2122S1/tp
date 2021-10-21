package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.commands.MarkingCommand;
import seedu.address.logic.commands.UnmarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkingCommand object
 */
public class MarkingCommandParser implements Parser<MarkingCommand> {

    private final String typeOfMarking;

    public MarkingCommandParser(String typeOfMarking) {
        this.typeOfMarking = typeOfMarking;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the DoneCommand
     * and returns a DoneCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkingCommand parse(String args) throws ParseException {
        try {
            Index[] indexes = ParserUtil.parseMultipleIndex(args);
            if (typeOfMarking.equals(MarkCommand.COMMAND_WORD)) {
                return new MarkCommand(indexes);
            } else {
                return new UnmarkCommand(indexes);
            }
        } catch (ParseException pe) {
            String errorMessage;
            if (typeOfMarking.equals(MarkCommand.COMMAND_WORD)) {
                errorMessage = MarkCommand.MESSAGE_USAGE;
            } else {
                errorMessage = UnmarkCommand.MESSAGE_USAGE;
            }
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, errorMessage), pe);
        }
    }

}
