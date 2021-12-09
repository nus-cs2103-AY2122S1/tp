package seedu.times.logic.parser.classcommandparsers;

import static java.util.Objects.requireNonNull;
import static seedu.times.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.times.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.times.logic.commands.classcommands.AddToClassCommand.CLASS_INDEX_POSITION;

import java.util.ArrayList;

import seedu.times.commons.core.index.Index;
import seedu.times.logic.commands.classcommands.AddToClassCommand;
import seedu.times.logic.commands.classcommands.RemoveFromClassCommand;
import seedu.times.logic.parser.Parser;
import seedu.times.logic.parser.ParserUtil;
import seedu.times.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RemoveFromClassCommand object
 */
public class RemoveFromClassCommandParser implements Parser<RemoveFromClassCommand> {

    @Override
    public RemoveFromClassCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String[] argsArray = args.trim().split("\\s+");
        ArrayList<Index> indexArray = mapToIndex(argsArray);

        return new RemoveFromClassCommand(indexArray);
    }

    private ArrayList<Index> mapToIndex(String[] str) throws ParseException {
        ArrayList<Index> indexArray = new ArrayList<>();
        for (int i = 0; i < str.length; i++) {
            try {
                indexArray.add(ParserUtil.parseIndex(str[i]));
            } catch (ParseException pe) {
                String errorMessage = i == CLASS_INDEX_POSITION
                        ? AddToClassCommand.INVALID_OR_MISSING_CLASS_INDEX
                        : MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        RemoveFromClassCommand.MESSAGE_USAGE), pe);
            }
        }
        if (indexArray.size() < 2) {
            throw new ParseException(RemoveFromClassCommand.NO_STUDENT_INDEX_PROVIDED_MESSAGE);
        }
        return indexArray;
    }
}
