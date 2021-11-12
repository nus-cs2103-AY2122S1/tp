package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindFacilityCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.facility.LocationContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindFacilityCommand object.
 */
public class FindFacilityCommandParser implements Parser<FindFacilityCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindFacilityCommand
     * and returns a FindFacilityCommand object for execution.
     *
     * @param args the user input.
     * @return the FindFacilityCommand object.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public FindFacilityCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindFacilityCommand.MESSAGE_USAGE));
        }

        String[] locationKeywords = trimmedArgs.split("\\s+");

        return new FindFacilityCommand(new LocationContainsKeywordsPredicate(Arrays.asList(locationKeywords)));
    }
}
