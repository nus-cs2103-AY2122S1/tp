package seedu.plannermd.model.doctor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.plannermd.testutil.doctor.TypicalDoctors.DR_ALICE;
import static seedu.plannermd.testutil.doctor.TypicalDoctors.DR_BOB;
import static seedu.plannermd.testutil.patient.TypicalPatients.ALICE;

import org.junit.jupiter.api.Test;

import seedu.plannermd.testutil.doctor.DoctorBuilder;

public class DoctorTest {

    @Test
    public void equals() {
        // same values -> returns true
        Doctor aliceCopy = new DoctorBuilder(DR_ALICE).build();
        assertTrue(DR_ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(DR_ALICE.equals(DR_ALICE));

        // null -> returns false
        assertFalse(DR_ALICE.equals(null));

        // different type -> returns false
        assertFalse(DR_ALICE.equals(5));

        // different doctor -> returns false
        assertFalse(DR_ALICE.equals(DR_BOB));

        // different name -> returns false
        Doctor editedAlice = new DoctorBuilder(DR_ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new DoctorBuilder(DR_ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new DoctorBuilder(DR_ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new DoctorBuilder(DR_ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new DoctorBuilder(DR_ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));

    }
}
