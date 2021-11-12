package seedu.notor.logic.parser.person;

import seedu.notor.logic.commands.person.PersonClearTagsCommand;
import seedu.notor.logic.commands.person.PersonCommand;
import seedu.notor.logic.parser.exceptions.ParseException;

/**
 * Checks the parsing for the clear tags command, and returns a Person Command when parse is called.
 */
public class PersonClearTagsCommandParser extends PersonCommandParser {
    public static final String COMMAND_WORD = PersonClearTagsCommand.COMMAND_WORD;

    public PersonClearTagsCommandParser(String unparsedIndex) throws ParseException {
        super(unparsedIndex, null);
    }

    @Override public PersonCommand parse() throws ParseException {
        return new PersonClearTagsCommand(index);
    }
}
