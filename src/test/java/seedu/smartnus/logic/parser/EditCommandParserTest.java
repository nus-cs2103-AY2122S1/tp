package seedu.smartnus.logic.parser;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.logic.commands.CommandTestUtil.IMPORTANCE_DESC_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.IMPORTANCE_DESC_2;
import static seedu.smartnus.logic.commands.CommandTestUtil.INVALID_IMPORTANCE_DESC;
import static seedu.smartnus.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.smartnus.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.smartnus.logic.commands.CommandTestUtil.QUESTION_DESC_3;
import static seedu.smartnus.logic.commands.CommandTestUtil.TAG_DESC_3;
import static seedu.smartnus.logic.commands.CommandTestUtil.TAG_DESC_4;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_IMPORTANCE_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_IMPORTANCE_2;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_QUESTION_3;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_TAG_3;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_TAG_4;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.smartnus.testutil.TypicalIndexes.INDEX_FIRST_QUESTION;
import static seedu.smartnus.testutil.TypicalIndexes.INDEX_SECOND_QUESTION;
import static seedu.smartnus.testutil.TypicalIndexes.INDEX_THIRD_QUESTION;

import org.junit.jupiter.api.Test;

import seedu.smartnus.commons.core.index.Index;
import seedu.smartnus.logic.commands.EditCommand;
import seedu.smartnus.logic.commands.EditCommand.EditQuestionDescriptor;
import seedu.smartnus.model.question.Importance;
import seedu.smartnus.model.question.Name;
import seedu.smartnus.model.tag.Tag;
import seedu.smartnus.testutil.EditQuestionDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_QUESTION_3, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + QUESTION_DESC_3, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + QUESTION_DESC_3, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 b/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "11" + INVALID_IMPORTANCE_DESC,
                Importance.MESSAGE_CONSTRAINTS); // invalid importance
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid importance followed by valid email
        assertParseFailure(parser, "11" + INVALID_IMPORTANCE_DESC, Importance.MESSAGE_CONSTRAINTS);

        // valid importance followed by invalid importance. The test case for invalid importance followed by
        // valid importance is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + IMPORTANCE_DESC_2 + INVALID_IMPORTANCE_DESC,
                Importance.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Question} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_4 + TAG_DESC_3 + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_4 + TAG_EMPTY + TAG_DESC_3, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_4 + TAG_DESC_3, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_IMPORTANCE_DESC,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_QUESTION;
        String userInput = targetIndex.getOneBased() + IMPORTANCE_DESC_2 + TAG_DESC_3
                + QUESTION_DESC_3 + TAG_DESC_4;

        EditQuestionDescriptor descriptor = new EditQuestionDescriptorBuilder().withName(VALID_QUESTION_3)
                .withImportance(VALID_IMPORTANCE_2)
                .withTags(VALID_TAG_3, VALID_TAG_4).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_QUESTION;
        String userInput = targetIndex.getOneBased() + IMPORTANCE_DESC_2;

        EditQuestionDescriptor descriptor = new EditQuestionDescriptorBuilder().withImportance(VALID_IMPORTANCE_2)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_QUESTION;
        String userInput = targetIndex.getOneBased() + QUESTION_DESC_3;
        EditCommand.EditQuestionDescriptor descriptor = new EditQuestionDescriptorBuilder()
                .withName(VALID_QUESTION_3).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // importance
        userInput = targetIndex.getOneBased() + IMPORTANCE_DESC_1;
        descriptor = new EditQuestionDescriptorBuilder().withImportance(VALID_IMPORTANCE_1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_4;
        descriptor = new EditQuestionDescriptorBuilder().withTags(VALID_TAG_4).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_QUESTION;
        String userInput = targetIndex.getOneBased() + IMPORTANCE_DESC_1
                + TAG_DESC_4 + IMPORTANCE_DESC_1 + TAG_DESC_4
                + IMPORTANCE_DESC_2 + TAG_DESC_3;

        EditQuestionDescriptor descriptor = new EditQuestionDescriptorBuilder().withImportance(VALID_IMPORTANCE_2)
                .withTags(VALID_TAG_4, VALID_TAG_3)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_QUESTION;
        String userInput = targetIndex.getOneBased() + INVALID_IMPORTANCE_DESC + IMPORTANCE_DESC_2;
        EditCommand.EditQuestionDescriptor descriptor = new EditQuestionDescriptorBuilder()
                .withImportance(VALID_IMPORTANCE_2).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_IMPORTANCE_DESC + IMPORTANCE_DESC_2;
        descriptor = new EditQuestionDescriptorBuilder().withImportance(VALID_IMPORTANCE_2).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_QUESTION;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditCommand.EditQuestionDescriptor descriptor = new EditQuestionDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
