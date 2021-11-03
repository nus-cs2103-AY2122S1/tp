package seedu.unify.logic.parser;

import static seedu.unify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.unify.logic.commands.CommandTestUtil.DATE_DESC_ASSIGNMENT;
import static seedu.unify.logic.commands.CommandTestUtil.DATE_DESC_QUIZ;
import static seedu.unify.logic.commands.CommandTestUtil.INVALID_TIME_DESC;
import static seedu.unify.logic.commands.CommandTestUtil.NAME_DESC_ASSIGNMENT;
import static seedu.unify.logic.commands.CommandTestUtil.TAG_DESC_CCA;
import static seedu.unify.logic.commands.CommandTestUtil.TIME_DESC_ASSIGNMENT;
import static seedu.unify.logic.commands.CommandTestUtil.TIME_DESC_QUIZ;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_DATE_ASSIGNMENT;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_DATE_QUIZ;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_NAME_ASSIGNMENT;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_TAG_CCA;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_TIME_ASSIGNMENT;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_TIME_QUIZ;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.unify.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.unify.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.unify.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.unify.testutil.TypicalIndexes.INDEX_THIRD_TASK;

import org.junit.jupiter.api.Test;

import seedu.unify.commons.core.index.Index;
import seedu.unify.logic.commands.EditCommand;
import seedu.unify.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.unify.testutil.EditTaskDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_ASSIGNMENT, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_ASSIGNMENT, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_ASSIGNMENT, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + TIME_DESC_QUIZ;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTime(VALID_TIME_QUIZ).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_TASK;
        String userInput = targetIndex.getOneBased() + NAME_DESC_ASSIGNMENT;
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withName(VALID_NAME_ASSIGNMENT).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // time
        userInput = targetIndex.getOneBased() + TIME_DESC_ASSIGNMENT;
        descriptor = new EditTaskDescriptorBuilder().withTime(VALID_TIME_ASSIGNMENT).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + DATE_DESC_ASSIGNMENT;
        descriptor = new EditTaskDescriptorBuilder().withDate(VALID_DATE_ASSIGNMENT).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_CCA;
        descriptor = new EditTaskDescriptorBuilder().withTags(VALID_TAG_CCA).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + INVALID_TIME_DESC + TIME_DESC_QUIZ;
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTime(VALID_TIME_QUIZ).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_TIME_DESC + DATE_DESC_QUIZ
                + TIME_DESC_QUIZ;
        descriptor = new EditTaskDescriptorBuilder().withTime(VALID_TIME_QUIZ)
                .withDate(VALID_DATE_QUIZ).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
