package seedu.address.logic.parser.persons;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.persons.PersonAddLessonCommand;
import seedu.address.logic.commands.persons.PersonRemoveLessonCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new PersonRemoveLessonCommand object
 */
public class PersonRemoveLessonParser implements Parser<PersonRemoveLessonCommand> {


    @Override
    public PersonRemoveLessonCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);

        List<Index> indexes;

        try {
            indexes = ParserUtil.parseAllIndex(userInput);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    PersonAddLessonCommand.MESSAGE_USAGE), pe);
        }

        return new PersonRemoveLessonCommand(indexes.get(0), indexes.get(1));
    }
}
