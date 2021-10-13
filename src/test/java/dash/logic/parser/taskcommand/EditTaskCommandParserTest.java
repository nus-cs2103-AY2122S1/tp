package dash.logic.parser.taskcommand;

import org.junit.jupiter.api.Test;

import dash.commons.core.Messages;
import dash.commons.core.index.Index;
import dash.logic.commands.CommandTestUtil;
import dash.logic.commands.taskcommand.EditTaskCommand;
import dash.logic.parser.CliSyntax;
import dash.logic.parser.CommandParserTestUtil;
import dash.model.tag.Tag;
import dash.model.task.TaskDescription;
import dash.testutil.EditTaskDescriptorBuilder;
import dash.testutil.TypicalIndexes;

class EditTaskCommandParserTest {

    private static final String TAG_EMPTY = " " + CliSyntax.PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

    private EditTaskCommandParser parser = new EditTaskCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.VALID_TASK_DESCRIPTION_TP,
                MESSAGE_INVALID_FORMAT);

        // no field specified
        CommandParserTestUtil.assertParseFailure(parser, "1", EditTaskCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        CommandParserTestUtil.assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        CommandParserTestUtil.assertParseFailure(parser, "-5" + CommandTestUtil.TASK_DESC_ASSIGNMENT,
                MESSAGE_INVALID_FORMAT);

        // zero index
        CommandParserTestUtil.assertParseFailure(parser, "0" + CommandTestUtil.TASK_DESC_ASSIGNMENT,
                MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        CommandParserTestUtil.assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        CommandParserTestUtil.assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        CommandParserTestUtil.assertParseFailure(parser, "1" + CommandTestUtil.INVALID_TASK_DESC,
                TaskDescription.MESSAGE_CONSTRAINTS); // invalid name
        CommandParserTestUtil.assertParseFailure(parser, "1" + CommandTestUtil.INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid task description
        CommandParserTestUtil.assertParseFailure(parser,
                "1" + CommandTestUtil.INVALID_TASK_DESC, TaskDescription.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        CommandParserTestUtil.assertParseFailure(parser,
                "1" + CommandTestUtil.TAG_DESC_UNGRADED + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        CommandParserTestUtil.assertParseFailure(parser,
                "1" + CommandTestUtil.INVALID_TASK_DESC + CommandTestUtil.TAG_DESC_UNGRADED + TAG_EMPTY,
                TaskDescription.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = TypicalIndexes.INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.TASK_DESC_ASSIGNMENT
                + CommandTestUtil.TAG_DESC_UNGRADED;

        EditTaskCommand.EditTaskDescriptor descriptor =
                new EditTaskDescriptorBuilder().withTaskDescription(CommandTestUtil.VALID_TASK_DESCRIPTION_ASSIGNMENT)
                        .withTags(CommandTestUtil.VALID_TAG_UNGRADED).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldsSpecified_success() {
        Index targetIndex = TypicalIndexes.INDEX_FIRST;

        // task description
        String userInput = targetIndex.getOneBased() + CommandTestUtil.TASK_DESC_ASSIGNMENT;

        EditTaskCommand.EditTaskDescriptor descriptor =
                new EditTaskDescriptorBuilder()
                        .withTaskDescription(CommandTestUtil.VALID_TASK_DESCRIPTION_ASSIGNMENT).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + CommandTestUtil.TAG_DESC_QUIZ;

        descriptor =
                new EditTaskDescriptorBuilder().withTags(CommandTestUtil.VALID_TAG_QUIZ).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = TypicalIndexes.INDEX_FIRST;
        String userInput =
                targetIndex.getOneBased() + CommandTestUtil.TASK_DESC_LECTURE + CommandTestUtil.TAG_DESC_UNGRADED
                        + CommandTestUtil.TASK_DESC_ASSIGNMENT + CommandTestUtil.TAG_DESC_QUIZ;

        EditTaskCommand.EditTaskDescriptor descriptor =
                new EditTaskDescriptorBuilder().withTaskDescription(CommandTestUtil.VALID_TASK_DESCRIPTION_ASSIGNMENT)
                        .withTags(CommandTestUtil.VALID_TAG_UNGRADED, CommandTestUtil.VALID_TAG_QUIZ).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = TypicalIndexes.INDEX_FIRST;
        String userInput =
                targetIndex.getOneBased() + CommandTestUtil.INVALID_TASK_DESC + CommandTestUtil.TASK_DESC_ASSIGNMENT;
        EditTaskCommand.EditTaskDescriptor descriptor =
                new EditTaskDescriptorBuilder()
                        .withTaskDescription(CommandTestUtil.VALID_TASK_DESCRIPTION_ASSIGNMENT).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput =
                targetIndex.getOneBased() + CommandTestUtil.INVALID_TASK_DESC + CommandTestUtil.TAG_DESC_GROUPWORK
                        + CommandTestUtil.TASK_DESC_ASSIGNMENT;
        descriptor =
                new EditTaskDescriptorBuilder().withTaskDescription(CommandTestUtil.VALID_TASK_DESCRIPTION_ASSIGNMENT)
                        .withTags(CommandTestUtil.VALID_TAG_GROUPWORK).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = TypicalIndexes.INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTags().build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

}
