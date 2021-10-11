package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PARTICIPANT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditParticipantDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ShowEventDetailsCommand;
import seedu.address.logic.commands.ShowEventParticipantsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventNamePredicate;
import seedu.address.model.participant.NameContainsKeywordsPredicate;
import seedu.address.model.participant.Participant;
import seedu.address.testutil.EditParticipantDescriptorBuilder;
import seedu.address.testutil.ParticipantBuilder;
import seedu.address.testutil.ParticipantUtil;
import seedu.address.testutil.TypicalEvents;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Participant participant = new ParticipantBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(ParticipantUtil.getAddCommand(participant));
        assertEquals(new AddCommand(participant).toString(), command.toString());
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
            DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PARTICIPANT.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PARTICIPANT), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Participant participant = new ParticipantBuilder().build();
        EditParticipantDescriptor descriptor = new EditParticipantDescriptorBuilder(participant).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PARTICIPANT.getOneBased() + " "
                + ParticipantUtil.getEditParticipantDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PARTICIPANT, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
            FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_showDetails() throws Exception {
        Event sampleEvent = TypicalEvents.SAMPLE_EVENT;
        ShowEventDetailsCommand command = (ShowEventDetailsCommand) parser.parseCommand(
                String.format("%s %s", ShowEventDetailsCommand.COMMAND_WORD, sampleEvent.getNameString()));
        assertEquals(new ShowEventDetailsCommand(new EventNamePredicate(sampleEvent.getNameString())), command);
    }

    @Test
    public void parseCommand_showParticipants() throws Exception {
        Event sampleEvent = TypicalEvents.SAMPLE_EVENT;
        ShowEventParticipantsCommand command = (ShowEventParticipantsCommand) parser.parseCommand(
                String.format("%s %s", ShowEventParticipantsCommand.COMMAND_WORD, sampleEvent.getNameString()));
        assertEquals(new ShowEventParticipantsCommand(new EventNamePredicate(sampleEvent.getNameString())), command);
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
