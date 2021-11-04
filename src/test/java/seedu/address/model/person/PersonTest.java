package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEVEL_OF_EDUCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTES_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.done.Done;
import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same email and same contact number, all other attributes different -> returns true
        Person editedBob = new PersonBuilder(BOB).withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).build();
        assertTrue(AMY.isSamePerson(editedBob));

        // same email but different contact number, all other attributes same -> returns true
        editedBob = new PersonBuilder(BOB).withPhone(VALID_PHONE_AMY).build();
        assertTrue(BOB.isSamePerson(editedBob));

        // different email but same contact number, all other attributes same -> returns true
        editedBob = new PersonBuilder(BOB).withEmail(VALID_EMAIL_AMY).build();
        assertTrue(BOB.isSamePerson(editedBob));

        // different email and contact number, all other attributes same -> returns false
        editedBob = new PersonBuilder(BOB).withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // checks case sensitiveness of email
        // email same but differing in case, different phone number, all other attributes same -> returns false
        editedBob = new PersonBuilder(BOB).withEmail(VALID_EMAIL_BOB.toUpperCase()).withPhone(VALID_PHONE_AMY).build();
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

        // different level of education -> returns false
        editedAlice = new PersonBuilder(ALICE).withLevelOfEducation(VALID_LEVEL_OF_EDUCATION_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));

        // different notes -> returns false
        editedAlice = new PersonBuilder(ALICE).withNotes(VALID_NOTES_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different done status -> return false
        Person aliceWithDone = new PersonBuilder(ALICE).withDone(Done.STATUS_DONE).build();
        Person aliceWithNotDone = new PersonBuilder(ALICE).withDone(Done.STATUS_UNDONE).build();
        assertFalse(aliceWithDone.equals(aliceWithNotDone));
    }
}
