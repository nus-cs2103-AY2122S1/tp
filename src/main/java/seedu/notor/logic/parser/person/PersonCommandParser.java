package seedu.notor.logic.parser.person;

import static seedu.notor.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.person.PersonCommand;
import seedu.notor.logic.commands.person.PersonEditCommand;
import seedu.notor.logic.parser.ArgumentMultimap;
import seedu.notor.logic.parser.Parser;
import seedu.notor.logic.parser.ParserUtil;
import seedu.notor.logic.parser.exceptions.ParseException;

public abstract class PersonCommandParser extends Parser<PersonCommand> {
    protected Index parseIndex(ArgumentMultimap argMultimap) throws ParseException {
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PersonEditCommand.MESSAGE_USAGE),
                    pe);
        }
        return index;
    }

    protected Index parseIndex(String args) throws ParseException {
        Index index;
        try {
            index = ParserUtil.parseIndex(args);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PersonEditCommand.MESSAGE_USAGE),
                    pe);
        }
        return index;
    }
}
