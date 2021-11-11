package seedu.intern.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intern.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intern.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.intern.testutil.Assert.assertThrows;
import static seedu.intern.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;
import static seedu.intern.testutil.TypicalSelections.SELECTION_FIRST_APPLICANT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.intern.commons.core.selection.Selection;
import seedu.intern.logic.commands.AddCommand;
import seedu.intern.logic.commands.ClearCommand;
import seedu.intern.logic.commands.DeleteCommand;
import seedu.intern.logic.commands.EditCommand;
import seedu.intern.logic.commands.EditCommand.EditApplicantDescriptor;
import seedu.intern.logic.commands.ExitCommand;
import seedu.intern.logic.commands.FindCommand;
import seedu.intern.logic.commands.HelpCommand;
import seedu.intern.logic.commands.ListCommand;
import seedu.intern.logic.parser.exceptions.ParseException;
import seedu.intern.model.applicant.Applicant;
import seedu.intern.model.applicant.NameContainsKeywordsPredicate;
import seedu.intern.testutil.ApplicantBuilder;
import seedu.intern.testutil.ApplicantUtil;
import seedu.intern.testutil.EditApplicantDescriptorBuilder;

public class InternWatcherParserTest {

    private final InternWatcherParser parser = new InternWatcherParser();

    @Test
    public void parseCommand_add() throws Exception {
        Applicant applicant = new ApplicantBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(ApplicantUtil.getAddCommand(applicant));
        assertEquals(new AddCommand(applicant), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + SELECTION_FIRST_APPLICANT.getIndexOneBased());
        assertEquals(new DeleteCommand(SELECTION_FIRST_APPLICANT), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Applicant applicant = new ApplicantBuilder().build();
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder(applicant).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_APPLICANT.getOneBased() + " "
                + ApplicantUtil.getEditApplicantDescriptorDetails(descriptor));
        assertEquals(new EditCommand(Selection.fromIndex(INDEX_FIRST_APPLICANT), descriptor), command);
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
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
