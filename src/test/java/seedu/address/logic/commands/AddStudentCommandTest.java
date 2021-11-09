package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELE_HANDLE_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;
import static seedu.address.testutil.TypicalStudents.AMY;
import static seedu.address.testutil.TypicalStudents.BOB;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TeachingAssistantBuddy;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.student.Student;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TypicalModules;

public class AddStudentCommandTest {
    private TeachingAssistantBuddy testBuddy = TypicalModules.getTypicalBuddy();
    private Model model = new ModelManager(testBuddy, new UserPrefs());

    @Test
    public void constructor_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddStudentCommand(null, null));
    }

    @Test
    public void execute_studentAcceptedByModule_addSuccessful() throws Exception {
        Student validStudent = new StudentBuilder().build();
        Model expectedModel = new ModelManager();
        for (Module module : model.getFilteredModuleList()) {
            ObservableList<Student> uniqueStudentList = module.getFilteredStudentList();
            ModuleName moduleName = new ModuleName(module.getName().toString());
            Module mod = new Module(moduleName);
            mod.setStudents(uniqueStudentList);
            expectedModel.addModule(mod);
        }
        ModuleName moduleName = new ModuleName(MODULE_NAME_0);
        CommandResult commandResult = new AddStudentCommand(validStudent, moduleName).execute(expectedModel);

        assertEquals(String.format(AddStudentCommand.MESSAGE_ADD_STUDENT_SUCCESS, validStudent),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(AMY, BOB, validStudent),
                expectedModel.getFilteredModuleList().get(0).getFilteredStudentList());
    }

    @Test
    public void execute_duplicateStudent_throwsCommandException() {
        ModuleName moduleName = new ModuleName(MODULE_NAME_0);
        AddStudentCommand addStudentCommand = new AddStudentCommand(AMY, moduleName);

        assertThrows(CommandException.class, AddStudentCommand.MESSAGE_DUPLICATE_STUDENT, () ->
                addStudentCommand.execute(model));
    }

    @Test
    public void execute_moduleNotFound_throwsCommandException() {
        ModuleName moduleName = new ModuleName("CS1101S");
        AddStudentCommand addStudentCommand = new AddStudentCommand(AMY, moduleName);
        assertThrows(CommandException.class, String.format(Messages.MESSAGE_MODULE_NAME_NOT_FOUND, "CS1101S"), () ->
                addStudentCommand.execute(model));
    }

    @Test
    public void execute_duplicateTeleHandle_throwsCommandException() {
        ModuleName moduleName = new ModuleName("CS2103");
        Student student = new StudentBuilder().withStudentId("A7654321X").withName("Dema")
                .withTeleHandle(VALID_TELE_HANDLE_AMY).withEmail("ddd@example.com").build();
        AddStudentCommand addStudentCommand = new AddStudentCommand(student, moduleName);
        assertThrows(CommandException.class, AddStudentCommand.MESSAGE_DUPLICATE_TELE_HANDLE, () ->
                addStudentCommand.execute(model));

    }

    @Test
    public void execute_duplicateEmail_throwsCommandException() {
        ModuleName moduleName = new ModuleName("CS2103");
        Student student = new StudentBuilder().withStudentId("A7654321X").withName("Dema")
                .withTeleHandle("@eeema").withEmail(VALID_EMAIL_AMY).build();
        AddStudentCommand addStudentCommand = new AddStudentCommand(student, moduleName);
        assertThrows(CommandException.class, AddStudentCommand.MESSAGE_DUPLICATE_EMAIL, () ->
                addStudentCommand.execute(model));
    }

    @Test
    public void equals() {
        Student amy = new StudentBuilder().withStudentId(VALID_STUDENT_ID_AMY).build();
        Student bob = new StudentBuilder().withStudentId(VALID_STUDENT_ID_BOB).build();
        ModuleName moduleName = new ModuleName(MODULE_NAME_0);
        AddStudentCommand addAliceCommand = new AddStudentCommand(amy, moduleName);
        AddStudentCommand addBobCommand = new AddStudentCommand(bob, moduleName);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddStudentCommand(amy, moduleName);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different students -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }
}
