package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_1;
import static seedu.address.testutil.TypicalStudents.AMY;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TeachingAssistantBuddy;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.student.Student;
import seedu.address.model.module.student.StudentId;
import seedu.address.testutil.TypicalModules;

class DeleteStudentCommandTest {
    private TeachingAssistantBuddy testBuddy = TypicalModules.getTypicalBuddy();
    private Model model = new ModelManager(testBuddy, new UserPrefs());

    @Test
    public void execute_validModuleNameAndStudentId_success() {
        String studentToDelete = VALID_STUDENT_ID_AMY;

        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(new StudentId(studentToDelete),
                new ModuleName(MODULE_NAME_0));

        String expectedMessage = String.format(DeleteStudentCommand.MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete);
        Model expectedModel = new ModelManager();
        for (Module module : model.getFilteredModuleList()) {
            ObservableList<Student> uniqueStudentList = module.getFilteredStudentList();
            ModuleName moduleName = new ModuleName(module.getName().toString());
            Module mod = new Module(moduleName);
            mod.setStudents(uniqueStudentList);
            expectedModel.addModule(mod);
        }
        expectedModel.getFilteredModuleList().get(0).removeStudent(AMY);
        assertCommandSuccess(deleteStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentId_throwsCommandException() {
        StudentId invalidStudentId = new StudentId("A0000000A");
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(invalidStudentId,
                new ModuleName(MODULE_NAME_0));
        String expectedMessage = String.format(Messages.MESSAGE_STUDENT_NOT_FOUND, invalidStudentId);
        assertCommandFailure(deleteStudentCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        DeleteStudentCommand deleteFirstCommand = new DeleteStudentCommand(new StudentId(VALID_STUDENT_ID_AMY),
                new ModuleName(MODULE_NAME_0));
        DeleteStudentCommand deleteSecondCommand = new DeleteStudentCommand(new StudentId(VALID_STUDENT_ID_BOB),
                new ModuleName(MODULE_NAME_1));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteStudentCommand deleteFirstCommandCopy = new DeleteStudentCommand(new StudentId(VALID_STUDENT_ID_AMY),
                new ModuleName(MODULE_NAME_0));
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

}
