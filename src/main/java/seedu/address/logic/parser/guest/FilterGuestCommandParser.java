package seedu.address.logic.parser.guest;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.guest.FilterGuestCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.guest.GuestPredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FilterGuestCommand object
 */
public class FilterGuestCommandParser implements Parser<FilterGuestCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterGuestCommand
     * and returns a FilterGuestCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterGuestCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_EMAIL, PREFIX_TAG,
                        PREFIX_PASSPORT_NUMBER, PREFIX_ROOM_NUMBER);

        if (!anyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_EMAIL, PREFIX_TAG,
                PREFIX_PASSPORT_NUMBER, PREFIX_ROOM_NUMBER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterGuestCommand.MESSAGE_USAGE));
        }

        try {
            Optional<String> nameOptional = argMultimap.getValue(PREFIX_NAME);

            Optional<String> emailOptional = argMultimap.getValue(PREFIX_EMAIL);

            Optional<String> passportNumberOptional = argMultimap.getValue(PREFIX_PASSPORT_NUMBER);

            Optional<String> roomNumberOptional = argMultimap.getValue(PREFIX_ROOM_NUMBER);

            List<String> tags = argMultimap.getAllValues(PREFIX_TAG);
            Optional<Set<Tag>> tagsOptional =
                    tags.isEmpty() ? Optional.empty() : Optional.of(ParserUtil.parseTags(tags));

            return new FilterGuestCommand(
                    new GuestPredicate(passportNumberOptional, roomNumberOptional, nameOptional, emailOptional,
                            tagsOptional));
        } catch (ParseException pe) {
            throw pe;
        }

    }

    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
