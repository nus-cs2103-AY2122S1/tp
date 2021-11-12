package seedu.teachbook.logic.commands;

import static seedu.teachbook.commons.core.Messages.MESSAGE_DUPLICATE_STUDENT;
import static seedu.teachbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.teachbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.teachbook.testutil.TypicalStudents.getTypicalTeachBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.teachbook.commons.core.index.GeneralIndex;
import seedu.teachbook.model.Model;
import seedu.teachbook.model.ModelManager;
import seedu.teachbook.model.UserPrefs;
import seedu.teachbook.model.student.Student;
import seedu.teachbook.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTeachBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Student validStudent = new StudentBuilder().buildToAdd();
        Model expectedModel = new ModelManager(model.getTeachBook(), new UserPrefs());
        // Set the first class as the currently selected class in the expected model
        // Note that this means the expected model should contain at least one class
        GeneralIndex firstClassIndex = GeneralIndex.fromZeroBased(0);
        expectedModel.updateCurrentlySelectedClass(firstClassIndex);
        expectedModel.setClassForStudent(validStudent);
        expectedModel.addStudent(validStudent);
        expectedModel.commitTeachBook();
        CommandResult expectedCommandResult = new CommandResult(String.format(AddCommand.MESSAGE_ADD_STUDENT_SUCCESS,
                validStudent), false, false, true, false);
        assertCommandSuccess(new AddCommand(validStudent), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Student studentInList = model.getTeachBook().getClassList().get(0).getStudentsOfThisClass().get(0);
        assertCommandFailure(new AddCommand(studentInList), model, MESSAGE_DUPLICATE_STUDENT);
    }

}
