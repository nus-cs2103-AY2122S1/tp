package seedu.notor.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.notor.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.notor.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.notor.testutil.Assert.assertThrows;
import static seedu.notor.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.notor.logic.commands.ClearCommand;
import seedu.notor.logic.commands.ExitCommand;
import seedu.notor.logic.commands.HelpCommand;
import seedu.notor.logic.commands.person.PersonCreateCommand;
import seedu.notor.logic.commands.person.PersonDeleteCommand;
import seedu.notor.logic.commands.person.PersonNoteCommand;
import seedu.notor.logic.parser.exceptions.ParseException;
import seedu.notor.model.common.Note;
import seedu.notor.model.person.Person;
import seedu.notor.testutil.PersonBuilder;
import seedu.notor.testutil.PersonUtil;

public class NotorParserTest {
    private final NotorParser parser = new NotorParser();

    @Test
    public void parseCommand_create() throws Exception {
        Person person = new PersonBuilder().withNote(Note.EMPTY_NOTE).build();
        PersonCreateCommand command = (PersonCreateCommand) parser.parseCommand(PersonUtil.getCreateCommand(person));
        assertEquals(new PersonCreateCommand(null, person), command);
    }

    public void parseCommand_createWithTags() throws Exception {
        Person person = new PersonBuilder().withTags("friends").build();
        PersonCreateCommand command =
                (PersonCreateCommand) parser.parseCommand(PersonUtil.getCreateCommandWithTags(person));
        assertEquals(new PersonCreateCommand(null, person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        PersonDeleteCommand command = (PersonDeleteCommand) parser.parseCommand("person 1 /delete");
        assertEquals(new PersonDeleteCommand(INDEX_FIRST_PERSON), command);
    }

    // TODO: Elton cannot fix this. This is caused by tags being different in both commands.
    // One has empty optional, the other has optional with empty set.
    //    @Test
    //    public void parseCommand_edit() throws Exception {
    //        Person person = new PersonBuilder().build();
    //        PersonEditDescriptor descriptor = new PersonEditDescriptorBuilder(person).build();
    //
    //        PersonEditCommand command =
    //                (PersonEditCommand) parser.parseCommand(PersonUtil.getEditPersonDescriptorCommand(1, descriptor));
    //        assertEquals(new PersonEditCommand(INDEX_FIRST_PERSON, descriptor), command);
    //    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
    }

    // TODO: When find command is added

    //    @Test
    //    public void parseCommand_find() throws Exception {
    //        List<String> keywords = Arrays.asList("foo", "bar", "baz");
    //        FindCommand command = (FindCommand) parser.parseCommand(
    //                FindCommand.COMMAND_WORD + " " + String.join(" ", keywords));
    //        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    //    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
    }

    // TODO: When list command is added

    //    @Test
    //    public void parseCommand_list() throws Exception {
    //        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
    //        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    //    }

    @Test
    public void parseCommand_note() throws Exception {
        final Note note = Note.of("Test note", "");
        PersonNoteCommand command = (PersonNoteCommand) parser.parseCommand("person 1 /note");
        assertEquals(new PersonNoteCommand(INDEX_FIRST_PERSON), command);
    }

    // @formatter:off
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
