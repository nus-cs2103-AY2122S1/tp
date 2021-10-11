package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddFacilityCommand;
import seedu.address.logic.commands.AddMemberCommand;
import seedu.address.logic.commands.ClearFacilitiesCommand;
import seedu.address.logic.commands.ClearMembersCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindFacilityCommand;
import seedu.address.logic.commands.FindMemberCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListFacilityCommand;
import seedu.address.logic.commands.ListMemberCommand;
import seedu.address.logic.commands.SplitCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.facility.Facility;
import seedu.address.model.facility.LocationContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.FacilityBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_addMember() throws Exception {
        Person person = new PersonBuilder().build();
        AddMemberCommand command = (AddMemberCommand) parser.parseCommand(PersonUtil.getAddMemberCommand(person));
        assertEquals(new AddMemberCommand(person), command);
    }

    @Test
    public void parseCommand_addFacility() throws ParseException {
        Facility facility = new FacilityBuilder()
                .withFacilityName("Court 1")
                .withLocation("University Sports Hall")
                .withCapacity("5")
                .withTime("11:30").build();
        AddFacilityCommand command = (AddFacilityCommand) parser.parseCommand("addf "
                + "n/Court 1 l/University Sports Hall t/11:30 c/5");
        assertEquals(new AddFacilityCommand(facility), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearMembersCommand.COMMAND_WORD) instanceof ClearMembersCommand);
        assertTrue(parser.parseCommand(ClearMembersCommand.COMMAND_WORD + " 3") instanceof ClearMembersCommand);
    }

    @Test
    public void parseCommand_clearf() throws Exception {
        assertTrue(parser.parseCommand(ClearFacilitiesCommand.COMMAND_WORD) instanceof ClearFacilitiesCommand);
        assertTrue(parser.parseCommand(ClearFacilitiesCommand.COMMAND_WORD + " 3")
                instanceof ClearFacilitiesCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindMemberCommand command = (FindMemberCommand) parser.parseCommand(
                FindMemberCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindMemberCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findFacility() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindFacilityCommand command = (FindFacilityCommand) parser.parseCommand(
                FindFacilityCommand.COMMAND_WORD
                        + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindFacilityCommand(new LocationContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listm() throws Exception {
        assertTrue(parser.parseCommand(ListMemberCommand.COMMAND_WORD) instanceof ListMemberCommand);
        assertTrue(parser.parseCommand(ListMemberCommand.COMMAND_WORD + " 3") instanceof ListMemberCommand);
    }

    @Test
    public void parseCommand_listf() throws Exception {
        assertTrue(parser.parseCommand(ListFacilityCommand.COMMAND_WORD) instanceof ListFacilityCommand);
        assertTrue(parser.parseCommand(
                ListFacilityCommand.COMMAND_WORD + " 3") instanceof ListFacilityCommand);
    }

    @Test
    public void parseCommand_split() throws Exception {
        assertEquals(new SplitCommand("Mon"), parser.parseCommand(SplitCommand.COMMAND_WORD + " Mon"));
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
