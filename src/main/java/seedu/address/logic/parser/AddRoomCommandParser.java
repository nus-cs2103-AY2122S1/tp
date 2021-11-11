package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddRoomCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddRoomCommand object
 */
public class AddRoomCommandParser implements Parser<AddRoomCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddRoomCommand
     * and returns an AddRoomCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddRoomCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_TAG)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddRoomCommand.MESSAGE_USAGE));
        }

        int numberOfRooms;
        try {
            numberOfRooms = ParserUtil.parseNumber(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(AddRoomCommand.MESSAGE_INVALID_INTEGER);
        }

        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        return new AddRoomCommand(numberOfRooms, tagList);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
