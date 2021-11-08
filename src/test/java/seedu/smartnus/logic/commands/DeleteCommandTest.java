package seedu.smartnus.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.smartnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartnus.logic.commands.CommandTestUtil.showNoteAtIndex;
import static seedu.smartnus.logic.commands.CommandTestUtil.showQuestionAtIndex;
import static seedu.smartnus.testutil.Assert.assertThrows;
import static seedu.smartnus.testutil.TypicalIndexes.INDEX_FIRST_QUESTION;
import static seedu.smartnus.testutil.TypicalIndexes.INDEX_SECOND_QUESTION;
import static seedu.smartnus.testutil.TypicalIndexes.INDEX_THIRD_QUESTION;
import static seedu.smartnus.testutil.TypicalSmartNus.getTypicalSmartNus;

import org.junit.jupiter.api.Test;

import seedu.smartnus.commons.core.Messages;
import seedu.smartnus.commons.core.index.Index;
import seedu.smartnus.logic.commands.exceptions.CommandException;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.ModelManager;
import seedu.smartnus.model.ModelStubTagPanel;
import seedu.smartnus.model.UserPrefs;
import seedu.smartnus.model.note.Note;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.ui.panel.NoteListPanel;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalSmartNus(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredQuestionList_success() {
        Question questionToDelete = model.getFilteredQuestionList().get(INDEX_FIRST_QUESTION.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand("question", INDEX_FIRST_QUESTION);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_QUESTION_SUCCESS, questionToDelete);

        ModelManager expectedModel = new ModelManager(model.getSmartNus(), new UserPrefs());
        expectedModel.deleteQuestion(questionToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexUnfilteredNoteList_success() {
        Note noteToDelete = model.getFilteredNoteList().get(INDEX_FIRST_QUESTION.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand("note", INDEX_FIRST_QUESTION);
        model.setPanel(NoteListPanel.NOTE_PANEL);
        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_NOTE_SUCCESS, noteToDelete);

        ModelManager expectedModel = new ModelManager(model.getSmartNus(), new UserPrefs());
        expectedModel.deleteNote(noteToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredQuestionList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand("question", outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_QUESTION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showQuestionAtIndex(model, INDEX_FIRST_QUESTION);

        Question questionToDelete = model.getFilteredQuestionList().get(INDEX_FIRST_QUESTION.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand("question", INDEX_FIRST_QUESTION);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_QUESTION_SUCCESS, questionToDelete);

        Model expectedModel = new ModelManager(model.getSmartNus(), new UserPrefs());
        expectedModel.deleteQuestion(questionToDelete);
        showNoQuestion(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showQuestionAtIndex(model, INDEX_FIRST_QUESTION);

        Index outOfBoundIndex = INDEX_SECOND_QUESTION;
        // ensures that outOfBoundIndex is still in bounds of the SmartNus question list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getSmartNus().getQuestionList().size());

        DeleteCommand deleteCommand = new DeleteCommand("question", outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_QUESTION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexUnfilteredNoteList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredNoteList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand("note", outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_NOT_IN_NOTE_PANEL);
    }

    @Test
    public void execute_validIndexFilteredNoteList_success() {
        showNoteAtIndex(model, INDEX_FIRST_QUESTION);

        model.setPanel(NoteListPanel.NOTE_PANEL);
        Note noteToDelete = model.getFilteredNoteList().get(INDEX_FIRST_QUESTION.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand("note", INDEX_FIRST_QUESTION);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_NOTE_SUCCESS, noteToDelete.toString());

        Model expectedModel = new ModelManager(model.getSmartNus(), new UserPrefs());
        expectedModel.deleteNote(noteToDelete);
        showNoNote(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredNoteList_throwsCommandException() {
        showNoteAtIndex(model, INDEX_FIRST_QUESTION);

        Index outOfBoundIndex = INDEX_SECOND_QUESTION;
        model.setPanel(NoteListPanel.NOTE_PANEL);
        // ensures that outOfBoundIndex is still in bounds of the SmartNus note list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getSmartNus().getNoteList().size());

        DeleteCommand deleteCommand = new DeleteCommand("note", outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidArgument_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand("wrong", INDEX_FIRST_QUESTION);
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE);

        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand("question", INDEX_FIRST_QUESTION);
        DeleteCommand deleteSecondCommand = new DeleteCommand("question", INDEX_SECOND_QUESTION);
        DeleteCommand deleteThirdCommand = new DeleteCommand("note", INDEX_FIRST_QUESTION);
        DeleteCommand deleteFourthCommand = new DeleteCommand("note", INDEX_THIRD_QUESTION);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand("question", INDEX_FIRST_QUESTION);
        DeleteCommand deleteThirdCommandCopy = new DeleteCommand("note", INDEX_FIRST_QUESTION);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));
        assertTrue(deleteThirdCommand.equals(deleteThirdCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different question -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));

        // different deleteItems, same index -> returns false
        assertFalse(deleteFirstCommand.equals(deleteThirdCommand));

        // different index, deleteItems -> returns false
        assertFalse(deleteFirstCommand.equals(deleteFourthCommand));
    }

    @Test
    public void delete_wrongPanel() {
        // Delete notes
        ModelStubTagPanel model = new ModelStubTagPanel();
        DeleteCommand noteCmd = new DeleteCommand("note", INDEX_FIRST_QUESTION);
        assertThrows(CommandException.class,
                Messages.MESSAGE_NOT_IN_NOTE_PANEL, () -> noteCmd.execute(model));

        // Delete question
        DeleteCommand qnCmd = new DeleteCommand("question", INDEX_FIRST_QUESTION);
        assertThrows(CommandException.class,
                Messages.MESSAGE_NOT_IN_QUESTION_PANEL, () -> qnCmd.execute(model));
    }

    /**
     * Updates {@code model}'s filtered list to show no question.
     */
    private void showNoQuestion(Model model) {
        model.updateFilteredQuestionList(p -> false);

        assertTrue(model.getFilteredQuestionList().isEmpty());
    }

    /**
     * Updates {@code model}'s filtered list to show no note.
     */
    private void showNoNote(Model model) {
        model.updateFilteredNoteList(p -> false);

        assertTrue(model.getFilteredNoteList().isEmpty());
    }
}
