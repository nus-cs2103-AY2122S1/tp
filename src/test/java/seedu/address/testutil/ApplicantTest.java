package seedu.address.model.applicant;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

public class ApplicantTest {

    @Test
    public void isSameApplicant() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));



    }

}
