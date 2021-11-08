package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.getTypicalCsBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Name;

public class ViewStudentCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalCsBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getCsBook(), new UserPrefs());
    }

    @Test
    public void execute_validStudent_success() {
        String successMsg = String.format("Viewing details of %1$s", ALICE.getName());
        assertCommandSuccess(new ViewStudentCommand(ALICE.getName()), model, successMsg, expectedModel);
    }

    @Test
    public void execute_invalidStudent_throwsCommandException() {
        Name nonExistentStudentName = new Name("abcde");
        String expectedMsg = String.format(Messages.MESSAGE_STUDENT_NOT_FOUND, nonExistentStudentName);
        assertCommandSuccess(new ViewStudentCommand(nonExistentStudentName), model, expectedMsg, expectedModel);
    }
}
