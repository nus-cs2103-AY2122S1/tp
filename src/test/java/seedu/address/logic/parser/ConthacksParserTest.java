package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.modulelesson.AddModuleLessonCommand;
import seedu.address.logic.commands.modulelesson.ClearModuleLessonCommand;
import seedu.address.logic.commands.modulelesson.DeleteModuleLessonCommand;
import seedu.address.logic.commands.modulelesson.EditModuleLessonCommand;
import seedu.address.logic.commands.modulelesson.FindModuleLessonCommand;
import seedu.address.logic.commands.modulelesson.ListModuleLessonCommand;
import seedu.address.logic.commands.person.AddPersonCommand;
import seedu.address.logic.commands.person.ClearPersonCommand;
import seedu.address.logic.commands.person.DeletePersonCommand;
import seedu.address.logic.commands.person.EditPersonCommand;
import seedu.address.logic.commands.person.EditPersonCommand.EditPersonDescriptor;
import seedu.address.logic.commands.person.FindPersonCommand;
import seedu.address.logic.commands.person.ListPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.modulelesson.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.modulelesson.ModuleLesson;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditLessonDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.ModuleLessonBuilder;
import seedu.address.testutil.ModuleLessonUtil;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class ConthacksParserTest {

    private final ConthacksParser parser = new ConthacksParser();

    @Test
    public void parseCommand_add() throws Exception {
        List<String> aliasForAdd = CommandWord.getAliasList(CommandWord.ADD_PERSON);
        Person person = new PersonBuilder().build();
        for (String alias : aliasForAdd) {
            AddPersonCommand command = (AddPersonCommand) parser.parseCommand(alias + " "
                    + PersonUtil.getPersonDetails(person));
            assertEquals(new AddPersonCommand(person), command);
        }
    }

    @Test
    public void parseCommand_clear() throws Exception {
        List<String> aliasForClear = CommandWord.getAliasList(CommandWord.CLEAR_PERSON);
        for (String alias : aliasForClear) {
            assertTrue(parser.parseCommand(alias) instanceof ClearPersonCommand);
            assertTrue(parser.parseCommand(alias + " 3") instanceof ClearPersonCommand);
        }
    }

    @Test
    public void parseCommand_delete() throws Exception {
        List<String> aliasForDelete = CommandWord.getAliasList(CommandWord.DELETE_PERSON);
        for (String alias : aliasForDelete) {
            DeletePersonCommand command = (DeletePersonCommand) parser.parseCommand(
                    alias + " " + INDEX_FIRST.getOneBased());
            assertEquals(new DeletePersonCommand(INDEX_FIRST), command);
        }
    }

    @Test
    public void parseCommand_edit() throws Exception {
        List<String> aliasForEdit = CommandWord.getAliasList(CommandWord.EDIT_PERSON);
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        for (String alias : aliasForEdit) {
            EditPersonCommand command = (EditPersonCommand) parser.parseCommand(alias + " "
                    + INDEX_FIRST.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
            assertEquals(new EditPersonCommand(INDEX_FIRST, descriptor), command);
        }
    }

    @Test
    public void parseCommand_exit() throws Exception {
        List<String> aliasForExit = CommandWord.getAliasList(CommandWord.EXIT);
        for (String alias : aliasForExit) {
            assertTrue(parser.parseCommand(alias) instanceof ExitCommand);
            assertTrue(parser.parseCommand(alias + " 3") instanceof ExitCommand);
        }

    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> aliasForFind = CommandWord.getAliasList(CommandWord.FIND_PERSON);
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        for (String alias : aliasForFind) {
            FindPersonCommand command = (FindPersonCommand) parser.parseCommand(
                    alias + " n/" + keywords.stream().collect(Collectors.joining(" ")));
            assertEquals(new FindPersonCommand(new NameContainsKeywordsPredicate(keywords)), command);
        }
    }

    @Test
    public void parseCommand_help() throws Exception {
        List<String> aliasForHelp = CommandWord.getAliasList(CommandWord.HELP);
        for (String alias : aliasForHelp) {
            assertTrue(parser.parseCommand(alias) instanceof HelpCommand);
            assertTrue(parser.parseCommand(alias + " 3") instanceof HelpCommand);
        }

    }

    @Test
    public void parseCommand_list() throws Exception {
        List<String> aliasForList = CommandWord.getAliasList(CommandWord.LIST_PERSON);
        for (String alias: aliasForList) {
            assertTrue(parser.parseCommand(alias) instanceof ListPersonCommand);
            assertTrue(parser.parseCommand(alias + " 3") instanceof ListPersonCommand);
        }
    }

    @Test
    public void parseCommand_listModuleLesson() throws Exception {
        List<String> aliasForList = CommandWord.getAliasList(CommandWord.LIST_MODULE_LESSON);
        for (String alias : aliasForList) {
            assertTrue(parser.parseCommand(alias) instanceof ListModuleLessonCommand);
            assertTrue(parser.parseCommand(alias + " 3") instanceof ListModuleLessonCommand);
        }
    }

    @Test
    public void parseCommand_addModuleLesson() throws Exception {
        List<String> aliasForAdd = CommandWord.getAliasList(CommandWord.ADD_MODULE_LESSON);
        ModuleLesson moduleLesson = new ModuleLessonBuilder().build();
        for (String alias : aliasForAdd) {
            AddModuleLessonCommand command = (AddModuleLessonCommand) parser.parseCommand(alias + " "
                    + ModuleLessonUtil.getModuleLessonDetails(moduleLesson));
            assertEquals(new AddModuleLessonCommand(moduleLesson), command);
        }
    }

    @Test
    public void parseCommand_deleteModuleLesson() throws Exception {
        List<String> aliasForDelete = CommandWord.getAliasList(CommandWord.DELETE_MODULE_LESSON);
        for (String alias : aliasForDelete) {
            DeleteModuleLessonCommand command = (DeleteModuleLessonCommand) parser.parseCommand(
                    alias + " " + INDEX_FIRST.getOneBased());
            assertEquals(new DeleteModuleLessonCommand(INDEX_FIRST), command);
        }
    }

    @Test
    public void parseCommand_editModuleLesson() throws Exception {
        List<String> aliasForEdit = CommandWord.getAliasList(CommandWord.EDIT_MODULE_LESSON);
        ModuleLesson lesson = new ModuleLessonBuilder().build();
        EditModuleLessonCommand.EditLessonDescriptor editLessonDescriptor =
                new EditLessonDescriptorBuilder(lesson).build();
        for (String alias : aliasForEdit) {
            EditModuleLessonCommand command = (EditModuleLessonCommand) parser.parseCommand(alias + " "
                    + INDEX_FIRST.getOneBased() + " "
                    + ModuleLessonUtil.getEditLessonDescriptorDetails(editLessonDescriptor));
            assertEquals(new EditModuleLessonCommand(INDEX_FIRST, editLessonDescriptor), command);
        }
    }

    @Test
    public void parseCommand_findModuleLesson() throws Exception {
        List<String> aliasForFind = CommandWord.getAliasList(CommandWord.FIND_MODULE_LESSON);
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        for (String alias : aliasForFind) {
            FindModuleLessonCommand command = (FindModuleLessonCommand) parser.parseCommand(
                    alias + " m/" + keywords.stream().collect(Collectors.joining(" ")));
            assertEquals(new FindModuleLessonCommand(new ModuleCodeContainsKeywordsPredicate(keywords)), command);
        }
    }

    @Test
    public void parseCommand_clearModuleLesson() throws Exception {
        List<String> aliasForClear = CommandWord.getAliasList(CommandWord.CLEAR_MODULE_LESSON);
        for (String alias : aliasForClear) {
            assertTrue(parser.parseCommand(alias) instanceof ClearModuleLessonCommand);
            assertTrue(parser.parseCommand(alias + " 3") instanceof ClearModuleLessonCommand);
        }
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
