package seedu.address.logic.parser.groups;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.groups.GroupRemoveLessonCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditPersonCommand object
 */
public class GroupRemoveLessonParser implements Parser<GroupRemoveLessonCommand> {


    public static final String COMMAND_WORD = "-dl";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes the lesson identified by the index number "
            + "from the group identified by the index number used in the displayed list"
            + "Parameters: INDEX1 INDEX2 (both must be a positive integer)";

    @Override
    public GroupRemoveLessonCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);

        List<Index> indexes;

        try {
            indexes = ParserUtil.parseAllIndex(userInput);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_USAGE), pe);
        }
        return new GroupRemoveLessonCommand(indexes.get(0), indexes.get(1));
    }
}
