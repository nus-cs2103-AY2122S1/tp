package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HEALTH_CONDITION_DEMENTIA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LANGUAGE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getHealthConditions().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withLanguage(VALID_LANGUAGE_BOB)
                .withAddress(VALID_ADDRESS_BOB).withHealthConditions(VALID_HEALTH_CONDITION_DEMENTIA).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void hasVisit() {
        // Alice has a non-empty visit -> returns true
        assertTrue(ALICE.hasVisit());

        // new person has an empty visit -> returns false
        Person noVisitAlice = new PersonBuilder(ALICE).withVisit("").build();
        assertFalse(noVisitAlice.hasVisit());
    }

    @Test
    public void hasLastVisit() {
        // Alice has a non-empty visit -> returns true
        assertTrue(ALICE.hasLastVisit());

        // new person has an empty visit -> returns false
        Person noVisitAlice = new PersonBuilder(ALICE).withLastVisit("").build();
        assertFalse(noVisitAlice.hasLastVisit());
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

        // different language -> returns false
        editedAlice = new PersonBuilder(ALICE).withLanguage(VALID_LANGUAGE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withHealthConditions(VALID_HEALTH_CONDITION_DEMENTIA).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void getFormattedVisit() {
        // without frequency and occurrence
        String visit = ALICE.getVisit().get().getFormatted();
        assertEquals(ALICE.getFormattedVisit(), visit);

        // with frequency and occurrence = 1
        Person editedAlice = new PersonBuilder(ALICE).withFrequency(Frequency.DAILY.toString())
                                 .withOccurrence(1).build();
        assertEquals(editedAlice.getFormattedVisit(), visit);

        // with frequency and occurrence > 1
        editedAlice = new PersonBuilder(ALICE).withFrequency(Frequency.WEEKLY.toString()).withOccurrence(2).build();
        visit = visit + " (repeats " + Frequency.WEEKLY.toString() + ", for " + 1 + " more time(s))";
        assertEquals(editedAlice.getFormattedVisit(), visit);

        // without visit
        editedAlice = new PersonBuilder(ALICE).withVisit("").build();
        assertEquals(editedAlice.getFormattedVisit(), "-");
    }

    @Test
    public void isVisitOverdue() {
        // no existing visit
        Person editedAlice = new PersonBuilder(ALICE).withVisit("").build();
        assertFalse(editedAlice.isVisitOverdue());

        // overdue as of this module's time
        editedAlice = new PersonBuilder(ALICE).withVisit("2000-01-01 08:00").build();
        assertTrue(editedAlice.isVisitOverdue());

        // not overdue in around 100 years
        editedAlice = new PersonBuilder(ALICE).withVisit("2103-01-01 08:00").build();
        assertFalse(editedAlice.isVisitOverdue());
    }

    @Test
    public void hasVisitThisWeekOrMonth() {
        // both visits' time are very far away from current time, a sanity check
        Person editedAlicePast = new PersonBuilder(ALICE).withVisit("1900-01-01 10:00").build();
        Person editedAliceFuture = new PersonBuilder(ALICE).withVisit("2077-01-01 10:00").build();

        assertFalse(editedAlicePast.hasVisitThisMonth());
        assertFalse(editedAlicePast.hasVisitThisWeek());
        assertFalse(editedAliceFuture.hasVisitThisMonth());
        assertFalse(editedAliceFuture.hasVisitThisMonth());
    }
}
