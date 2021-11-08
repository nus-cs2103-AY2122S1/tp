package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailureWithoutException;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.RemoveStudentCommand.MESSAGE_REMOVE_STUDENT_FAILURE;
import static seedu.address.logic.commands.RemoveStudentCommand.MESSAGE_REMOVE_STUDENT_SUCCESS;
import static seedu.address.testutil.TypicalClasses.addTypicalClassesToAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_TENTH;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;
import static seedu.address.testutil.TypicalStudents.getAddressBookWithTypicalStudents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuition.TuitionClass;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemoveStudentCommandTest.
 */
public class RemoveStudentCommandTest {

    private Model model = new ModelManager(addTypicalClassesToAddressBook(getAddressBookWithTypicalStudents()),
            new UserPrefs());

    @Test
    public void execute_classDoesNotExist_failure() {
        RemoveStudentCommand removeStudentCommand = new RemoveStudentCommand(List.of(INDEX_FIRST), INDEX_TENTH);
        assertCommandFailure(removeStudentCommand, model, Messages.MESSAGE_CLASS_NOT_FOUND);
    }

    @Test
    public void execute_removeInvalidIndexes_failure() {
        RemoveStudentCommand removeStudentCommand = new RemoveStudentCommand(List.of(INDEX_TENTH), INDEX_FIRST);
        String message = "This student is not found.";
        assertCommandFailure(removeStudentCommand, model, message);
    }

    @Test
    public void execute_removeOneStudent_success() {
        TuitionClass tuitionClass = getNewModel().getTuitionClass(INDEX_FIRST);
        Model expectedModel = setStudentsInClasses(model, tuitionClass, INDEX_FIRST);

        List<Index> indices = List.of(INDEX_FIRST);
        RemoveStudentCommand removeStudentCommand = new RemoveStudentCommand(indices, INDEX_FIRST);
        String expectedMessage = String.format(MESSAGE_REMOVE_STUDENT_SUCCESS + "\n",
                getNames(indices, expectedModel), tuitionClass.getName());

        assertCommandSuccess(removeStudentCommand, expectedModel, expectedMessage, getNewModel());
    }

    @Test
    public void execute_outOfBoundsClassIndex_failure() {
        Index outOfBounds = Index.fromOneBased(model.getFilteredTuitionList().size() + 1);

        RemoveStudentCommand removeStudentCommand = new RemoveStudentCommand(List.of(INDEX_FIRST), outOfBounds);

        assertCommandFailure(removeStudentCommand, model, Messages.MESSAGE_CLASS_NOT_FOUND);
    }

    @Test
    public void execute_removeMultipleStudents_success() {
        TuitionClass tuitionClass = getNewModel().getTuitionClass(INDEX_FIRST);
        Model expectedModel = setStudentsInClasses(model, tuitionClass, INDEX_FIRST, INDEX_SECOND, INDEX_THIRD);

        List<Index> indices = List.of(INDEX_FIRST, INDEX_SECOND, INDEX_THIRD);
        RemoveStudentCommand removeStudentCommand = new RemoveStudentCommand(indices, INDEX_FIRST);

        String expectedMessage = String.format(MESSAGE_REMOVE_STUDENT_SUCCESS + "\n",
                getNames(indices, expectedModel), tuitionClass.getName());
        assertCommandSuccess(removeStudentCommand, expectedModel, expectedMessage, getNewModel());
    }

    @Test
    public void execute_removeFromEmptyClass_failure() {
        model = getNewModel();
        TuitionClass tuitionClass = model.getTuitionClass(INDEX_SECOND);
        TuitionClass editedClass = tuitionClass.changeStudents(new ArrayList<>());
        model.setTuition(tuitionClass, editedClass);

        List<Index> indices = List.of(INDEX_FIRST, INDEX_SECOND);

        RemoveStudentCommand removeStudentCommand = new RemoveStudentCommand(indices, INDEX_SECOND);

        String expectedMessage = String.format(MESSAGE_REMOVE_STUDENT_FAILURE,
                getNames(indices, model), tuitionClass.getName());

        assertCommandFailureWithoutException(removeStudentCommand, model, expectedMessage);
    }

    @Test
    public void execute_removeStudentNotInClass_failure() {
        model = getNewModel();
        TuitionClass tuitionClass = model.getTuitionClass(INDEX_FIRST);
        tuitionClass.changeStudents(new ArrayList<>());
        Model expectedModel = setStudentsInClasses(model, tuitionClass, INDEX_FIRST);

        List<Index> indices = List.of(INDEX_SECOND);

        RemoveStudentCommand removeStudentCommand = new RemoveStudentCommand(indices, INDEX_FIRST);

        String expectedMessage = String.format(MESSAGE_REMOVE_STUDENT_FAILURE,
                getNames(List.of(INDEX_SECOND), expectedModel), tuitionClass.getName());

        assertCommandFailureWithoutException(removeStudentCommand, expectedModel, expectedMessage);
    }

    @Test
    public void execute_removeInvalidStudent_failure() {
        RemoveStudentCommand removeStudentCommand = new RemoveStudentCommand(List.of(INDEX_TENTH), INDEX_FIRST);

        assertCommandFailure(removeStudentCommand, model, Messages.MESSAGE_STUDENT_NOT_FOUND);
    }

    @Test
    public void equals() {
        RemoveStudentCommand removeStudentCommand = new RemoveStudentCommand(List.of(INDEX_FIRST), INDEX_FIRST);
        RemoveStudentCommand command = new RemoveStudentCommand(List.of(INDEX_SECOND, INDEX_THIRD), INDEX_FIRST);

        // same object -> returns true
        assertTrue(removeStudentCommand.equals(removeStudentCommand));

        // same values -> returns true
        RemoveStudentCommand copyFirstCommand = new RemoveStudentCommand(List.of(INDEX_FIRST), INDEX_FIRST);

        assertTrue(copyFirstCommand.equals(removeStudentCommand));

        // different types -> returns false
        assertFalse(removeStudentCommand.equals(1));

        // null -> returns false
        assertFalse(removeStudentCommand.equals(null));

        // different students -> returns false
        assertFalse(removeStudentCommand.equals(command));

    }

    private Model setStudentsInClasses(Model expectedModel, TuitionClass tuitionClass, Index ... indices) {
        List<Student> students = Arrays.stream(indices).map(i -> model.getStudent(i)).collect(Collectors.toList());
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            ArrayList<Integer> classes = student.addClass(tuitionClass).getClasses();
            Student editedStudent = new StudentBuilder(student).withClasses(classes)
                    .withTags(new Tag(String.format("%s | %s", tuitionClass.getName().getName(),
                    tuitionClass.getTimeslot()))).build();
            TuitionClass editedClass = expectedModel.addToClass(tuitionClass, editedStudent);
            expectedModel.setStudent(student, editedStudent);
        }
        return expectedModel;
    }

    private List<String> getNames(List<Index> indices, Model expectedModel) {
        return indices.stream().map(i -> expectedModel.getStudent(i).getNameString()).collect(Collectors.toList());
    }

    private static Model getNewModel() {
        return new ModelManager(addTypicalClassesToAddressBook(getAddressBookWithTypicalStudents()),
                new UserPrefs());
    }
}
