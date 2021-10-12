package dash.logic.parser.taskcommand;

import org.junit.jupiter.api.Test;

import dash.commons.core.Messages;
import dash.logic.commands.CommandTestUtil;
import dash.logic.commands.taskcommand.AddTaskCommand;
import dash.logic.parser.CommandParserTestUtil;
import dash.model.tag.Tag;
import dash.model.task.Task;
import dash.model.task.TaskDescription;
import dash.testutil.TaskBuilder;
import dash.testutil.TypicalTasks;

public class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask =
                new TaskBuilder(TypicalTasks.ASSIGNMENT).withTags(CommandTestUtil.VALID_TAG_HOMEWORK).build();

        // whitespace only preamble
        CommandParserTestUtil.assertParseSuccess(parser,
                CommandTestUtil.PREAMBLE_WHITESPACE + CommandTestUtil.TASK_DESC_ASSIGNMENT
                + CommandTestUtil.TAG_DESC_HOMEWORK,
                new AddTaskCommand(expectedTask));

        // multiple task descriptions - last task description accepted
        CommandParserTestUtil.assertParseSuccess(parser,
                CommandTestUtil.TASK_DESC_QUIZ + CommandTestUtil.TASK_DESC_ASSIGNMENT
                        + CommandTestUtil.TAG_DESC_HOMEWORK,
                new AddTaskCommand(expectedTask));

        // multiple tags - all accepted
        Task expectedTaskMultipleTags =
                new TaskBuilder(TypicalTasks.PR_REVIEW).withTags(CommandTestUtil.VALID_TAG_GROUPWORK,
                        CommandTestUtil.VALID_TAG_UNGRADED)
                        .build();
        CommandParserTestUtil.assertParseSuccess(parser,
                CommandTestUtil.TASK_DESC_PR_REVIEW + CommandTestUtil.TAG_DESC_GROUPWORK
                        + CommandTestUtil.TAG_DESC_UNGRADED,
                new AddTaskCommand(expectedTaskMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Task expectedTask = new TaskBuilder(TypicalTasks.LECTURE).withTags().build();
        CommandParserTestUtil.assertParseSuccess(parser,
                CommandTestUtil.TASK_DESC_LECTURE,
                new AddTaskCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // missing task description prefix
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.VALID_TASK_DESCRIPTION_ASSIGNMENT,
                expectedMessage);

        // all prefixes missing
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.VALID_TASK_DESCRIPTION_ASSIGNMENT + CommandTestUtil.VALID_TAG_HOMEWORK,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid task description
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.INVALID_TASK_DESC
                        + CommandTestUtil.TAG_DESC_UNGRADED, TaskDescription.MESSAGE_CONSTRAINTS);

        // invalid tag
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.TASK_DESC_LECTURE + CommandTestUtil.INVALID_TAG_DESC
                        + CommandTestUtil.VALID_TAG_UNGRADED, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.INVALID_TASK_DESC + CommandTestUtil.INVALID_TAG_DESC,
                TaskDescription.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.PREAMBLE_NON_EMPTY + CommandTestUtil.TASK_DESC_QUIZ
                        + CommandTestUtil.TAG_DESC_QUIZ,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
    }
}
