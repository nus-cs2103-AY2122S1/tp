package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CAPACITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddFacilityCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.facility.Capacity;
import seedu.address.model.facility.Facility;
import seedu.address.model.facility.FacilityName;
import seedu.address.model.facility.Location;
import seedu.address.model.facility.Time;


/**
 * Parses input arguments and creates a new AddFacilityCommand object.
 */
public class AddFacilityCommandParser implements Parser<AddFacilityCommand> {

    /**
     * Parses the given arguments in the context of AddFacilityCommand.
     *
     * @param args String arguments to be parsed.
     * @return AddFacilityCommand object for execution.
     * @throws ParseException If the user input does not follow the format.
     */
    public AddFacilityCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultiMap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_LOCATION, PREFIX_TIME, PREFIX_CAPACITY);

        if (!arePrefixesPresent(argMultiMap, PREFIX_NAME, PREFIX_LOCATION, PREFIX_TIME, PREFIX_CAPACITY)
                || !argMultiMap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddFacilityCommand.MESSAGE_USAGE));
        }

        FacilityName name = ParserUtil.parseFacilityName(argMultiMap.getValue(PREFIX_NAME).get());
        Location location = ParserUtil.parseLocation(argMultiMap.getValue(PREFIX_LOCATION).get());
        Time time = ParserUtil.parseTime(argMultiMap.getValue(PREFIX_TIME).get());
        Capacity capacity = ParserUtil.parseCapacity(argMultiMap.getValue(PREFIX_CAPACITY).get());

        Facility facility = new Facility(name, location, time, capacity);

        return new AddFacilityCommand(facility);

    }

    /**
     * Returns true if all the prefixes are non-empty.
     *
     * @param argMultiMap ArgumentMultimap containing the prefixes.
     * @param prefixes Prefixes that need to present.
     * @return boolean value of whether all prefixes are non-empty.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argMultiMap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argMultiMap.getValue(prefix).isPresent());
    }
}
