package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    public enum SortableField {
        NAME("-a"),
        MODULE_CODES("-m");

        private ArrayList<String> flagList;

        SortableField(String... flags) {
            flagList = new ArrayList<>(Arrays.asList(flags));
        }

        public static SortableField getField(String userInput) throws ParseException {
            for (SortableField sf : SortableField.values()) {
                if (sf.flagList.contains(userInput)) {
                    return sf;
                }
            }
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (!trimmedArgs.isEmpty()) {
            return new SortCommand(SortableField.getField(trimmedArgs));
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }


}
