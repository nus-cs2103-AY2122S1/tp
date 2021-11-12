package seedu.address.logic.parser.person;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2040;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.person.DeletePersonCommand;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.ModuleCodesContainsKeywordsPredicate;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeletePersonCommandParserTest {

    private DeletePersonCommandParser parser = new DeletePersonCommandParser();

    @Test
    public void parse_validIndex_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeletePersonCommand(INDEX_FIRST));
    }

    @Test
    public void parse_validRange_returnsDeleteCommand() {
        assertParseSuccess(parser, " 1-2", new DeletePersonCommand(INDEX_FIRST, INDEX_SECOND));
    }

    @Test
    public void parse_validModuleCode_returnsDeleteCommand() {
        DeletePersonCommand expectedDeletePersonCommand =
                new DeletePersonCommand(new ModuleCodesContainsKeywordsPredicate(
                        Arrays.asList(String.format("%s", VALID_MODULE_CODE_CS2040))),
                        new ModuleCode(VALID_MODULE_CODE_CS2040, new HashSet<>()));
        String userInput = String.format(" m/%s", VALID_MODULE_CODE_CS2040);
        assertParseSuccess(parser, userInput, expectedDeletePersonCommand);
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidRange_throwsParseException() {
        assertParseFailure(parser, " 1,2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyModuleCode_throwsParseException() {
        assertParseFailure(parser, " m/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModuleCode.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_multiplyModuleCode_throwsParseException() {
        assertParseFailure(parser, " m/CS2040S m/CS2030S",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonCommand.MESSAGE_DELETE_BY_MODULE_USAGE));
    }

    @Test
    public void parse_multipleLessonCode_throwsParseException() {
        assertParseFailure(parser, " m/CS2030S T12 L09",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonCommand.MESSAGE_DELETE_BY_LESSON_CODE_USAGE));
    }
}
