package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.Venue;

public class AddTaskParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    void parse_validArgs_returnsAddTaskCommand() {
        List<Task> taskList1 = new ArrayList<>();
        taskList1.add(new Task(new TaskName("play"), null, null, null));
        List<Task> taskList2 = new ArrayList<>();
        taskList2.add(new Task(new TaskName("play"), null, null, null));
        taskList2.add(new Task(new TaskName("sleep"), null, null, new Venue("Home")));
        assertParseSuccess(parser, "1 -tn play", new AddTaskCommand(INDEX_FIRST_PERSON,
                taskList1));
        assertParseSuccess(parser, "1 -tn play -tn sleep -ta Home", new AddTaskCommand(INDEX_FIRST_PERSON,
                taskList2));
    }

    @Test
    void parse_invalidArgs_throwParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddTaskCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "1 tas/a", expectedMessage);
    }
}
