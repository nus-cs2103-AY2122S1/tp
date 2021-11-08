package seedu.notor.logic.parser.person;

import static seedu.notor.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.notor.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.person.PersonCommand;
import seedu.notor.logic.parser.Parser;
import seedu.notor.logic.parser.ParserUtil;
import seedu.notor.logic.parser.exceptions.ParseException;

public abstract class PersonCommandParser extends Parser<PersonCommand> {
    protected String arguments;
    protected Index index;

    protected PersonCommandParser(String unparsedIndex, String arguments) throws ParseException {
        this.arguments = arguments;
        parseIndex(unparsedIndex);
    }

    private void parseIndex(String unparsedIndex) throws ParseException {
        if (unparsedIndex == null) {
            index = null;
        } else {
            try {
                index = ParserUtil.parseIndex(unparsedIndex);
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, PersonCommand.MESSAGE_USAGE),
                        pe);
            }
        }
    }

    public abstract PersonCommand parse() throws ParseException;
}
