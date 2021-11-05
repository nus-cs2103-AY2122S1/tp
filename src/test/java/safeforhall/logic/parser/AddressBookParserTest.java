package safeforhall.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static safeforhall.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static safeforhall.logic.commands.sort.SortPersonCommand.ASCENDING;
import static safeforhall.logic.parser.CliSyntax.PREFIX_ORDER;
import static safeforhall.logic.parser.CliSyntax.PREFIX_SORT;
import static safeforhall.testutil.Assert.assertThrows;
import static safeforhall.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import safeforhall.commons.core.index.Index;
import safeforhall.logic.commands.ClearCommand;
import safeforhall.logic.commands.DeadlineCommand;
import safeforhall.logic.commands.ExcludeCommand;
import safeforhall.logic.commands.ExitCommand;
import safeforhall.logic.commands.ExportCommand;
import safeforhall.logic.commands.HelpCommand;
import safeforhall.logic.commands.ImportCommand;
import safeforhall.logic.commands.IncludeCommand;
import safeforhall.logic.commands.TraceCommand;
import safeforhall.logic.commands.add.AddPersonCommand;
import safeforhall.logic.commands.delete.DeletePersonCommand;
import safeforhall.logic.commands.edit.EditPersonCommand;
import safeforhall.logic.commands.edit.EditPersonCommand.EditPersonDescriptor;
import safeforhall.logic.commands.find.FindPersonCommand;
import safeforhall.logic.commands.find.FindPersonCommand.FindCompositePredicate;
import safeforhall.logic.commands.sort.SortEventCommand;
import safeforhall.logic.commands.sort.SortPersonCommand;
import safeforhall.logic.commands.view.ViewEventCommand;
import safeforhall.logic.commands.view.ViewPersonCommand;
import safeforhall.logic.parser.exceptions.ParseException;
import safeforhall.model.event.EventName;
import safeforhall.model.event.ResidentList;
import safeforhall.model.person.LastDate;
import safeforhall.model.person.Name;
import safeforhall.model.person.Person;
import safeforhall.model.person.VaccStatus;
import safeforhall.testutil.EditPersonDescriptorBuilder;
import safeforhall.testutil.PersonBuilder;
import safeforhall.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddPersonCommand command = (AddPersonCommand) parser.parseCommand(PersonUtil.getAddCommand(person), true);
        assertEquals(new AddPersonCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD, true) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3", true) instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeletePersonCommand command = (DeletePersonCommand) parser.parseCommand(
                DeletePersonCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased(), true);
        ArrayList<Index> indexArray = new ArrayList<>();
        indexArray.add(INDEX_FIRST_PERSON);
        assertEquals(new DeletePersonCommand(indexArray), command);
    }

    @Test
    public void parseCommand_import() throws Exception {
        ImportCommand command = (ImportCommand) parser.parseCommand(
                ImportCommand.COMMAND_WORD + " safeforhall", true);
        assertEquals(new ImportCommand("safeforhall"), command);
    }

    @Test
    public void parseCommand_trace() throws Exception {
        TraceCommand command = (TraceCommand) parser.parseCommand(
                TraceCommand.COMMAND_WORD + " r/A123", true);
        assertEquals(new TraceCommand("A123"), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditPersonCommand command = (EditPersonCommand) parser.parseCommand(EditPersonCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " "
                + PersonUtil.getEditPersonDescriptorDetails(descriptor), true);
        ArrayList<Index> list = new ArrayList<>();
        list.add(INDEX_FIRST_PERSON);
        assertEquals(new EditPersonCommand(list, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD, true) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3", true) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        String joint = keywords.stream().collect(Collectors.joining(" "));
        FindPersonCommand command = (FindPersonCommand) parser.parseCommand(
                FindPersonCommand.COMMAND_WORD + " "
                        + CliSyntax.PREFIX_NAME + joint + " "
                        + CliSyntax.PREFIX_ROOM + "A100" + " "
                        + CliSyntax.PREFIX_VACCSTATUS + "T", true);

        FindCompositePredicate predicate = new FindCompositePredicate();
        predicate.setName(new Name(joint));
        predicate.setRoom("A100");
        predicate.setVaccStatus(new VaccStatus("T"));

        assertEquals(new FindPersonCommand(predicate), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, true) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3", true) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        DeadlineCommand command = (DeadlineCommand) parser.parseCommand(
                DeadlineCommand.COMMAND_WORD + " k/c d1/10-10-2021 d2/12-10-2021", true);
        assertEquals(new DeadlineCommand("c", new LastDate("10-10-2021"), new LastDate("12-10-2021")), command);
    }

    @Test
    public void parseCommand_view() throws Exception {
        assertTrue(parser.parseCommand(ViewPersonCommand.COMMAND_WORD, true) instanceof ViewPersonCommand);
        assertTrue(parser.parseCommand(ViewPersonCommand.COMMAND_WORD + " 3", true) instanceof ViewPersonCommand);

        assertTrue(parser.parseCommand(ViewEventCommand.COMMAND_WORD, false) instanceof ViewEventCommand);
        assertTrue(parser.parseCommand(ViewEventCommand.COMMAND_WORD + " 3", false) instanceof ViewEventCommand);
    }

    @Test
    public void parseCommand_include() throws Exception {
        IncludeCommand command = (IncludeCommand) parser.parseCommand(
                IncludeCommand.COMMAND_WORD + " "
                        + "1 " + CliSyntax.PREFIX_RESIDENTS + "a213", false);
        assertEquals(command, new IncludeCommand(Index.fromOneBased(1), new ResidentList("a213")));
    }

    @Test
    public void parseCommand_export() throws Exception {
        ExportCommand command = (ExportCommand) parser.parseCommand(ExportCommand.COMMAND_WORD
                + " safeforhall", true);
        assertEquals(new ExportCommand("safeforhall"), command);
    }

    @Test
    public void parseCommand_exclude() throws Exception {
        ExcludeCommand command = (ExcludeCommand) parser.parseCommand(
                ExcludeCommand.COMMAND_WORD + " "
                        + "1 " + CliSyntax.PREFIX_RESIDENTS + "a213", false);
        assertEquals(command, new ExcludeCommand(Index.fromOneBased(1), new ResidentList("a213")));
    }

    @Test
    public void parseCommand_sortPerson() throws Exception {
        SortPersonCommand command = (SortPersonCommand) parser.parseCommand(SortPersonCommand.COMMAND_WORD
                + " " + PREFIX_SORT + Name.FIELD + " " + PREFIX_ORDER + ASCENDING, true);
        assertEquals(new SortPersonCommand(Name.FIELD, ASCENDING), command);
    }
    @Test
    public void parseCommand_sortEvent() throws Exception {
        SortEventCommand command = (SortEventCommand) parser.parseCommand(SortEventCommand.COMMAND_WORD
                + " " + PREFIX_SORT + EventName.FIELD + " " + PREFIX_ORDER + ASCENDING, false);
        assertEquals(new SortEventCommand(EventName.FIELD, ASCENDING), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand("", true));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
            -> parser.parseCommand("unknownCommand", true));
    }
}
