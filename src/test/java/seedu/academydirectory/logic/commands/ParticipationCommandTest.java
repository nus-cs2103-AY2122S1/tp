package seedu.academydirectory.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.academydirectory.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.academydirectory.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.academydirectory.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.academydirectory.testutil.TypicalStudents.getTypicalAcademyDirectory;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.commons.core.Messages;
import seedu.academydirectory.commons.core.index.Index;
import seedu.academydirectory.model.Model;
import seedu.academydirectory.model.ModelManager;
import seedu.academydirectory.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ParticipationCommand
 */
public class ParticipationCommandTest {

    private static final Integer STUDIO_SESSION_STUB = 1;
    private static final ArrayList<Index> INDEX_ARRAYLIST_STUB = new ArrayList<>();
    private static final ArrayList<Index> INDEX_ARRAYLIST_STUB_2 = new ArrayList<>();

    private Model model = new ModelManager(getTypicalAcademyDirectory(), new UserPrefs());

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
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
