package seedu.tuitione.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tuitione.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.tuitione.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_REMARK_FINANCIAL_AID;
import static seedu.tuitione.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.tuitione.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tuitione.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.tuitione.testutil.TypicalTuition.MATH_S2;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.tuitione.commons.core.Messages;
import seedu.tuitione.commons.core.index.Index;
import seedu.tuitione.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.tuitione.model.Model;
import seedu.tuitione.model.ModelManager;
import seedu.tuitione.model.Tuitione;
import seedu.tuitione.model.UserPrefs;
import seedu.tuitione.model.student.Student;
import seedu.tuitione.testutil.EditStudentDescriptorBuilder;
import seedu.tuitione.testutil.StudentBuilder;
import seedu.tuitione.testutil.TypicalTuition;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(TypicalTuition.getTypicalTuitione(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredListStudentNoLessons_success() {

        Student editedStudent = new StudentBuilder().build();
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(editedStudent).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_STUDENT, descriptor);

        // friends is the original remark of the original student
        Student expectedStudent = new StudentBuilder(editedStudent).withRemarks("friends").build();
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, expectedStudent);

        Model expectedModel = new ModelManager(new Tuitione(model.getTuitione()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), expectedStudent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        // Student with no lessons
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());

        StudentBuilder studentInList = new StudentBuilder(firstStudent);
        Student editedStudent = studentInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withRemarks(VALID_REMARK_FINANCIAL_AID).build();

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withRemarks(VALID_REMARK_FINANCIAL_AID).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_STUDENT, descriptor);

        // friends is the original remark of the original student
        Student expectedStudent = new StudentBuilder(editedStudent)
                .withRemarks("friends", VALID_REMARK_FINANCIAL_AID).withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).build();
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, expectedStudent);

        Model expectedModel = new ModelManager(new Tuitione(model.getTuitione()), new UserPrefs());
        expectedModel.setStudent(firstStudent, expectedStudent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_STUDENT, new EditStudentDescriptor());
        Student editedStudent = model
                .getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new Tuitione(model.getTuitione()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        Student studentInFilteredList = model.getFilteredStudentList()
                .get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(studentInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_STUDENT,
                new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new Tuitione(model.getTuitione()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateStudentUnfilteredList_failure() {
        Student firstStudent = model
                .getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(firstStudent).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_STUDENT, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_duplicateStudentFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        // edit student in filtered list into a duplicate in tuitione book
        Student studentInList = model.getTuitione().getStudentList()
                .get(INDEX_SECOND_STUDENT.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_STUDENT,
                new EditStudentDescriptorBuilder(studentInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of tuitione book
     */
    @Test
    public void execute_invalidStudentIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of tuitione book list
        assertTrue(outOfBoundIndex.getZeroBased() < model
                .getTuitione().getStudentList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_STUDENT, DESC_AMY);

        // same values -> returns true
        EditStudentDescriptor copyDescriptor = new EditStudentDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_STUDENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_STUDENT, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_STUDENT, DESC_BOB)));
    }

    @Test
    public void execute_gradeFieldChanged_success() { // student unenrolled from existing lessons
        Model modelWithStudentsAndLessons = new ModelManager(TypicalTuition.getTypicalTuitione(), new UserPrefs());

        // get student BENSON
        Student secondStudent = modelWithStudentsAndLessons
                .getFilteredStudentList().get(INDEX_SECOND_STUDENT.getZeroBased());

        StudentBuilder studentInList = new StudentBuilder(secondStudent);
        Student editedStudent = studentInList.withGrade("S3").build();

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(secondStudent).withGrade("S3").build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_STUDENT, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        Model expectedModel =
                new ModelManager(new Tuitione(modelWithStudentsAndLessons.getTuitione()), new UserPrefs());
        expectedModel.setStudent(secondStudent, editedStudent);

        assertCommandSuccess(editCommand, modelWithStudentsAndLessons, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), modelWithStudentsAndLessons.getFilteredLessonList().get(3).getStudents());
        assertEquals(Collections.emptyList(), editedStudent.getLessons());
    }

    @Test
    public void execute_gradeFieldNotChanged_success() { // student still enrolled in existing lessons

        // create new model
        Model modelWithStudentsAndLessons = new ModelManager(TypicalTuition.getTypicalTuitione(), new UserPrefs());
        // get student BENSON
        Student secondStudent = modelWithStudentsAndLessons
                .getFilteredStudentList().get(INDEX_SECOND_STUDENT.getZeroBased());

        // edit name
        StudentBuilder studentInList = new StudentBuilder(secondStudent);
        Student editedStudentName = studentInList.withName("Ben").build();
        editedStudentName.addLesson(MATH_S2);

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(secondStudent).withName("Ben").build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_STUDENT, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudentName);

        Model expectedModel =
                new ModelManager(new Tuitione(modelWithStudentsAndLessons.getTuitione()), new UserPrefs());
        expectedModel.setStudent(secondStudent, editedStudentName);

        assertCommandSuccess(editCommand, modelWithStudentsAndLessons, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(editedStudentName),
                modelWithStudentsAndLessons.getFilteredLessonList().get(3).getStudents());
        assertEquals(Arrays.asList(MATH_S2), editedStudentName.getLessons());

        // create new model
        modelWithStudentsAndLessons = new ModelManager(TypicalTuition.getTypicalTuitione(), new UserPrefs());
        secondStudent = modelWithStudentsAndLessons
                .getFilteredStudentList().get(INDEX_SECOND_STUDENT.getZeroBased());
        studentInList = new StudentBuilder(secondStudent);

        //edit parent contact
        Student editedStudentPhone = studentInList.withPhone("91111111").build();
        editedStudentPhone.addLesson(MATH_S2);

        descriptor = new EditStudentDescriptorBuilder(secondStudent).withPhone("91111111").build();
        editCommand = new EditCommand(INDEX_SECOND_STUDENT, descriptor);

        expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudentPhone);

        expectedModel = new ModelManager(new Tuitione(modelWithStudentsAndLessons.getTuitione()), new UserPrefs());
        expectedModel.setStudent(secondStudent, editedStudentPhone);

        assertCommandSuccess(editCommand, modelWithStudentsAndLessons, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(editedStudentPhone),
                modelWithStudentsAndLessons.getFilteredLessonList().get(3).getStudents());
        assertEquals(Arrays.asList(MATH_S2), editedStudentPhone.getLessons());

    }



}
