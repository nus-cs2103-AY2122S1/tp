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
                new QueryStudentDescriptor(null, null, null);
        assertFalse(descriptor.isAnyFieldToBeQueried());
    }

    @Test
    void isAnyFieldToBeQueried_notAllNull_returnTrue() {
        // name not null
        QueryStudentDescriptor descriptor =
                new QueryStudentDescriptor("Alice", null, null);
        assertTrue(descriptor.isAnyFieldToBeQueried());

        // student ID not null
        descriptor =
                new QueryStudentDescriptor(null, "A1234567X", null);
        assertTrue(descriptor.isAnyFieldToBeQueried());

        // name not null
        descriptor =
                new QueryStudentDescriptor(null, null, "B01");
        assertTrue(descriptor.isAnyFieldToBeQueried());
    }

    @Test
    void isAnyFieldToBeQueried_noNull_returnTrue() {
        QueryStudentDescriptor descriptor =
                new QueryStudentDescriptor("Alice", "A1234567X", "B01");
        assertTrue(descriptor.isAnyFieldToBeQueried());
    }

    @Test
    void doesStudentMatchDescriptor_detailsMatch_returnsTrue() {
        String nameAlice = "Alice Pauline";
        String sidAlice = "A0212425H";
        String cidAlice = "B01";

        // match name only
        QueryStudentDescriptor descriptorAliceOne =
                new QueryStudentDescriptor(nameAlice, null, null);
        assertTrue(descriptorAliceOne.doesStudentMatchDescriptor(ALICE));

        // match all three fields
        QueryStudentDescriptor descriptorAliceAll =
                new QueryStudentDescriptor(nameAlice, sidAlice, cidAlice);
        assertTrue(descriptorAliceOne.doesStudentMatchDescriptor(ALICE));
    }

    @Test
    void doesStudentMatchDescriptor_detailsDoesNotMatch_returnsFalse() {
        String nameFake = "Fake Name";
        String sidFake = "A1234567X";
        String cidFake = "B11";

        // match name only
        QueryStudentDescriptor descriptorFakeOne =
                new QueryStudentDescriptor(nameFake, null, null);
        assertFalse(descriptorFakeOne.doesStudentMatchDescriptor(ALICE));

        // match all three fields
        QueryStudentDescriptor descriptorFakeAll =
                new QueryStudentDescriptor(nameFake, sidFake, cidFake);
        assertFalse(descriptorFakeAll.doesStudentMatchDescriptor(ALICE));
    }

    @Test
    void equals() {
        String nameOne = "nameOne";
        String sidOne = "sidOne";
        String cidOne = "cidOne";

        String nameTwo = "nameTwo";
        String sidTwo = "sidTwo";
        String cidTwo = "cidTwo";
        QueryStudentDescriptor descriptorOne =
                new QueryStudentDescriptor(nameOne, sidOne, cidOne);
        QueryStudentDescriptor descriptorTwo =
                new QueryStudentDescriptor(nameTwo, sidTwo, cidTwo);

        // same object -> returns true
        assertEquals(descriptorOne, descriptorOne);

        // same values -> returns true
        QueryStudentDescriptor descriptorOneCopy =
                new QueryStudentDescriptor(nameOne, sidOne, cidOne);
        assertEquals(descriptorOne, descriptorOneCopy);

        // different types -> returns false
        assertNotEquals(1, descriptorOne);

        // null -> returns false
        assertNotEquals(null, descriptorOne);

        // different student -> returns false
        assertNotEquals(descriptorOne, descriptorTwo);
    }
}
