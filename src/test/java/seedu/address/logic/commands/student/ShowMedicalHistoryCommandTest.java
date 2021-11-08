package seedu.address.logic.commands.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.student.ShowMedicalHistoryCommand.SHOWING_MEDICAL_HISTORY_MESSAGE;
import static seedu.address.testutil.TypicalAddressBookObjects.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.student.Student;

class ShowMedicalHistoryCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        ShowMedicalHistoryCommand showMedicalHistoryCommand = new ShowMedicalHistoryCommand(outOfBoundIndex);

        assertCommandFailure(showMedicalHistoryCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student studentToShow = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        ShowMedicalHistoryCommand showCommand = new ShowMedicalHistoryCommand(INDEX_FIRST_STUDENT);
        CommandResult expectedCommandResult = new CommandResult(SHOWING_MEDICAL_HISTORY_MESSAGE,
                false, false, true, studentToShow, false, false);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(showCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        ShowMedicalHistoryCommand showFirstCommand = new ShowMedicalHistoryCommand(INDEX_FIRST_STUDENT);
        ShowMedicalHistoryCommand showSecondCommand = new ShowMedicalHistoryCommand(INDEX_SECOND_STUDENT);

        // same object -> returns true
        assertTrue(showFirstCommand.equals(showFirstCommand));

        // same values -> returns true
        ShowMedicalHistoryCommand showFirstCommandCopy = new ShowMedicalHistoryCommand(INDEX_FIRST_STUDENT);
        assertTrue(showFirstCommand.equals(showFirstCommandCopy));

        // different types -> returns false
        assertFalse(showFirstCommand.equals(1));

        // null -> returns false
        assertFalse(showFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(showFirstCommand.equals(showSecondCommand));
    }
}
