package seedu.address.logic.parser.guest;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.guest.CheckInNewGuestCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commonattributes.Email;
import seedu.address.model.commonattributes.Name;
import seedu.address.model.guest.Guest;
import seedu.address.model.guest.PassportNumber;
import seedu.address.model.guest.RoomNumber;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new CheckInCommand object
 */
public class CheckInNewGuestCommandParser implements Parser<CheckInNewGuestCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CheckInCommand
     * and returns an CheckInCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public CheckInNewGuestCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_EMAIL, PREFIX_TAG, PREFIX_PASSPORT_NUMBER,
                        PREFIX_ROOM_NUMBER);

        if (!arePrefixesPresent(argMultimap, PREFIX_PASSPORT_NUMBER, PREFIX_NAME, PREFIX_EMAIL, PREFIX_ROOM_NUMBER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckInNewGuestCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        RoomNumber roomNumber = ParserUtil.parseRoomNumber(argMultimap.getValue(PREFIX_ROOM_NUMBER).get());
        PassportNumber passport =
                ParserUtil.parsePassportNumber(argMultimap.getValue(PREFIX_PASSPORT_NUMBER).get());

        Guest guest = new Guest(name, email, tagList, roomNumber, passport);
        return new CheckInNewGuestCommand(guest);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
