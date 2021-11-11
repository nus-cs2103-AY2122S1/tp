package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showApplicationAtIndex;
import static seedu.address.testutil.TypicalApplications.getTypicalInternship;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.Application;
import seedu.address.model.application.Company;
import seedu.address.model.application.Completion;
import seedu.address.model.application.Deadline;
import seedu.address.model.application.InterviewDateAndTime;
import seedu.address.model.application.Position;
import seedu.address.model.application.Priority;
import seedu.address.model.application.Requirement;
import seedu.address.model.application.Status;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code RejectCommand}.
 */
public class RejectCommandTest {

    private final Model model = new ModelManager(getTypicalInternship(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicationList().size() + 1);
        RejectCommand rejectCommand = new RejectCommand(outOfBoundIndex);

        assertCommandFailure(rejectCommand, model, Messages.MESSAGE_INDEX_EXCEEDS_LIST_LENGTH);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);

        Index outOfBoundIndex = INDEX_SECOND_APPLICATION;
        // ensures that outOfBoundIndex is still in bounds of Internship list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternship().getApplicationList().size());

        RejectCommand rejectCommand = new RejectCommand(outOfBoundIndex);

        assertCommandFailure(rejectCommand, model, Messages.MESSAGE_INDEX_EXCEEDS_LIST_LENGTH);
    }

    @Test
    public void execute_rejectTwice_throwsCommandException() {

        //the second application is an application that is already rejected
        RejectCommand rejectCommand = new RejectCommand(INDEX_SECOND_APPLICATION);

        assertCommandFailure(rejectCommand, model, RejectCommand.MESSAGE_ALREADY_REJECTED);
    }

    @Test
    public void execute_validIndexApplicationList_success() {
        Application applicationToReject = model.getFilteredApplicationList()
                .get(INDEX_FIRST_APPLICATION.getZeroBased());

        Company company = applicationToReject.getCompany();
        Position position = applicationToReject.getPosition();
        Deadline deadline = applicationToReject.getDeadline();
        Completion completion = new Completion("Completed");
        Status status = new Status("Rejected");
        Priority priority = applicationToReject.getPriority();
        Set<Requirement> requirementList = applicationToReject.getRequirements();
        Set<InterviewDateAndTime> interviewDateAndTimeList = applicationToReject.getInterviewDateAndTime();

        Application addedApplication = new Application(company, position, deadline, completion, status, priority,
                requirementList, interviewDateAndTimeList);

        String expectedMessage = String.format(RejectCommand.MESSAGE_SUCCESS, addedApplication);
        ModelManager expectedModel = new ModelManager(model.getInternship(), new UserPrefs());
        expectedModel.setApplication(applicationToReject, addedApplication);

        RejectCommand rejectCommand = new RejectCommand(INDEX_FIRST_APPLICATION);

        assertCommandSuccess(rejectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        RejectCommand rejectFirstCommand = new RejectCommand(INDEX_FIRST_APPLICATION);
        RejectCommand rejectSecondCommand = new RejectCommand(INDEX_SECOND_APPLICATION);

        // same object -> returns true
        assertTrue(rejectFirstCommand.equals(rejectFirstCommand));

        // same values -> returns true
        RejectCommand rejectFirstCommandCopy = new RejectCommand(INDEX_FIRST_APPLICATION);
        assertTrue(rejectFirstCommand.equals(rejectFirstCommandCopy));

        // different types -> returns false
        assertFalse(rejectFirstCommand.equals(1));

        // null -> returns false
        assertFalse(rejectFirstCommand.equals(null));

        // different application -> returns false
        assertFalse(rejectFirstCommand.equals(rejectSecondCommand));
    }

}
