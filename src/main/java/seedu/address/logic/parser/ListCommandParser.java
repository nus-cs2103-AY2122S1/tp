package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.function.Predicate;

import seedu.address.commons.core.listroomargs.ListRoomArg;
import seedu.address.commons.core.listtype.ListType;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomIsOccupiedPredicate;
import seedu.address.model.room.RoomIsVacantPredicate;

public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ListCommand parse(String args) throws ParseException {
        // check for empty arguments
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        String[] argsArr = trimmedArgs.split(" ", 2);
        String type = argsArr[0];
        ListType listType = ParserUtil.parseListType(type);
        if (argsArr.length == 1) {
            return new ListCommand(listType);
        } else {
            // list guests command should not have extra arguments
            if (listType.isGuestsType() || listType.isRecordsType()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
            }
            String listRoomArgument = argsArr[1];
            ListRoomArg arg = ParserUtil.parseListRoomArgument(listRoomArgument);
            return new ListCommand(listType, getListRoomPredicate(arg));
        }
    }

    /**
     * Returns the Predicate corresponding to the {@code ListRoomArg}.
     *
     * @param argument
     */
    public Predicate<Room> getListRoomPredicate(ListRoomArg argument) {
        if (argument.isVacant()) {
            return new RoomIsVacantPredicate();
        } else {
            return new RoomIsOccupiedPredicate();
        }
    }
}
