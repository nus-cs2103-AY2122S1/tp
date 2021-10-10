package seedu.academydirectory.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.academydirectory.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.academydirectory.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.academydirectory.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.academydirectory.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.academydirectory.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.academydirectory.testutil.TypicalStudents.getTypicalAcademyDirectory;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.commons.core.Messages;
import seedu.academydirectory.commons.core.index.Index;
import seedu.academydirectory.model.AcademyDirectory;
import seedu.academydirectory.model.Model;
import seedu.academydirectory.model.ModelManager;
import seedu.academydirectory.model.UserPrefs;
import seedu.academydirectory.model.student.Student;
import seedu.academydirectory.testutil.StudentBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for ParticipationCommand
 */
public class ParticipationCommandTest {

    private static final int PARTICIPATION_COUNT_STUB = 12;
    private static final Integer STUDIO_SESSION_STUB = 1;
    private static final Index INDEX_STUB = INDEX_FIRST_STUDENT;
    private static final ArrayList<Index> INDEX_ARRAYLIST_STUB = new ArrayList<>();
    private static final ArrayList<Index> INDEX_ARRAYLIST_STUB_2 = new ArrayList<>();
    private static final int[] INTEGER_ARRAY_STUB = new int[10];

    private Model model = new ModelManager(getTypicalAcademyDirectory(), new UserPrefs());

    @Test
    public void execute_updateParticipation_success() {
        INDEX_ARRAYLIST_STUB.add(INDEX_STUB);
        INTEGER_ARRAY_STUB[0] = PARTICIPATION_COUNT_STUB;
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student firstStudentCopy = new StudentBuilder(firstStudent).withParticipation(INTEGER_ARRAY_STUB).build();
        Student editedStudent = new StudentBuilder(firstStudent).withParticipation(INTEGER_ARRAY_STUB).build();

        ParticipationCommand participationCommand =
                new ParticipationCommand(PARTICIPATION_COUNT_STUB, STUDIO_SESSION_STUB, INDEX_ARRAYLIST_STUB);
        String expectedMessage = ParticipationCommand.MESSAGE_UPDATE_PARTICIPATION_SUCCESS;

        Model expectedModel = new ModelManager(new AcademyDirectory(model.getAcademyDirectory()), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);

        // attendance before command is false
        assertFalse(editedStudent.getAttendance().getAttendanceArray()[STUDIO_SESSION_STUB - 1]);

        // assert command success
        assertCommandSuccess(participationCommand, model, expectedMessage, expectedModel);

        // attendance after command is true
        assertTrue(editedStudent.getAttendance().getAttendanceArray()[STUDIO_SESSION_STUB - 1]);

        INTEGER_ARRAY_STUB[0] = 0;
        Student otherEditedStudent = new StudentBuilder(firstStudent).withParticipation(INTEGER_ARRAY_STUB).build();
        expectedModel.setStudent(editedStudent, otherEditedStudent);

        ParticipationCommand resetParticipationCommand =
                new ParticipationCommand(-100, STUDIO_SESSION_STUB, INDEX_ARRAYLIST_STUB);

        assertCommandSuccess(resetParticipationCommand, model, expectedMessage, expectedModel);

        // reset model after tests
        expectedModel.setStudent(otherEditedStudent, firstStudentCopy);

    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        ArrayList<Index> indexArrayListOutOfBounds = new ArrayList<>();
        indexArrayListOutOfBounds.add(outOfBoundIndex);
        ParticipationCommand participationCommand =
                new ParticipationCommand(12, STUDIO_SESSION_STUB, indexArrayListOutOfBounds);
        assertCommandFailure(participationCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }
    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAcademyDirectory().getStudentList().size());
        ArrayList<Index> indexArrayListOutOfBounds = new ArrayList<>();
        indexArrayListOutOfBounds.add(outOfBoundIndex);
        ParticipationCommand participationCommand =
                new ParticipationCommand(12, STUDIO_SESSION_STUB, indexArrayListOutOfBounds);

        assertCommandFailure(participationCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        INDEX_ARRAYLIST_STUB.add(INDEX_FIRST_STUDENT);
        INDEX_ARRAYLIST_STUB_2.add(INDEX_SECOND_STUDENT);
        final ParticipationCommand standardCommand =
                new ParticipationCommand(12, STUDIO_SESSION_STUB, INDEX_ARRAYLIST_STUB);

        // same values -> returns true
        ParticipationCommand commandWithSameValues =
                new ParticipationCommand(12, STUDIO_SESSION_STUB, INDEX_ARRAYLIST_STUB);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different indexArrayList
        assertFalse(standardCommand.equals(
                new ParticipationCommand(12, STUDIO_SESSION_STUB, INDEX_ARRAYLIST_STUB_2)));

        // different participation
        assertFalse(standardCommand.equals(
                new ParticipationCommand(11, STUDIO_SESSION_STUB, INDEX_ARRAYLIST_STUB)));

        // different session
        assertFalse(standardCommand.equals(
                new ParticipationCommand(12, 2, INDEX_ARRAYLIST_STUB)));
    }
}
