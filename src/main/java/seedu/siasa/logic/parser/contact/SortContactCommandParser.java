package seedu.siasa.logic.parser.contact;

import static seedu.siasa.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.siasa.model.contact.PersonComparator.CONTACT_SORT_BY_ALPHA_ASC;
import static seedu.siasa.model.contact.PersonComparator.CONTACT_SORT_BY_ALPHA_DESC;

import seedu.siasa.logic.commands.contact.SortContactCommand;
import seedu.siasa.logic.parser.Parser;
import seedu.siasa.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortContactCommand object
 */
public class SortContactCommandParser implements Parser<SortContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortContactCommand
     * and returns a SortContactCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortContactCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortContactCommand.MESSAGE_USAGE));
        }

        if ("asc".equals(trimmedArgs)) {
            return new SortContactCommand(CONTACT_SORT_BY_ALPHA_ASC);
        } else if ("dsc".equals(trimmedArgs)) {
            return new SortContactCommand(CONTACT_SORT_BY_ALPHA_DESC);
        } else {
            return new SortContactCommand();
        }
    }
}
