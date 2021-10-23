package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.RejectionRateCommand.MESSAGE_NO_CURRENT_APPLICANTS;
import static seedu.address.logic.commands.RejectionRateCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPositions.DATASCIENTIST;
import static seedu.address.testutil.TypicalPositions.getTypicalPositionBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.Address;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Email;
import seedu.address.model.applicant.Name;
import seedu.address.model.applicant.Phone;
import seedu.address.model.applicant.applicantparticulars.ApplicantParticulars;
import seedu.address.model.application.Application.ApplicationStatus;
import seedu.address.model.position.Title;

public class RejectionRateCommandTest {
    private Model model = new ModelManager(getTypicalPositionBook(), new UserPrefs());

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
        ApplicantParticulars applicantParticulars = new ApplicantParticulars(new Name("Amy"), new Phone("12345678"),
                new Email("test@gmail.com"),
                new Address("Blk 123, Jurong West Ave 6, #08-111"),
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

    //    @Test
    //    public void execute_changeRejectionRateAfterAddingApplicant_successful() {
    //        Position validPosition = DATASCIENTIST;
    //        ApplicantParticulars applicantParticulars = new ApplicantParticulars(new Name("Amy"),
    //                  new Phone("12345678")
    //                , new Email("test@gmail.com")
    //                , new Address("Blk 123, Jurong West Ave 6, #08-111")
    //                , validPosition.getTitle());
    //        Applicant applicant = new Applicant(applicantParticulars, validPosition)
    //                .markAs(ApplicationStatus.REJECTED);
    //
    //        model.addApplicantWithParticulars(applicantParticulars);
    //
    //        float initialRejectionRate = model.calculateRejectionRate(validPosition);
    //
    //        ApplicantParticulars otherApplicantParticulars = new ApplicantParticulars(new Name("Bob"),
    //                new Phone("12345678"),
    //                new Email("test@gmail.com"),
    //                new Address("Blk 123, Jurong West Ave 6, #08-111"),
    //                validPosition.getTitle());
    //        Applicant otherApplicant = new Applicant(otherApplicantParticulars, validPosition);
    //        otherApplicant.markAs(ApplicationStatus.REJECTED);
    //
    //        model.addApplicantWithParticulars(otherApplicantParticulars);
    //
    //        float newRejectionRate = model.calculateRejectionRate(validPosition);
    //
    //        assertNotEquals(initialRejectionRate, newRejectionRate);
    //    }
    //
    //    @Test
    //    public void execute_changeRejectionRateAfterDeletingApplicant_successful() { ;
    //        Position validPosition = DATASCIENTIST;
    //        ApplicantParticulars applicantParticulars = new ApplicantParticulars(new Name("Amy"),
    //                  new Phone("12345678")
    //                , new Email("test@gmail.com")
    //                , new Address("Blk 123, Jurong West Ave 6, #08-111")
    //                , validPosition.getTitle());
    //        model.addApplicantWithParticulars(applicantParticulars);
    //        Applicant applicant = new Applicant(applicantParticulars, validPosition);
    //        applicant.markAs(ApplicationStatus.REJECTED);
    //
    //        ApplicantParticulars otherApplicantParticulars = new ApplicantParticulars(new Name("Bob"),
    //                new Phone("12345678"),
    //                new Email("test@gmail.com"),
    //                new Address("Blk 123, Jurong West Ave 6, #08-111"),
    //                validPosition.getTitle());
    //        model.addApplicantWithParticulars(otherApplicantParticulars);
    //        Applicant otherApplicant = new Applicant(otherApplicantParticulars, validPosition);
    //        otherApplicant.markAs(ApplicationStatus.REJECTED);
    //
    //        float initialRejectionRate = model.calculateRejectionRate(validPosition);
    //
    //        model.deleteApplicant(applicant);
    //
    //        float newRejectionRate = model.calculateRejectionRate(validPosition);
    //
    //        assertNotEquals(initialRejectionRate, newRejectionRate);
    //    }
}

