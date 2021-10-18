package dash.logic.parser.taskcommand;

import static dash.logic.parser.CommandParserTestUtil.assertParseFailureWithPersonList;
import static dash.logic.parser.CommandParserTestUtil.assertParseSuccessWithPersonList;

import org.junit.jupiter.api.Test;

import dash.commons.core.Messages;
import dash.logic.commands.CommandTestUtil;
import dash.logic.commands.taskcommand.AddTaskCommand;
import dash.model.person.Person;
import dash.model.tag.Tag;
import dash.model.task.Task;
import dash.model.task.TaskDescription;
import dash.testutil.TaskBuilder;
import dash.testutil.TypicalPersons;
import dash.testutil.TypicalTasks;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();
    private ObservableList<Person> people = FXCollections.observableList(TypicalPersons.getTypicalPersons());

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask =
                new TaskBuilder(TypicalTasks.ASSIGNMENT)
                        .withTags(CommandTestUtil.VALID_TAG_HOMEWORK)
                        .withPeople()
                        .build();

        // whitespace only preamble
        assertParseSuccessWithPersonList(parser,
                CommandTestUtil.PREAMBLE_WHITESPACE + CommandTestUtil.TASK_DESC_ASSIGNMENT
                        + CommandTestUtil.TAG_DESC_HOMEWORK,
                people,
                new AddTaskCommand(expectedTask));

        // multiple task descriptions - last task description accepted
        assertParseSuccessWithPersonList(parser,
                CommandTestUtil.TASK_DESC_QUIZ + CommandTestUtil.TASK_DESC_ASSIGNMENT
                        + CommandTestUtil.TAG_DESC_HOMEWORK,
                people,
                new AddTaskCommand(expectedTask));

        // multiple tags - all accepted
        Task expectedTaskMultipleTags =
                new TaskBuilder(TypicalTasks.PR_REVIEW)
                        .withTags(CommandTestUtil.VALID_TAG_GROUPWORK, CommandTestUtil.VALID_TAG_UNGRADED)
                        .withPeople()
                        .build();
        assertParseSuccessWithPersonList(parser,
                CommandTestUtil.TASK_DESC_PR_REVIEW + CommandTestUtil.TAG_DESC_GROUPWORK
                        + CommandTestUtil.TAG_DESC_UNGRADED,
                people,
                new AddTaskCommand(expectedTaskMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Task expectedTask = new TaskBuilder(TypicalTasks.LECTURE).withTags().build();
        assertParseSuccessWithPersonList(parser,
                CommandTestUtil.TASK_DESC_LECTURE,
                people,
                new AddTaskCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // missing task description prefix
        assertParseFailureWithPersonList(parser,
                CommandTestUtil.VALID_TASK_DESCRIPTION_ASSIGNMENT,
                people,
                expectedMessage);

        // all prefixes missing
        assertParseFailureWithPersonList(parser,
                CommandTestUtil.VALID_TASK_DESCRIPTION_ASSIGNMENT + CommandTestUtil.VALID_TAG_HOMEWORK,
                people,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid task description
        assertParseFailureWithPersonList(parser,
                CommandTestUtil.INVALID_TASK_DESC
                        + CommandTestUtil.TAG_DESC_UNGRADED,
                people,
                TaskDescription.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailureWithPersonList(parser,
                CommandTestUtil.TASK_DESC_LECTURE + CommandTestUtil.INVALID_TAG_DESC
                        + CommandTestUtil.VALID_TAG_UNGRADED,
                people,
                Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailureWithPersonList(parser,
                CommandTestUtil.INVALID_TASK_DESC + CommandTestUtil.INVALID_TAG_DESC,
                people,
                TaskDescription.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailureWithPersonList(parser,
                CommandTestUtil.PREAMBLE_NON_EMPTY + CommandTestUtil.TASK_DESC_QUIZ
                        + CommandTestUtil.TAG_DESC_QUIZ,
                people,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        AddTaskCommand.MESSAGE_USAGE));
    }
}
