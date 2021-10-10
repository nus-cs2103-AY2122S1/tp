package dash.logic.parser;

import static dash.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static dash.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import dash.logic.commands.ExitCommand;
import dash.logic.commands.HelpCommand;
import dash.logic.commands.SwitchTabHelpCommand;
import dash.logic.commands.personcommand.AddPersonCommand;
import dash.logic.commands.personcommand.ClearPeopleCommand;
import dash.logic.commands.personcommand.DeletePersonCommand;
import dash.logic.commands.personcommand.EditPersonCommand;
import dash.logic.commands.personcommand.EditPersonCommand.EditPersonDescriptor;
import dash.logic.commands.personcommand.FindPersonCommand;
import dash.logic.commands.personcommand.FindPersonCommand.FindPersonDescriptor;
import dash.logic.commands.personcommand.ListPeopleCommand;
import dash.logic.parser.exceptions.ParseException;
import dash.model.person.Person;
import dash.testutil.Assert;
import dash.testutil.EditPersonDescriptorBuilder;
import dash.testutil.PersonBuilder;
import dash.testutil.PersonUtil;
import dash.testutil.TypicalIndexes;

public class ContactsTabParserTest {

    private final ContactsTabParser parser = new ContactsTabParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddPersonCommand command = (AddPersonCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddPersonCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearPeopleCommand.COMMAND_WORD) instanceof ClearPeopleCommand);
        assertTrue(parser.parseCommand(ClearPeopleCommand.COMMAND_WORD + " 3") instanceof ClearPeopleCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeletePersonCommand command = (DeletePersonCommand) parser.parseCommand(
                DeletePersonCommand.COMMAND_WORD + " " + TypicalIndexes.INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeletePersonCommand(TypicalIndexes.INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditPersonCommand command = (EditPersonCommand) parser.parseCommand(EditPersonCommand.COMMAND_WORD + " "
                + TypicalIndexes.INDEX_FIRST_PERSON.getOneBased() + " "
                + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditPersonCommand(TypicalIndexes.INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindPersonCommand command = (FindPersonCommand) parser.parseCommand(
                FindPersonCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        FindPersonDescriptor findPersonDescriptor = new FindPersonDescriptor();
        findPersonDescriptor.setName(keywords);
        assertEquals(new FindPersonCommand(findPersonDescriptor), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(SwitchTabHelpCommand.COMMAND_WORD) instanceof SwitchTabHelpCommand);
        assertTrue(parser.parseCommand(SwitchTabHelpCommand.COMMAND_WORD + " 3") instanceof SwitchTabHelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListPeopleCommand.COMMAND_WORD) instanceof ListPeopleCommand);
        assertTrue(parser.parseCommand(ListPeopleCommand.COMMAND_WORD + " 3") instanceof ListPeopleCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        Assert.assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        Assert.assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
