package nustracker.logic.parser;

import static nustracker.logic.parser.CliSyntax.PREFIX_EVENT;
import static nustracker.logic.parser.CliSyntax.PREFIX_NAME;
import static nustracker.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static nustracker.testutil.TypicalEvents.EVENTNAME_ONE;
import static nustracker.testutil.TypicalStudents.STUDENTID_ONE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import nustracker.commons.core.Messages;
import nustracker.logic.commands.AddCommand;
import nustracker.logic.commands.BlackListCommand;
import nustracker.logic.commands.CreateCommand;
import nustracker.logic.commands.DeleteCommand;
import nustracker.logic.commands.DeleteEventCommand;
import nustracker.logic.commands.DeleteFilteredStudentsCommand;
import nustracker.logic.commands.DeleteStudentCommand;
import nustracker.logic.commands.EditCommand;
import nustracker.logic.commands.EventsCommand;
import nustracker.logic.commands.ExitCommand;
import nustracker.logic.commands.FilterCommand;
import nustracker.logic.commands.FilterEventCommand;
import nustracker.logic.commands.FilterIdCommand;
import nustracker.logic.commands.FilterNameCommand;
import nustracker.logic.commands.HelpCommand;
import nustracker.logic.commands.RefreshCommand;
import nustracker.logic.commands.SettingsCommand;
import nustracker.logic.commands.StudentsCommand;
import nustracker.logic.commands.ThemeCommand;
import nustracker.logic.commands.WhiteListCommand;
import nustracker.logic.parser.exceptions.ParseException;
import nustracker.model.event.Event;
import nustracker.model.event.EventName;
import nustracker.model.student.NameContainsKeywordsPredicate;
import nustracker.model.student.Student;
import nustracker.model.student.StudentId;
import nustracker.model.student.StudentIdContainsKeywordsPredicate;
import nustracker.testutil.Assert;
import nustracker.testutil.EditStudentDescriptorBuilder;
import nustracker.testutil.EventBuilder;
import nustracker.testutil.EventUtil;
import nustracker.testutil.StudentBuilder;
import nustracker.testutil.StudentUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Student student = new StudentBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(StudentUtil.getAddCommand(student));
        assertEquals(new AddCommand(student), command);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteStudentCommand studentCommand = (DeleteStudentCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + PREFIX_STUDENTID + STUDENTID_ONE);
        assertEquals(new DeleteStudentCommand(STUDENTID_ONE), studentCommand);

        DeleteEventCommand eventCommand = (DeleteEventCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + PREFIX_EVENT + EVENTNAME_ONE);
        assertEquals(new DeleteEventCommand(EVENTNAME_ONE), eventCommand);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Student student = new StudentBuilder().build();
        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(student).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + PREFIX_STUDENTID + STUDENTID_ONE.getStudentIdString() + " "
                + StudentUtil.getEditStudentDescriptorDetails(descriptor));
        assertEquals(new EditCommand(STUDENTID_ONE, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_filter() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");

        FilterCommand nameCommand = (FilterCommand) parser.parseCommand(
                FilterCommand.COMMAND_WORD + " " + PREFIX_NAME + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FilterNameCommand(new NameContainsKeywordsPredicate(keywords)), nameCommand);

        List<String> ids = Arrays.asList("e0000000", "e0000001", "e0000002");

        FilterCommand idCommand = (FilterCommand) parser.parseCommand(
                FilterCommand.COMMAND_WORD + " " + PREFIX_STUDENTID + ids.stream().collect(
                        Collectors.joining(" ")));
        assertEquals(new FilterIdCommand(new StudentIdContainsKeywordsPredicate(ids.stream().map(
            x -> new StudentId(x)).collect(Collectors.toUnmodifiableList()))), idCommand);

        FilterCommand eventCommand = (FilterCommand) parser.parseCommand(
                FilterCommand.COMMAND_WORD + " " + PREFIX_EVENT + "foo");
        assertEquals(new FilterEventCommand(new EventName("foo")), eventCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_create() throws Exception {
        Event event = new EventBuilder().build();
        CreateCommand command = (CreateCommand) parser.parseCommand(EventUtil.getCreateCommand(event));
        assertEquals(new CreateCommand(event), command);
    }

    @Test
    public void parseCommand_blacklist() throws Exception {
        Event event = new EventBuilder().build();
        BlackListCommand command = (BlackListCommand) parser.parseCommand(
                BlackListCommand.COMMAND_WORD + " "
                        + PREFIX_STUDENTID + STUDENTID_ONE + " "
                        + PREFIX_EVENT + EVENTNAME_ONE);
        assertEquals(new BlackListCommand(STUDENTID_ONE, EVENTNAME_ONE), command);
    }

    @Test
    public void parseCommand_whitelist() throws Exception {
        Event event = new EventBuilder().build();
        WhiteListCommand command = (WhiteListCommand) parser.parseCommand(
                WhiteListCommand.COMMAND_WORD + " "
                        + PREFIX_STUDENTID + STUDENTID_ONE + " "
                        + PREFIX_EVENT + EVENTNAME_ONE);
        assertEquals(new WhiteListCommand(STUDENTID_ONE, EVENTNAME_ONE), command);
    }

    @Test
    public void parseCommand_students() throws Exception {
        assertTrue(parser.parseCommand(StudentsCommand.COMMAND_WORD) instanceof StudentsCommand);
        assertTrue(parser.parseCommand(StudentsCommand.COMMAND_WORD + " 3") instanceof StudentsCommand);
    }

    @Test
    public void parseCommand_events() throws Exception {
        assertTrue(parser.parseCommand(EventsCommand.COMMAND_WORD) instanceof EventsCommand);
        assertTrue(parser.parseCommand(EventsCommand.COMMAND_WORD + " 3") instanceof EventsCommand);
    }

    @Test
    public void parseCommand_refresh() throws Exception {
        assertTrue(parser.parseCommand(RefreshCommand.COMMAND_WORD) instanceof RefreshCommand);
        assertTrue(parser.parseCommand(RefreshCommand.COMMAND_WORD + " 3") instanceof RefreshCommand);
    }

    @Test
    public void themeCommand_events() throws Exception {
        assertTrue(parser.parseCommand(ThemeCommand.COMMAND_WORD) instanceof ThemeCommand);
        assertTrue(parser.parseCommand(ThemeCommand.COMMAND_WORD + " 3") instanceof ThemeCommand);
    }

    @Test
    public void settingsCommand_events() throws Exception {
        assertTrue(parser.parseCommand(SettingsCommand.COMMAND_WORD) instanceof SettingsCommand);
        assertTrue(parser.parseCommand(SettingsCommand.COMMAND_WORD + " 3") instanceof SettingsCommand);
    }

    @Test
    public void deleteFilteredStudentsCommand_events() throws Exception {
        assertTrue(parser.parseCommand(DeleteFilteredStudentsCommand.COMMAND_WORD)
                instanceof DeleteFilteredStudentsCommand);
        assertTrue(parser.parseCommand(DeleteFilteredStudentsCommand.COMMAND_WORD + " 3")
                instanceof DeleteFilteredStudentsCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        Assert.assertThrows(ParseException.class, String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        HelpCommand.MESSAGE_USAGE), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        Assert.assertThrows(ParseException.class, Messages.MESSAGE_UNKNOWN_COMMAND, () ->
                parser.parseCommand("unknownCommand"));
    }
}
