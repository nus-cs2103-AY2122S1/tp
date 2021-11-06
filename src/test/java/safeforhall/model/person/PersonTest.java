package safeforhall.model.person;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static safeforhall.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static safeforhall.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static safeforhall.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static safeforhall.testutil.TypicalPersons.ALICE;
import static safeforhall.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import safeforhall.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSamePerson(editedBob));

        // different name, all other attributes same (including room) -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, different room all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).withRoom("E101").build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).withRoom("E101").build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void checkHashCode() {
        try {
            new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build().hashCode();
        } catch (NoSuchMethodError e) {
            fail();
        }
    }

    @Test
    public void testHasMissedDeadline() {
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertTrue(editedAlice.hasMissedDeadline());
    }

    @Test
    public void getMissedDates() {
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        Person finalEditedAlice = editedAlice;
        assertDoesNotThrow(finalEditedAlice::getMissedDates);

        // Has not missed returns -1
        editedAlice = new PersonBuilder(ALICE).withFet("01-01-2100").build();
        assertEquals(-1, editedAlice.getMissedDates());
    }
}
