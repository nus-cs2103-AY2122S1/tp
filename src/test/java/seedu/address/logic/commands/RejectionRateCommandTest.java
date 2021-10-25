package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.RejectionRateCommand.MESSAGE_NO_CURRENT_APPLICANTS;
import static seedu.address.logic.commands.RejectionRateCommand.MESSAGE_SUCCESS;
import static seedu.address.model.applicant.Application.ApplicationStatus.REJECTED;
import static seedu.address.testutil.TypicalPositions.DATASCIENTIST;
import static seedu.address.testutil.TypicalPositions.getTypicalPositionBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ApplicantBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;
import seedu.address.testutil.ApplicantBuilder;
import seedu.address.testutil.PositionBuilder;

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
        Applicant applicant = new ApplicantBuilder().build();

        Position position = new PositionBuilder().build();

        ApplicantBook applicantBook = new ApplicantBook();

        applicantBook.addApplicant(applicant);

        model.setApplicantBook(applicantBook);

        model.addPosition(position);

        CommandResult commandResult = new RejectionRateCommand(position.getTitle()).execute(model);
        float rejectionRate = model.calculateRejectionRate(position.getTitle()); // 0%

        assertEquals(String.format(MESSAGE_SUCCESS, position.getTitle(), rejectionRate),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_changeRejectionRateAfterUpdatingApplicant_successful() {
        Applicant applicant = new ApplicantBuilder().build();

        Position position = new PositionBuilder().build();

        ApplicantBook applicantBook = new ApplicantBook();

        applicantBook.addApplicant(applicant);

        model.setApplicantBook(applicantBook);

        model.addPosition(position);

        float initialRejectionRate = model.calculateRejectionRate(position.getTitle()); // currently 0.0%

        Applicant newApplicant = applicant.markAs(REJECTED);

        model.setApplicant(applicant, newApplicant);

        float newRejectionRate = model.calculateRejectionRate(position.getTitle()); // now 100.0%

        assertNotEquals(initialRejectionRate, newRejectionRate);
    }

    @Test
    public void execute_changeRejectionRateAfterAddingMoreApplicants_successful() {
        // Create Amy and set status to REJECTED
        Applicant applicant = new ApplicantBuilder().build();

        ApplicantBook applicantBook = new ApplicantBook();

        applicantBook.addApplicant(applicant);

        model.setApplicantBook(applicantBook);

        Position position = new PositionBuilder().build();

        model.addPosition(position);

        Applicant newApplicant = applicant.markAs(REJECTED);

        model.setApplicant(applicant, newApplicant);

        float initialRejectionRate = model.calculateRejectionRate(position.getTitle()); // 100%

        // Create Alice
        Applicant otherApplicant = new ApplicantBuilder().withName("Alice").build();

        applicantBook.setApplicant(applicant, newApplicant);
        applicantBook.addApplicant(otherApplicant);

        model.setApplicantBook(applicantBook);

        float newRejectionRate = model.calculateRejectionRate(position.getTitle()); // 50%

        assertNotEquals(initialRejectionRate, newRejectionRate);
    }

    @Test
    public void execute_changeRejectionRateAfterDeletingApplicants_successful() {
        // Create Amy and set status to REJECTED
        Position position = new PositionBuilder().build();

        model.addPosition(position);

        Applicant applicant = new ApplicantBuilder().build();

        ApplicantBook applicantBook = new ApplicantBook();

        applicantBook.addApplicant(applicant);

        model.setApplicantBook(applicantBook);

        Applicant newApplicant = applicant.markAs(REJECTED);

        // Create Alice
        Applicant otherApplicant = new ApplicantBuilder().withName("Alice").build();

        applicantBook.setApplicant(applicant, newApplicant);
        applicantBook.addApplicant(otherApplicant);

        model.setApplicantBook(applicantBook);

        float oldRejectionRate = model.calculateRejectionRate(position.getTitle()); // 50%

        model.deleteApplicant(newApplicant);

        float newRejectionRate = model.calculateRejectionRate(position.getTitle()); // 0%

        assertNotEquals(oldRejectionRate, newRejectionRate);
    }
}

