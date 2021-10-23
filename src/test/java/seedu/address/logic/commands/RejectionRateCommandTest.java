package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.RejectionRateCommand.MESSAGE_NO_CURRENT_APPLICANTS;
import static seedu.address.logic.commands.RejectionRateCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPositions.DATASCIENTIST;
import static seedu.address.testutil.TypicalPositions.getTypicalPositionBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.descriptors.EditApplicantDescriptor;
import seedu.address.logic.descriptors.EditApplicationDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.applicantparticulars.ApplicantParticulars;
import seedu.address.model.application.Application;
import seedu.address.model.application.Application.ApplicationStatus;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;
import seedu.address.testutil.TypicalApplicants;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code RejectionRateCommand}.
 */
public class RejectionRateCommandTest {
    private final Model model = new ModelManager(getTypicalPositionBook(), new UserPrefs());

    @Test
    public void constructor_nullPosition_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RejectionRateCommand(null));
    }

    @Test
    public void execute_nonExistingPosition_throwsCommandException() {
        assertThrows(CommandException.class, () -> new RejectionRateCommand(new Title("Impossible")).execute(model));
    }

    @Test
    public void execute_existingPositionWithoutApplicant_successful() throws Exception {
        assertEquals(new CommandResult(MESSAGE_NO_CURRENT_APPLICANTS),
                new RejectionRateCommand(DATASCIENTIST.getTitle()).execute(model));
    }

    @Test
    public void execute_existingPositionWithExistingApplicant_successful() throws Exception {
        ApplicantParticulars applicantParticulars = TypicalApplicants.getApplicantParticulars(TypicalApplicants.AMY,
                DATASCIENTIST.getTitle());
        Applicant applicant = new Applicant(applicantParticulars, DATASCIENTIST);
        model.addApplicantWithParticulars(applicantParticulars);
        applicant.markAs(ApplicationStatus.REJECTED);

        assertTrue(model.hasApplicantWithName(applicant.getName()));
        assertTrue(model.hasPosition(DATASCIENTIST));

        CommandResult commandResult = new RejectionRateCommand(DATASCIENTIST.getTitle()).execute(model);
        float rejectionRate = model.calculateRejectionRate(DATASCIENTIST.getTitle());

        assertEquals(String.format(MESSAGE_SUCCESS, DATASCIENTIST.getTitle(), rejectionRate),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_changeRejectionRateAfterUpdatingApplicant_successful() {
        Position validPosition = DATASCIENTIST;
        ApplicantParticulars applicantParticulars = TypicalApplicants.getApplicantParticulars(TypicalApplicants.AMY,
                DATASCIENTIST.getTitle());

        Applicant applicant = new Applicant(applicantParticulars, DATASCIENTIST);

        model.addApplicantWithParticulars(applicantParticulars);

        float initialRejectionRate = model.calculateRejectionRate(validPosition.getTitle()); // currently 0.0%

        EditApplicationDescriptor editApplicationDescriptor = new EditApplicationDescriptor();
        editApplicationDescriptor.setPosition(DATASCIENTIST);
        editApplicationDescriptor.setStatus(ApplicationStatus.REJECTED);

        Application updatedApplication = editApplicationDescriptor.createEditedApplication(
                applicant.getApplication());

        EditApplicantDescriptor editApplicantDescriptor = new EditApplicantDescriptor();
        editApplicantDescriptor.setApplication(updatedApplication);

        Applicant newApplicant = editApplicantDescriptor.createEditedApplicant(applicant);

        model.setApplicant(applicant, newApplicant);

        float newRejectionRate = model.calculateRejectionRate(validPosition.getTitle()); // now 100.0%

        assertNotEquals(initialRejectionRate, newRejectionRate);
    }

    @Test
    public void execute_changeRejectionRateAfterAddingMoreApplicants_successful() {
        // Create Amy and set status to REJECTED
        Position validPosition = DATASCIENTIST;
        ApplicantParticulars applicantParticulars = TypicalApplicants.getApplicantParticulars(
                TypicalApplicants.AMY,
                validPosition.getTitle());

        Applicant applicant = new Applicant(applicantParticulars, DATASCIENTIST);

        model.addApplicantWithParticulars(applicantParticulars);

        EditApplicationDescriptor editApplicationDescriptor = new EditApplicationDescriptor();
        editApplicationDescriptor.setPosition(DATASCIENTIST);
        editApplicationDescriptor.setStatus(ApplicationStatus.REJECTED);

        Application updatedApplication = editApplicationDescriptor.createEditedApplication(
                applicant.getApplication());

        EditApplicantDescriptor editApplicantDescriptor = new EditApplicantDescriptor();
        editApplicantDescriptor.setApplication(updatedApplication);

        Applicant newApplicant = editApplicantDescriptor.createEditedApplicant(applicant);

        model.setApplicant(applicant, newApplicant);

        float initialRejectionRate = model.calculateRejectionRate(validPosition.getTitle()); // 100%

        // Create Alice
        ApplicantParticulars otherApplicantParticulars = TypicalApplicants.getApplicantParticulars(
                TypicalApplicants.ALICE,
                validPosition.getTitle());

        model.addApplicantWithParticulars(otherApplicantParticulars);

        float newRejectionRate = model.calculateRejectionRate(validPosition.getTitle()); // 50%

        assertNotEquals(initialRejectionRate, newRejectionRate);
        assertEquals(newRejectionRate, 50.0);

        // Set Alice status as REJECTED
        Applicant otherApplicant = new Applicant(otherApplicantParticulars, validPosition);

        Application otherUpdatedApplication = editApplicationDescriptor.createEditedApplication(
                applicant.getApplication());

        editApplicantDescriptor.setApplication(otherUpdatedApplication);

        Applicant newOtherApplicant = editApplicantDescriptor.createEditedApplicant(otherApplicant);

        model.setApplicant(otherApplicant, newOtherApplicant);

        float otherRejectionRate = model.calculateRejectionRate(validPosition.getTitle()); // 100%

        assertNotEquals(newRejectionRate, otherRejectionRate);
        assertEquals(otherRejectionRate, 100.0);
    }

    @Test
    public void execute_changeRejectionRateAfterDeletingApplicants_successful() {
        // Create Amy and set status to REJECTED
        Position validPosition = DATASCIENTIST;
        ApplicantParticulars applicantParticulars = TypicalApplicants.getApplicantParticulars(
                TypicalApplicants.AMY,
                validPosition.getTitle());

        Applicant applicant = new Applicant(applicantParticulars, DATASCIENTIST);

        model.addApplicantWithParticulars(applicantParticulars);

        EditApplicationDescriptor editApplicationDescriptor = new EditApplicationDescriptor();
        editApplicationDescriptor.setPosition(DATASCIENTIST);
        editApplicationDescriptor.setStatus(ApplicationStatus.REJECTED);

        Application updatedApplication = editApplicationDescriptor.createEditedApplication(
                applicant.getApplication());

        EditApplicantDescriptor editApplicantDescriptor = new EditApplicantDescriptor();
        editApplicantDescriptor.setApplication(updatedApplication);

        Applicant newApplicant = editApplicantDescriptor.createEditedApplicant(applicant);

        model.setApplicant(applicant, newApplicant);

        // Create Alice
        ApplicantParticulars otherApplicantParticulars = TypicalApplicants.getApplicantParticulars(
                TypicalApplicants.ALICE,
                validPosition.getTitle());

        model.addApplicantWithParticulars(otherApplicantParticulars);

        float oldRejectionRate = model.calculateRejectionRate(validPosition.getTitle()); // 50%
        assertEquals(oldRejectionRate, 50.0);

        model.deleteApplicant(newApplicant);

        float newRejectionRate = model.calculateRejectionRate(validPosition.getTitle()); // 0%

        assertNotEquals(oldRejectionRate, newRejectionRate);
        assertEquals(newRejectionRate, 0.0);
    }
}

