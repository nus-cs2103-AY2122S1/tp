package seedu.address.logic.commands.teacher;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTeacherAtIndex;
import static seedu.address.testutil.TypicalAddressBookObjects.ALI;
import static seedu.address.testutil.TypicalAddressBookObjects.BEN;
import static seedu.address.testutil.TypicalAddressBookObjects.CAR;
import static seedu.address.testutil.TypicalAddressBookObjects.DAN;
import static seedu.address.testutil.TypicalAddressBookObjects.EL;
import static seedu.address.testutil.TypicalAddressBookObjects.FIO;
import static seedu.address.testutil.TypicalAddressBookObjects.GEO;
import static seedu.address.testutil.TypicalAddressBookObjects.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEACHER;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.teacher.Teacher;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteStudentCommand}.
 */
public class ClearTeacherCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_massDelete_success() {
        ClearTeacherCommand clearCommand = new ClearTeacherCommand();

        String expectedMessage = String.format(ClearTeacherCommand.MESSAGE_DELETE_TEACHER_SUCCESS, 7);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTeacher(ALI);
        expectedModel.deleteTeacher(BEN);
        expectedModel.deleteTeacher(EL);
        expectedModel.deleteTeacher(FIO);
        expectedModel.deleteTeacher(CAR);
        expectedModel.deleteTeacher(DAN);
        expectedModel.deleteTeacher(GEO);


        assertCommandSuccess(clearCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTeacherAtIndex(model, INDEX_FIRST_TEACHER);

        Teacher teacherToDelete = model.getFilteredTeacherList().get(INDEX_FIRST_TEACHER.getZeroBased());
        ClearTeacherCommand clearCommand = new ClearTeacherCommand();

        String expectedMessage = String.format(clearCommand.MESSAGE_DELETE_TEACHER_SUCCESS, 1);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTeacher(teacherToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(clearCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredTeacherList(p -> false);

        assertTrue(model.getFilteredTeacherList().isEmpty());
    }
}
