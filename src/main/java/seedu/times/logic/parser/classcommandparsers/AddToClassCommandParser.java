package seedu.times.logic.parser.classcommandparsers;

import static java.util.Objects.requireNonNull;
import static seedu.times.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;

import seedu.times.commons.core.index.Index;
import seedu.times.logic.commands.classcommands.AddToClassCommand;
import seedu.times.logic.parser.Parser;
import seedu.times.logic.parser.ParserUtil;
import seedu.times.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new AddToClassCommand object
 */
public class AddToClassCommandParser implements Parser<AddToClassCommand> {


    @Override
    public AddToClassCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String[] argsArray = args.trim().split("\\s+");
        List<Index> indexArray = mapToIndexArray(argsArray);

        return new AddToClassCommand(indexArray);
    }

    private List<Index> mapToIndexArray(String[] str) throws ParseException {
        ArrayList<Index> indexArray = new ArrayList<>();
        for (int i = 0; i < str.length; i++) {
            try {
                Index currentIndex = ParserUtil.parseIndex(str[i]);
                indexArray.add(currentIndex);
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddToClassCommand.MESSAGE_USAGE), pe);
            }
        }

        if (indexArray.size() < 2) {
            throw new ParseException(AddToClassCommand.NO_STUDENT_INDEX_PROVIDED_MESSAGE);
        }

        return copyWithoutDuplicateExcludeFirst(indexArray);
    }

    private List<Index> copyWithoutDuplicateExcludeFirst(List<Index> indexArray) {
        int size = indexArray.size();
        assert size >= 2 : "Should have thrown ParseException.";

        List<Index> res = new ArrayList<>();

        for (int i = 1; i < size; i++) {
            Index currentIndex = indexArray.get(i);
            if (!res.contains(currentIndex)) {
                res.add(currentIndex);
            }
        }

        res.add(0, indexArray.get(0));
        return res;
    }


}
