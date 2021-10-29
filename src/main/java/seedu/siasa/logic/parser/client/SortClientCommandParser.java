package seedu.siasa.logic.parser.client;

import static seedu.siasa.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.siasa.model.person.PersonComparator.PERSON_SORT_BY_ALPHA_ASC;
import static seedu.siasa.model.person.PersonComparator.PERSON_SORT_BY_ALPHA_DESC;

import seedu.siasa.logic.commands.client.SortClientCommand;
import seedu.siasa.logic.parser.Parser;
import seedu.siasa.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class SortClientCommandParser implements Parser<SortClientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortClientCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortClientCommand.MESSAGE_USAGE));
        }

        if ("dsc".equals(trimmedArgs)) {
            return new SortClientCommand(PERSON_SORT_BY_ALPHA_DESC);
        } else {
            return new SortClientCommand(PERSON_SORT_BY_ALPHA_ASC);
        }
    }
}
