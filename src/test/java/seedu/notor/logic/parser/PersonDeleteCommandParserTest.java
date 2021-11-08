package seedu.notor.logic.parser;

import static seedu.notor.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.notor.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.notor.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.notor.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.notor.logic.commands.HelpCommand;
import seedu.notor.logic.commands.person.PersonDeleteCommand;
import seedu.notor.logic.parser.exceptions.ParseException;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class PersonDeleteCommandParserTest {
    private final NotorParser notorParser = new NotorParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() throws ParseException {
        String deleteFirstPerson = "person 1 /delete";
        assertParseSuccess(notorParser.parseCommand(deleteFirstPerson), new PersonDeleteCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String deleteFirstPerson = "person a /delete";
        assertParseFailure(notorParser, deleteFirstPerson,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
    }
}
