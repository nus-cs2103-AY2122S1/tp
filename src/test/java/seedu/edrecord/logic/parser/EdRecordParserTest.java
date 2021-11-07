package seedu.edrecord.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.edrecord.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edrecord.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.edrecord.testutil.Assert.assertThrows;
import static seedu.edrecord.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.edrecord.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.edrecord.testutil.TypicalModules.setTypicalModuleSystem;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.edrecord.logic.commands.AddCommand;
import seedu.edrecord.logic.commands.ClearCommand;
import seedu.edrecord.logic.commands.DeleteCommand;
import seedu.edrecord.logic.commands.DeleteGradeCommand;
import seedu.edrecord.logic.commands.EditCommand;
import seedu.edrecord.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.edrecord.logic.commands.ExitCommand;
import seedu.edrecord.logic.commands.FindCommand;
import seedu.edrecord.logic.commands.GradeCommand;
import seedu.edrecord.logic.commands.HelpCommand;
import seedu.edrecord.logic.commands.ListCommand;
import seedu.edrecord.logic.parser.exceptions.ParseException;
import seedu.edrecord.model.person.NameContainsKeywordsPredicate;
import seedu.edrecord.model.person.Person;
import seedu.edrecord.testutil.EditPersonDescriptorBuilder;
import seedu.edrecord.testutil.PersonBuilder;
import seedu.edrecord.testutil.PersonUtil;

public class EdRecordParserTest {

    private final EdRecordParser parser = new EdRecordParser();

    @Test
    public void parseCommand_add() throws Exception {
        setTypicalModuleSystem();
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
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        setTypicalModuleSystem();
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
    public void parseCommand_grade() throws Exception {
        assertTrue(parser.parseCommand(GradeCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " "
                + PREFIX_ID + INDEX_SECOND_PERSON.getOneBased() + " " + PREFIX_STATUS + "submitted")
                instanceof GradeCommand);
        assertTrue(parser.parseCommand(GradeCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " "
                + PREFIX_ID + INDEX_SECOND_PERSON.getOneBased() + " " + PREFIX_SCORE + "50" + " " + PREFIX_STATUS +
                "graded") instanceof GradeCommand);
    }

    @Test
    public void parseCommand_deleteGrade() throws Exception {
        assertTrue(parser.parseCommand(DeleteGradeCommand.COMMAND_WORD + " " +
                INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_ID + INDEX_SECOND_PERSON.getOneBased())
                instanceof DeleteGradeCommand);
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
