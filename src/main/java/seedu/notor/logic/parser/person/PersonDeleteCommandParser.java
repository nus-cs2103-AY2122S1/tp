package seedu.notor.logic.parser.person;

import seedu.notor.commons.core.Messages;
import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.person.PersonDeleteCommand;
import seedu.notor.logic.parser.exceptions.ParseException;

public class PersonDeleteCommandParser extends PersonCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public PersonDeleteCommand parse(String args) throws ParseException {
        try {
            Index index = super.parseIndex(args);
            return new PersonDeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, PersonDeleteCommand.MESSAGE_USAGE), pe);
        }
    }
}
