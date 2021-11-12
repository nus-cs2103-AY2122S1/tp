package seedu.address.logic.parser.modulelesson;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2040;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.modulelesson.DeleteModuleLessonCommand;
import seedu.address.model.modulelesson.ModuleCodeContainsKeywordsPredicate;

public class DeleteModuleLessonCommandParserTest {

    private DeleteModuleLessonCommandParser parser = new DeleteModuleLessonCommandParser();

    @Test
    public void parse_validIndex_returnsDeleteModuleLessonCommand() {
        assertParseSuccess(parser, "1", new DeleteModuleLessonCommand(INDEX_FIRST));
    }

    @Test
    public void parse_validRange_returnsDeleteModuleLessonCommand() {
        assertParseSuccess(parser, " 1-2", new DeleteModuleLessonCommand(INDEX_FIRST, INDEX_SECOND));
    }

    @Test
    public void parse_validModuleCode_returnsDeleteModuleLessonCommand() {
        String userInput = String.format(" m/%s", VALID_MODULE_CODE_CS2040);
        DeleteModuleLessonCommand deleteModuleLessonCommand = new DeleteModuleLessonCommand(
                new ModuleCodeContainsKeywordsPredicate(Collections.singletonList(VALID_MODULE_CODE_CS2040)));

        assertParseSuccess(parser, userInput, deleteModuleLessonCommand);
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleLessonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidRange_throwsParseException() {
        assertParseFailure(parser, " 1,2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleLessonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyModuleCode_throwsParseException() {
        assertParseFailure(parser, "m/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleLessonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleModuleCode_throwsParseException() {
        assertParseFailure(parser, " m/CS2030S m/CS2040S",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteModuleLessonCommand.MESSAGE_DELETE_BY_MODULE_CODE_USAGE));
    }

    @Test
    public void parse_lessonCodes_throwsParseException() {
        assertParseFailure(parser, " m/CS2030S T09",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleLessonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidModuleCode_throwsParseException() {
        assertParseFailure(parser, " m/CS2030S CS2040",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleLessonCommand.MESSAGE_USAGE));
    }
}
