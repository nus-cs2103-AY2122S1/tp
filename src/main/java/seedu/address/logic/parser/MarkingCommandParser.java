package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.commands.MarkingCommand;
import seedu.address.logic.commands.UnmarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code MarkingCommand} object
 */
public class MarkingCommandParser implements Parser<MarkingCommand> {

    private final String typeOfMarking;

    public MarkingCommandParser(String typeOfMarking) {
        this.typeOfMarking = typeOfMarking;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the MarkingCommand
     * and returns a MarkingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkingCommand parse(String args) throws ParseException {
        try {
            MarkingCommand markingCommand;
            Index[] indexes = ParserUtil.parseMultipleIndex(args);
            if (typeOfMarking.equals(MarkCommand.COMMAND_WORD)) {
                markingCommand = new MarkCommand(indexes);
            } else if (typeOfMarking.equals(UnmarkCommand.COMMAND_WORD)) {
                markingCommand = new UnmarkCommand(indexes);
            } else {
                markingCommand = null;
            }

            assert markingCommand != null;
            return markingCommand;

        } catch (ParseException pe) {
            String errorMessage = typeOfMarking.equals(MarkCommand.COMMAND_WORD)
                    ? MarkCommand.MESSAGE_USAGE : UnmarkCommand.MESSAGE_USAGE;
            String causeOfError = "\n" + pe.getMessage();

            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT + causeOfError, errorMessage), pe);
        }
    }

}
