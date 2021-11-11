package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GUEST;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.CheckInCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CheckInCommand object
 */
public class CheckInCommandParser implements Parser<CheckInCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CheckInCommand
     * and returns a CheckInCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public CheckInCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_GUEST);

        Index roomIndex;
        try {
            roomIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(CheckInCommand.MESSAGE_ROOM_INVALID_INDEX);
        }

        List<String> guestList = argMultimap.getAllValues(PREFIX_GUEST);
        List<Index> guestIndexes = new ArrayList<>();
        try {
            for (String guestString : guestList) {
                guestIndexes.add(ParserUtil.parseIndex(guestString));
            }
        } catch (IllegalValueException ive) {
            throw new ParseException(CheckInCommand.MESSAGE_PERSON_INVALID_INDEX);
        }

        return new CheckInCommand(roomIndex, guestIndexes);
    }

}
