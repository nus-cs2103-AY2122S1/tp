package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTutorialClassAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TUTORIALCLASS;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TUTORIALCLASS;
import static seedu.address.testutil.TypicalTutorialClasses.getTypicalClassmate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tutorialclass.TutorialClass;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteClassCommandTest {

    private Model model = new ModelManager(getTypicalClassmate(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        TutorialClass tutorialClassToDelete =
                model.getFilteredTutorialClassList().get(INDEX_FIRST_TUTORIALCLASS.getZeroBased());
        DeleteClassCommand deleteClassCommand = new DeleteClassCommand(INDEX_FIRST_TUTORIALCLASS);

        String expectedMessage = String.format(DeleteClassCommand.MESSAGE_DELETE_CLASS_SUCCESS, tutorialClassToDelete);

        ModelManager expectedModel = new ModelManager(model.getClassmate(), new UserPrefs());
        expectedModel.deleteTutorialClass(tutorialClassToDelete);

        assertCommandSuccess(deleteClassCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTutorialClassList().size() + 1);
        DeleteClassCommand deleteCommand = new DeleteClassCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTutorialClassAtIndex(model, INDEX_FIRST_TUTORIALCLASS);


        TutorialClass tutorialClassToDelete =
                model.getFilteredTutorialClassList().get(INDEX_FIRST_TUTORIALCLASS.getZeroBased());
        DeleteClassCommand deleteClassCommand = new DeleteClassCommand(INDEX_FIRST_TUTORIALCLASS);

        String expectedMessage = String.format(DeleteClassCommand.MESSAGE_DELETE_CLASS_SUCCESS, tutorialClassToDelete);

        Model expectedModel = new ModelManager(model.getClassmate(), new UserPrefs());
        expectedModel.deleteTutorialClass(tutorialClassToDelete);
        showNoTutorialClass(expectedModel);

        assertCommandSuccess(deleteClassCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTutorialClassAtIndex(model, INDEX_FIRST_TUTORIALCLASS);

        Index outOfBoundIndex = INDEX_SECOND_TUTORIALCLASS;
        // ensures that outOfBoundIndex is still in bounds of ClassMATE list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getClassmate().getTutorialClassList().size());

        DeleteClassCommand deleteClassCommand = new DeleteClassCommand(outOfBoundIndex);

        assertCommandFailure(deleteClassCommand, model, Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {

        DeleteStudentCommand deleteFirstCommand = new DeleteStudentCommand(INDEX_FIRST_TUTORIALCLASS);
        DeleteStudentCommand deleteSecondCommand = new DeleteStudentCommand(INDEX_SECOND_TUTORIALCLASS);


        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteStudentCommand deleteFirstCommandCopy = new DeleteStudentCommand(INDEX_FIRST_TUTORIALCLASS);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoStudent(Model model) {
        model.updateFilteredStudentList(p -> false);

        assertTrue(model.getFilteredStudentList().isEmpty());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTutorialClass(Model model) {
        model.updateFilteredTutorialClassList(p -> false);

        assertTrue(model.getFilteredTutorialClassList().isEmpty());
    }
}
