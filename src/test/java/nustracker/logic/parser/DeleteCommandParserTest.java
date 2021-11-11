package nustracker.logic.parser;

import static nustracker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustracker.logic.parser.CliSyntax.PREFIX_EVENT;
import static nustracker.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static nustracker.logic.parser.CommandParserTestUtil.assertParseFailure;
import static nustracker.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static nustracker.testutil.TypicalEvents.EVENTNAME_ONE;
import static nustracker.testutil.TypicalStudents.STUDENTID_ONE;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import nustracker.logic.commands.DeleteCommand;
import nustracker.logic.commands.DeleteEventCommand;
import nustracker.logic.commands.DeleteStudentCommand;
import nustracker.logic.parser.exceptions.ParseException;
import nustracker.model.event.EventName;
import nustracker.model.student.StudentId;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "s/1" and "s/1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 *
 * Note: This javadoc is from the original test. To edit when delete s/ no longer is by index.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteStudentCommand() {
        assertParseSuccess(parser, " " + PREFIX_STUDENTID + STUDENTID_ONE,
                new DeleteStudentCommand(STUDENTID_ONE));
    }

    @Test
    public void parse_validArgs_returnsDeleteEventCommand() {
        assertParseSuccess(parser, " " + PREFIX_EVENT + EVENTNAME_ONE,
                new DeleteEventCommand(EVENTNAME_ONE));
    }

    @Test
    public void parse_bothStudentIdAndEventPrefix_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" "
                + PREFIX_STUDENTID + STUDENTID_ONE + " "
                + PREFIX_EVENT + EVENTNAME_ONE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " missing prefixes ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " " + PREFIX_STUDENTID, StudentId.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, " " + PREFIX_EVENT, EventName.MESSAGE_CONSTRAINTS);
    }
}
