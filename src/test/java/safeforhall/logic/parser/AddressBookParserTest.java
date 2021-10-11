package safeforhall.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static safeforhall.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static safeforhall.testutil.Assert.assertThrows;
import static safeforhall.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
//import static safeforhall.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import safeforhall.commons.core.index.Index;
import safeforhall.logic.commands.AddCommand;
import safeforhall.logic.commands.ClearCommand;
import safeforhall.logic.commands.DeleteCommand;
//import safeforhall.logic.commands.EditCommand;
import safeforhall.logic.commands.ExitCommand;
import safeforhall.logic.commands.FindCommand;
import safeforhall.logic.commands.FindCommand.FindCompositePredicate;
import safeforhall.logic.commands.HelpCommand;
import safeforhall.logic.commands.ListCommand;
import safeforhall.logic.commands.ViewCommand;
import safeforhall.logic.parser.exceptions.ParseException;
import safeforhall.model.person.LastDate;
import safeforhall.model.person.Name;
import safeforhall.model.person.Person;
import safeforhall.model.person.Room;
import safeforhall.model.person.VaccStatus;
//import safeforhall.testutil.EditPersonDescriptorBuilder;
import safeforhall.testutil.PersonBuilder;
import safeforhall.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        ArrayList<Index> indexArray = new ArrayList<>();
        indexArray.add(INDEX_FIRST_PERSON);
        assertEquals(new DeleteCommand(indexArray), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        // TODO : Fix after edit command is done
        /*Person person = new PersonBuilder().build();
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);*/
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        String joint = keywords.stream().collect(Collectors.joining(" "));
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " "
                        + CliSyntax.PREFIX_NAME + joint + " "
                        + CliSyntax.PREFIX_ROOM + "A100" + " "
                        + CliSyntax.PREFIX_VACCSTATUS + "T");

        FindCompositePredicate predicate = new FindCompositePredicate();
        predicate.setName(new Name(joint));
        predicate.setRoom(new Room("A100"));
        predicate.setVaccStatus(new VaccStatus("T"));

        assertEquals(new FindCommand(predicate), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        ListCommand command = (ListCommand) parser.parseCommand(
                ListCommand.COMMAND_WORD + " k/c d1/10-10-2021");
        assertEquals(new ListCommand("c", new LastDate("10-10-2021")), command);
    }

    @Test
    public void parseCommand_view() throws Exception {
        assertTrue(parser.parseCommand(ViewCommand.COMMAND_WORD) instanceof ViewCommand);
        assertTrue(parser.parseCommand(ViewCommand.COMMAND_WORD + " 3") instanceof ViewCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
