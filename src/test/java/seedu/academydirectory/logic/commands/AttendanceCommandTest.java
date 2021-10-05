package seedu.academydirectory.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.academydirectory.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.academydirectory.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.academydirectory.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.academydirectory.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.academydirectory.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.academydirectory.testutil.TypicalPersons.getTypicalAcademyDirectory;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.commons.core.Messages;
import seedu.academydirectory.commons.core.index.Index;
import seedu.academydirectory.model.AcademyDirectory;
import seedu.academydirectory.model.Model;
import seedu.academydirectory.model.ModelManager;
import seedu.academydirectory.model.UserPrefs;
import seedu.academydirectory.model.person.Person;
import seedu.academydirectory.testutil.PersonBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for AttendanceCommand
 */
public class AttendanceCommandTest {

    private static final boolean STUDIO_ATTENDANCE_STUB = true;
    private static final Integer STUDIO_SESSION_STUB = 1;
    private static final Index INDEX_STUB = INDEX_FIRST_PERSON;
    private static final ArrayList<Index> INDEX_ARRAYLIST_STUB = new ArrayList<>();
    private static final ArrayList<Index> INDEX_ARRAYLIST_STUB_2 = new ArrayList<>();
    private static final boolean[] BOOLEAN_ARRAY_STUB = new boolean[10];

    private Model model = new ModelManager(getTypicalAcademyDirectory(), new UserPrefs());

    @Test
    public void execute_updateAttendance_success() {
        INDEX_ARRAYLIST_STUB.add(INDEX_STUB);
        BOOLEAN_ARRAY_STUB[0] = true;
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withAttendance(BOOLEAN_ARRAY_STUB).build();

        AttendanceCommand attendanceCommand =
                new AttendanceCommand(STUDIO_ATTENDANCE_STUB, STUDIO_SESSION_STUB, INDEX_ARRAYLIST_STUB);
        String expectedMessage = AttendanceCommand.MESSAGE_UPDATE_ATTENDANCE_SUCCESS;

        Model expectedModel = new ModelManager(new AcademyDirectory(model.getAcademyDirectory()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);
        assertCommandSuccess(attendanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ArrayList<Index> indexArrayListOutOfBounds = new ArrayList<>();
        indexArrayListOutOfBounds.add(outOfBoundIndex);
        AttendanceCommand attendanceCommand =
                new AttendanceCommand(STUDIO_ATTENDANCE_STUB, STUDIO_SESSION_STUB, indexArrayListOutOfBounds);
        assertCommandFailure(attendanceCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAcademyDirectory().getPersonList().size());
        ArrayList<Index> indexArrayListOutOfBounds = new ArrayList<>();
        indexArrayListOutOfBounds.add(outOfBoundIndex);
        AttendanceCommand attendanceCommand =
                new AttendanceCommand(STUDIO_ATTENDANCE_STUB, STUDIO_SESSION_STUB, indexArrayListOutOfBounds);

        assertCommandFailure(attendanceCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        INDEX_ARRAYLIST_STUB.add(INDEX_FIRST_PERSON);
        INDEX_ARRAYLIST_STUB_2.add(INDEX_SECOND_PERSON);
        final AttendanceCommand standardCommand =
                new AttendanceCommand(STUDIO_ATTENDANCE_STUB, STUDIO_SESSION_STUB, INDEX_ARRAYLIST_STUB);

        // same values -> returns true
        AttendanceCommand commandWithSameValues =
                new AttendanceCommand(STUDIO_ATTENDANCE_STUB, STUDIO_SESSION_STUB, INDEX_ARRAYLIST_STUB);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different indexArrayList
        assertFalse(standardCommand.equals(
                new AttendanceCommand(STUDIO_ATTENDANCE_STUB, STUDIO_SESSION_STUB, INDEX_ARRAYLIST_STUB_2)));

        // different attendance
        assertFalse(standardCommand.equals(
                new AttendanceCommand(false, STUDIO_SESSION_STUB, INDEX_ARRAYLIST_STUB)));

        // different session
        assertFalse(standardCommand.equals(
                new AttendanceCommand(STUDIO_ATTENDANCE_STUB, 2, INDEX_ARRAYLIST_STUB)));
    }
}
