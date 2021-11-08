package seedu.programmer.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.programmer.testutil.TypicalStudents.ALICE;

import org.junit.jupiter.api.Test;

class QueryStudentDescriptorTest {

    @Test
    void isAnyFieldToBeQueried_allNull_returnFalse() {
        QueryStudentDescriptor descriptor =
                new QueryStudentDescriptor(null, null, null, null);
        assertFalse(descriptor.isAnyFieldToBeQueried());
    }

    @Test
    void isAnyFieldToBeQueried_notAllNull_returnTrue() {
        // name not null
        QueryStudentDescriptor descriptor =
                new QueryStudentDescriptor("Alice", null, null, null);
        assertTrue(descriptor.isAnyFieldToBeQueried());

        // student ID not null
        descriptor =
                new QueryStudentDescriptor(null, "A1234567X", null, null);
        assertTrue(descriptor.isAnyFieldToBeQueried());

        // class ID not null
        descriptor =
                new QueryStudentDescriptor(null, null, "B01", null);
        assertTrue(descriptor.isAnyFieldToBeQueried());

        // email not null
        descriptor =
                new QueryStudentDescriptor(null, null, null, "e123456");
        assertTrue(descriptor.isAnyFieldToBeQueried());
    }

    @Test
    void isAnyFieldToBeQueried_noNull_returnTrue() {
        QueryStudentDescriptor descriptor =
                new QueryStudentDescriptor("Alice", "A1234567X", "B01", "e123456");
        assertTrue(descriptor.isAnyFieldToBeQueried());
    }

    @Test
    void doesStudentMatchDescriptor_detailsMatch_returnsTrue() {
        String nameAlice = "Alice Pauline";
        String sidAlice = "A0212425H";
        String cidAlice = "B01";
        String emailAlice = "e0422331@u.nus.edu";

        // match name only
        QueryStudentDescriptor descriptorAliceOne =
                new QueryStudentDescriptor(nameAlice, null, null, null);
        assertTrue(descriptorAliceOne.doesStudentMatchDescriptor(ALICE));

        // match four fields
        QueryStudentDescriptor descriptorAliceAll =
                new QueryStudentDescriptor(nameAlice, sidAlice, cidAlice, emailAlice);
        assertTrue(descriptorAliceOne.doesStudentMatchDescriptor(ALICE));
    }

    @Test
    void doesStudentMatchDescriptor_detailsDoesNotMatch_returnsFalse() {
        String nameFake = "Fake Name";
        String sidFake = "A1234567X";
        String cidFake = "B11";
        String emailFake = "e1234567@u.nus.edu";

        // name does not match
        QueryStudentDescriptor descriptorFakeOne =
                new QueryStudentDescriptor(nameFake, null, null, null);
        assertFalse(descriptorFakeOne.doesStudentMatchDescriptor(ALICE));

        // all fields do not match
        QueryStudentDescriptor descriptorFakeAll =
                new QueryStudentDescriptor(nameFake, sidFake, cidFake, emailFake);
        assertFalse(descriptorFakeAll.doesStudentMatchDescriptor(ALICE));
    }

    @Test
    void equals() {
        String nameOne = "nameOne";
        String sidOne = "sidOne";
        String cidOne = "cidOne";
        String emailOne = "emailOne";

        String nameTwo = "nameTwo";
        String sidTwo = "sidTwo";
        String cidTwo = "cidTwo";
        String emailTwo = "emailTwo";
        QueryStudentDescriptor descriptorOne =
                new QueryStudentDescriptor(nameOne, sidOne, cidOne, emailOne);
        QueryStudentDescriptor descriptorTwo =
                new QueryStudentDescriptor(nameTwo, sidTwo, cidTwo, emailTwo);

        // same object -> returns true
        assertEquals(descriptorOne, descriptorOne);

        // same values -> returns true
        QueryStudentDescriptor descriptorOneCopy =
                new QueryStudentDescriptor(nameOne, sidOne, cidOne, emailOne);
        assertEquals(descriptorOne, descriptorOneCopy);

        // different types -> returns false
        assertNotEquals(1, descriptorOne);

        // null -> returns false
        assertNotEquals(null, descriptorOne);

        // different student -> returns false
        assertNotEquals(descriptorOne, descriptorTwo);
    }
}
