package seedu.times.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.times.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.times.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.times.testutil.Assert.assertThrows;
import static seedu.times.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.times.logic.commands.AddCommand;
import seedu.times.logic.commands.ClearCommand;
import seedu.times.logic.commands.DeleteCommand;
import seedu.times.logic.commands.EditCommand;
import seedu.times.logic.commands.ExitCommand;
import seedu.times.logic.commands.FindNameCommand;
import seedu.times.logic.commands.HelpCommand;
import seedu.times.logic.commands.ListCommand;
import seedu.times.logic.parser.exceptions.ParseException;
import seedu.times.model.person.Student;
import seedu.times.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.times.testutil.EditPersonDescriptorBuilder;
import seedu.times.testutil.PersonBuilder;
import seedu.times.testutil.PersonUtil;

public class TimesTableParserTest {

    private final TimesTableParser parser = new TimesTableParser();

    @Test
    public void parseCommand_add() throws Exception {
        Student student = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(student));
        assertEquals(new AddCommand(student), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Student student = new PersonBuilder().build();
        EditCommand.EditStudentDescriptor descriptor = new EditPersonDescriptorBuilder(student).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findname() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindNameCommand command = (FindNameCommand) parser.parseCommand(
                FindNameCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(", ")));
        assertEquals(new FindNameCommand(new NameContainsKeywordsPredicate(keywords)), command);
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
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
