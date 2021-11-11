package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.AMAZON;
import static seedu.address.testutil.TypicalApplications.BYTEDANCE;
import static seedu.address.testutil.TypicalApplications.GRAB;
import static seedu.address.testutil.TypicalApplications.getTypicalInternship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Internship;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortCommand.
 */
public class SortCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInternship(), new UserPrefs());
    }

    @Test
    public void constructor_nullParameter_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortCommand(null));
    }

    @Test
    public void execute_sortByCompanyName_success() {
        Internship expectedInternship = new Internship();
        expectedInternship.addApplication(AMAZON);
        expectedInternship.addApplication(BYTEDANCE);
        expectedInternship.addApplication(GRAB);
        expectedModel = new ModelManager(expectedInternship, new UserPrefs());
        assertCommandSuccess(new SortCommand("company"), model,
                String.format(SortCommand.MESSAGE_SUCCESS, "company"), expectedModel);
    }

    @Test
    public void execute_sortByPosition_success() {
        Internship expectedInternship = new Internship();
        expectedInternship.addApplication(GRAB);
        expectedInternship.addApplication(AMAZON);
        expectedInternship.addApplication(BYTEDANCE);
        expectedModel = new ModelManager(expectedInternship, new UserPrefs());
        assertCommandSuccess(new SortCommand("position"), model,
                String.format(SortCommand.MESSAGE_SUCCESS, "position"), expectedModel);
    }

    @Test
    public void execute_sortByDeadline_success() {
        Internship expectedInternship = new Internship();
        expectedInternship.addApplication(GRAB);
        expectedInternship.addApplication(BYTEDANCE);
        expectedInternship.addApplication(AMAZON);
        expectedModel = new ModelManager(expectedInternship, new UserPrefs());
        assertCommandSuccess(new SortCommand("deadline"), model,
                String.format(SortCommand.MESSAGE_SUCCESS, "deadline"), expectedModel);
    }

    @Test
    public void execute_sortByInterview_success() {
        Internship expectedInternship = new Internship();
        expectedInternship.addApplication(BYTEDANCE);
        expectedInternship.addApplication(GRAB);
        expectedInternship.addApplication(AMAZON);
        expectedModel = new ModelManager(expectedInternship, new UserPrefs());
        assertCommandSuccess(new SortCommand("interview"), model,
                String.format(SortCommand.MESSAGE_SUCCESS, "interview"), expectedModel);
    }

    @Test
    public void execute_sortByInterviewNoInterviews_noInterviewMessage() {
        Internship noInterviewInternship = new Internship();
        noInterviewInternship.addApplication(AMAZON);
        Model noInterviewModel = new ModelManager(noInterviewInternship, new UserPrefs());
        assertCommandSuccess(new SortCommand("interview"), noInterviewModel,
                SortCommand.MESSAGE_NO_INTERVIEWS, noInterviewModel);
    }

    @Test
    public void execute_sortByPriority_success() {
        Internship expectedInternship = new Internship();
        expectedInternship.addApplication(BYTEDANCE);
        expectedInternship.addApplication(AMAZON);
        expectedInternship.addApplication(GRAB);
        expectedModel = new ModelManager(expectedInternship, new UserPrefs());
        assertCommandSuccess(new SortCommand("priority"), model,
                String.format(SortCommand.MESSAGE_SUCCESS, "priority"), expectedModel);
    }

    @Test
    public void execute_sortEmptyList_emptyListMessage() {
        Internship emptyInternship = new Internship();
        Model emptyModel = new ModelManager(emptyInternship, new UserPrefs());
        assertCommandSuccess(new SortCommand("priority"), emptyModel,
                SortCommand.MESSAGE_EMPTY_LIST, emptyModel);
    }


    @Test
    public void equals() {
        final SortCommand standardCommand = new SortCommand("company");

        // same values -> returns true
        SortCommand commandWithSameValues = new SortCommand("company");
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different parameter -> returns false
        assertFalse(standardCommand.equals(new SortCommand("priority")));
    }

}
