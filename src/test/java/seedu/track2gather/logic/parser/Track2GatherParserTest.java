package seedu.track2gather.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.track2gather.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.track2gather.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_CASE_NUMBER;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.track2gather.testutil.Assert.assertThrows;
import static seedu.track2gather.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.track2gather.testutil.TypicalPersons.ALICE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import seedu.track2gather.commons.core.index.Index;
import seedu.track2gather.logic.commands.AddCommand;
import seedu.track2gather.logic.commands.ClearCommand;
import seedu.track2gather.logic.commands.DeleteCommand;
import seedu.track2gather.logic.commands.EditCommand;
import seedu.track2gather.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.track2gather.logic.commands.ExitCommand;
import seedu.track2gather.logic.commands.FCallCommand;
import seedu.track2gather.logic.commands.FindCommand;
import seedu.track2gather.logic.commands.HelpCommand;
import seedu.track2gather.logic.commands.ListCommand;
import seedu.track2gather.logic.commands.SCallCommand;
import seedu.track2gather.logic.commands.ScheduleCommand;
import seedu.track2gather.logic.commands.SessionCommand;
import seedu.track2gather.logic.commands.SortCommand;
import seedu.track2gather.logic.commands.SortCommand.Direction;
import seedu.track2gather.logic.commands.TShiftCommand;
import seedu.track2gather.logic.parser.exceptions.ParseException;
import seedu.track2gather.model.person.Person;
import seedu.track2gather.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.track2gather.testutil.EditPersonDescriptorBuilder;
import seedu.track2gather.testutil.PersonBuilder;
import seedu.track2gather.testutil.PersonUtil;

public class Track2GatherParserTest {

    private final Track2GatherParser parser = new Track2GatherParser();

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
        ArrayList<Index> indicesFirstPerson = new ArrayList<>();
        indicesFirstPerson.add(INDEX_FIRST_PERSON);
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(indicesFirstPerson), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(ALICE).build();
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
        FindCommand command = (FindCommand) parser.parseCommand(FindCommand.COMMAND_WORD + " "
               + PREFIX_NAME + keywords.stream().collect(Collectors.joining(" ")));
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
    public void parseCommand_sort() throws Exception {
        List<Prefix> prefixes = List.of(PREFIX_NAME, PREFIX_CASE_NUMBER);
        List<Direction> directions = List.of(Direction.ASCENDING, Direction.DESCENDING);
        assertTrue(prefixes.size() > 0);
        assertTrue(prefixes.size() == directions.size());

        String sortsString = IntStream.range(0, prefixes.size())
                .mapToObj(i -> prefixes.get(i).toString() + directions.get(i))
                .collect(Collectors.joining(" "));
        SortCommand command = (SortCommand) parser.parseCommand(SortCommand.COMMAND_WORD + " " + sortsString);
        assertEquals(new SortCommand(prefixes, directions), command);
    }

    @Test
    public void parseCommand_tshift() throws Exception {
        assertTrue(parser.parseCommand(TShiftCommand.COMMAND_WORD + " 3") instanceof TShiftCommand);
        assertTrue(parser.parseCommand(TShiftCommand.COMMAND_WORD + " -3") instanceof TShiftCommand);

        assertEquals(parser.parseCommand(TShiftCommand.COMMAND_WORD + " 3"), new TShiftCommand(3));
        assertEquals(parser.parseCommand(TShiftCommand.COMMAND_WORD + " -3"), new TShiftCommand(-3));
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

    @Test
    public void parseCommand_schedule() throws Exception {
        assertTrue(parser.parseCommand(ScheduleCommand.COMMAND_WORD) instanceof ScheduleCommand);
        assertTrue(parser.parseCommand(ScheduleCommand.COMMAND_WORD + " 3 ") instanceof ScheduleCommand);
    }

    @Test
    public void parseCommand_session() throws Exception {
        assertTrue(parser.parseCommand(SessionCommand.COMMAND_WORD) instanceof SessionCommand);
        assertTrue(parser.parseCommand(SessionCommand.COMMAND_WORD + " 3 ") instanceof SessionCommand);
    }

    @Test
    public void parseCommand_sCall() throws Exception {
        assertTrue(parser.parseCommand(SCallCommand.COMMAND_WORD + " 3 ") instanceof SCallCommand);
    }

    @Test
    public void parseCommand_fCall() throws Exception {
        assertTrue(parser.parseCommand(FCallCommand.COMMAND_WORD + " 3 ") instanceof FCallCommand);
    }
}
