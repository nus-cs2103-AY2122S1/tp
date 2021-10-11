package seedu.programmer.logic.commands;

import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.programmer.testutil.TypicalStudents.getTypicalProgrammerError;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.programmer.model.Model;
import seedu.programmer.model.ModelManager;
import seedu.programmer.model.UserPrefs;
import seedu.programmer.model.student.ClassId;
import seedu.programmer.model.student.Grade;
import seedu.programmer.model.student.Name;
import seedu.programmer.model.student.Student;
import seedu.programmer.model.student.StudentId;


/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {
    private Student validStudent;
    private Model model;

    @BeforeEach
    public void setUp() {
        // Re-initialize these variables before each test
        validStudent = new Student(new Name("Tester"), new StudentId("A0123456B"), new ClassId("B01"), new Grade("A"));
        model = new ModelManager(getTypicalProgrammerError(), new UserPrefs());
    }

    @Test

    public void execute_newStudent_success() {
        Model expectedModel = new ModelManager(model.getProgrammerError(), new UserPrefs());
        expectedModel.addStudent(validStudent);
        assertCommandSuccess(new AddCommand(validStudent), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validStudent), expectedModel);
    }

    @Test
    public void execute_duplicateStudent_throwsCommandException() {
        Student studentInList = model.getProgrammerError().getStudentList().get(0);
        assertCommandFailure(new AddCommand(studentInList), model, AddCommand.MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_sameNameDifferentStudentId_success() {
        Model expectedModel = new ModelManager(model.getProgrammerError(), new UserPrefs());
        expectedModel.addStudent(validStudent);
        String differentStudentId = "A6543210B";
        Student studentDifferentName = new Student(validStudent.getName(), new StudentId(differentStudentId),
                                                   validStudent.getClassId(), validStudent.getGrade());

        assertCommandSuccess(new AddCommand(studentDifferentName), expectedModel,
                String.format(AddCommand.MESSAGE_SUCCESS, studentDifferentName), expectedModel);
    }

    @Test
    public void execute_sameSameStudentIdDifferentName_failure() {
        Model expectedModel = new ModelManager(model.getProgrammerError(), new UserPrefs());
        expectedModel.addStudent(validStudent);
        String differentName = "Different Name";
        Student studentDifferentName = new Student(new Name(differentName), validStudent.getStudentId(),
                                                   validStudent.getClassId(), validStudent.getGrade());

        assertCommandFailure(new AddCommand(studentDifferentName), expectedModel, AddCommand.MESSAGE_DUPLICATE_STUDENT);
    }
}
