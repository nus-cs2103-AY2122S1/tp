package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPLETION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_OF_APPLICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REQUIREMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AcceptCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CompleteCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditApplicationDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.RejectCommand;
import seedu.address.logic.commands.SoonCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.application.Application;
import seedu.address.model.application.CompletionContainsKeywordsPredicate;
import seedu.address.model.application.NameContainsKeywordsPredicate;
import seedu.address.model.application.PositionContainsKeywordsPredicate;
import seedu.address.model.application.PriorityContainsKeywordsPredicate;
import seedu.address.model.application.RequirementsContainsKeywordsPredicate;
import seedu.address.model.application.StatusContainsKeywordsPredicate;
import seedu.address.testutil.ApplicationBuilder;
import seedu.address.testutil.ApplicationUtil;
import seedu.address.testutil.EditApplicationDescriptorBuilder;

public class InternshipParserTest {

    private final InternshipParser parser = new InternshipParser();


    @Test
    public void parseCommand_add() throws Exception {
        Application application = new ApplicationBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(ApplicationUtil.getAddCommand(application));
        assertEquals(new AddCommand(application), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_APPLICATION.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_APPLICATION), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Application application = new ApplicationBuilder().build();
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder(application).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_APPLICATION.getOneBased() + " "
                + ApplicationUtil.getEditApplicationDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_APPLICATION, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findByCompany() throws Exception {
        List<String> keywords = Arrays.asList("google", "grab", "shopee");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_COMPANY_NAME + String.join(" ", keywords));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findByPosition() throws Exception {
        List<String> keywords = Arrays.asList("engineer", "designer");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_INTERNSHIP_POSITION + String.join(" ", keywords));
        assertEquals(new FindCommand(new PositionContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findByCompletion() throws Exception {
        List<String> keywords = Arrays.asList("completed");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_COMPLETION + String.join(" ", keywords));
        assertEquals(new FindCommand(new CompletionContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findByStatus() throws Exception {
        List<String> keywords = Arrays.asList("accepted");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_STATUS + String.join(" ", keywords));
        assertEquals(new FindCommand(new StatusContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findByRequirement() throws Exception {
        List<String> keywords = Arrays.asList("resume", "cv", "interview");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_REQUIREMENT + String.join(" ", keywords));
        assertEquals(new FindCommand(new RequirementsContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findByPriority() throws Exception {
        List<String> keywords = Arrays.asList("high");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_PRIORITY + String.join(" ", keywords));
        assertEquals(new FindCommand(new PriorityContainsKeywordsPredicate(keywords)), command);
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
    public void parseCommand_complete() throws Exception {
        CompleteCommand command = (CompleteCommand) parser.parseCommand(
                CompleteCommand.COMMAND_WORD + " " + INDEX_FIRST_APPLICATION.getOneBased());
        assertEquals(new CompleteCommand(INDEX_FIRST_APPLICATION), command);
    }

    @Test
    public void parseCommand_soon() throws Exception {
        assertTrue(parser.parseCommand(SoonCommand.COMMAND_WORD + " d/3") instanceof SoonCommand);
    }

    @Test
    public void parseCommand_sortByCompany() throws Exception {
        SortCommand command = (SortCommand) parser.parseCommand(
                SortCommand.COMMAND_WORD + " " + PREFIX_COMPANY_NAME);
        assertEquals(new SortCommand("company"), command);
    }

    @Test
    public void parseCommand_sortByPosition() throws Exception {
        SortCommand command = (SortCommand) parser.parseCommand(
                SortCommand.COMMAND_WORD + " " + PREFIX_INTERNSHIP_POSITION);
        assertEquals(new SortCommand("position"), command);
    }

    @Test
    public void parseCommand_sortByDeadline() throws Exception {
        SortCommand command = (SortCommand) parser.parseCommand(
                SortCommand.COMMAND_WORD + " " + PREFIX_DEADLINE_OF_APPLICATION);
        assertEquals(new SortCommand("deadline"), command);
    }

    @Test
    public void parseCommand_sortByPriority() throws Exception {
        SortCommand command = (SortCommand) parser.parseCommand(
                SortCommand.COMMAND_WORD + " " + PREFIX_PRIORITY);
        assertEquals(new SortCommand("priority"), command);
    }

    @Test
    public void parseCommand_accept() throws Exception {
        AcceptCommand command = (AcceptCommand) parser.parseCommand(
                AcceptCommand.COMMAND_WORD + " " + INDEX_FIRST_APPLICATION.getOneBased());
        assertEquals(new AcceptCommand(INDEX_FIRST_APPLICATION), command);
    }

    @Test
    public void parseCommand_reject() throws Exception {
        RejectCommand command = (RejectCommand) parser.parseCommand(
                RejectCommand.COMMAND_WORD + " " + INDEX_FIRST_APPLICATION.getOneBased());
        assertEquals(new RejectCommand(INDEX_FIRST_APPLICATION), command);
    }

    @Test
    public void parseCommand_undo() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD + " 3") instanceof UndoCommand);
    }

    @Test
    public void parseCommand_redo() throws Exception {
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD) instanceof RedoCommand);
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD + " 3") instanceof RedoCommand);
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
