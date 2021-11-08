package dash.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dash.logic.commands.ExitCommand;
import dash.logic.commands.SwitchTabContactsCommand;
import dash.logic.commands.SwitchTabHelpCommand;
import dash.logic.commands.SwitchTabTasksCommand;
import dash.logic.commands.taskcommand.AddTaskCommand;
import dash.logic.commands.taskcommand.AssignPeopleCommand;
import dash.logic.commands.taskcommand.ClearDoneTaskCommand;
import dash.logic.commands.taskcommand.ClearTaskCommand;
import dash.logic.commands.taskcommand.CompleteTaskCommand;
import dash.logic.commands.taskcommand.DeleteTaskCommand;
import dash.logic.commands.taskcommand.EditTaskCommand;
import dash.logic.commands.taskcommand.FindTaskCommand;
import dash.logic.commands.taskcommand.ListTaskCommand;
import dash.logic.commands.taskcommand.TagTaskCommand;
import dash.logic.commands.taskcommand.UpcomingTaskCommand;
import dash.logic.parser.exceptions.ParseException;
import dash.model.person.Person;
import dash.testutil.Assert;
import dash.testutil.TypicalPersons;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

class TaskTabParserTest {

    private final Person newPerson = TypicalPersons.AMY;
    private final ObservableList<Person> persons = FXCollections.observableArrayList(newPerson);
    private final TaskTabParser parser = new TaskTabParser();

    @Test
    public void parseCommand_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> parser.parseCommand("  ", persons));
    }

    @Test
    public void parseCommand_addTaskCommand() throws Exception {
        assertTrue(parser.parseCommand(AddTaskCommand.COMMAND_WORD + " d/Test",
                persons) instanceof AddTaskCommand);
    }

    @Test
    public void parseCommand_editTaskCommand() throws Exception {
        assertTrue(parser.parseCommand(EditTaskCommand.COMMAND_WORD + " 1 d/Test",
                persons) instanceof EditTaskCommand);
    }

    @Test
    public void parseCommand_deleteTaskCommand() throws Exception {
        assertTrue(parser.parseCommand(DeleteTaskCommand.COMMAND_WORD + " 1",
                persons) instanceof DeleteTaskCommand);
    }

    @Test
    public void parseCommand_tagTaskCommand() throws Exception {
        assertTrue(parser.parseCommand(TagTaskCommand.COMMAND_WORD + " 1 t/Tag",
                persons) instanceof TagTaskCommand);
    }

    @Test
    public void parseCommand_assignPersonCommand() throws Exception {
        assertTrue(parser.parseCommand(AssignPeopleCommand.COMMAND_WORD + " 1 p/1",
                persons) instanceof AssignPeopleCommand);
    }

    @Test
    public void parseCommand_exitCommand() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD, persons) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 1", persons) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findTaskCommand() throws Exception {
        assertTrue(parser.parseCommand(FindTaskCommand.COMMAND_WORD + " 1 d/Test",
                persons) instanceof FindTaskCommand);
    }

    @Test
    public void parseCommand_listTaskCommand() throws Exception {
        assertTrue(parser.parseCommand(ListTaskCommand.COMMAND_WORD, persons) instanceof ListTaskCommand);
        assertTrue(parser.parseCommand(ListTaskCommand.COMMAND_WORD + " 1",
                persons) instanceof ListTaskCommand);
    }

    @Test
    public void parseCommand_clearTaskCommand() throws Exception {
        assertTrue(parser.parseCommand(ClearTaskCommand.COMMAND_WORD, persons) instanceof ClearTaskCommand);
        assertTrue(parser.parseCommand(ClearTaskCommand.COMMAND_WORD + " 1",
                persons) instanceof ClearTaskCommand);
    }

    @Test
    public void parseCommand_clearDoneTaskCommand() throws Exception {
        assertTrue(parser.parseCommand(ClearDoneTaskCommand.COMMAND_WORD + " d/Test",
                persons) instanceof ClearDoneTaskCommand);
    }

    @Test
    public void parseCommand_completeTaskCommand() throws Exception {
        assertTrue(parser.parseCommand(CompleteTaskCommand.COMMAND_WORD + " 1",
                persons) instanceof CompleteTaskCommand);
    }

    @Test
    public void parseCommand_upcomingTaskCommand() throws Exception {
        assertTrue(parser.parseCommand(UpcomingTaskCommand.COMMAND_WORD,
                persons) instanceof UpcomingTaskCommand);
        assertTrue(parser.parseCommand(UpcomingTaskCommand.COMMAND_WORD + " 1",
                persons) instanceof UpcomingTaskCommand);
    }

    @Test
    public void parseCommand_switchTabContactsCommand() throws Exception {
        assertTrue(parser.parseCommand(SwitchTabContactsCommand.COMMAND_WORD,
                persons) instanceof SwitchTabContactsCommand);
        assertTrue(parser.parseCommand(SwitchTabContactsCommand.COMMAND_WORD + " 1",
                persons) instanceof SwitchTabContactsCommand);
    }

    @Test
    public void parseCommand_switchTabTasksCommand() throws Exception {
        assertTrue(parser.parseCommand(SwitchTabTasksCommand.COMMAND_WORD,
                persons) instanceof SwitchTabTasksCommand);
        assertTrue(parser.parseCommand(SwitchTabTasksCommand.COMMAND_WORD + " 1",
                persons) instanceof SwitchTabTasksCommand);
    }

    @Test
    public void parseCommand_switchTabHelpCommand() throws Exception {
        assertTrue(parser.parseCommand(SwitchTabHelpCommand.COMMAND_WORD,
                persons) instanceof SwitchTabHelpCommand);
        assertTrue(parser.parseCommand(SwitchTabHelpCommand.COMMAND_WORD + " 1",
                persons) instanceof SwitchTabHelpCommand);
    }
}
