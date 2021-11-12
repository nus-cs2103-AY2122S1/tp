package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.interests.InterestsList;
import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void constructor_anyNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Person(
                new Name(VALID_NAME_BOB), new Phone(VALID_PHONE_BOB), new Email(VALID_EMAIL_BOB),
                new IsCalled("true"), new Address("VALID ADDRESS"), new Gender("F"), new Age("22"),
                null
        ));

        assertThrows(NullPointerException.class, () -> new Person(
                new Name(VALID_NAME_BOB), new Phone(VALID_PHONE_BOB), new Email(VALID_EMAIL_BOB),
                new IsCalled("true"), new Address("VALID ADDRESS"), new Gender("F"), null,
                new InterestsList()
        ));

        assertThrows(NullPointerException.class, () -> new Person(
                new Name(VALID_NAME_BOB), new Phone(VALID_PHONE_BOB), new Email(VALID_EMAIL_BOB),
                new IsCalled("true"), new Address("VALID ADDRESS"), null, new Age("22"),
                new InterestsList()
        ));

        assertThrows(NullPointerException.class, () -> new Person(
                new Name(VALID_NAME_BOB), new Phone(VALID_PHONE_BOB), new Email(VALID_EMAIL_BOB),
                new IsCalled("true"), null, new Gender("F"), new Age("22"),
                new InterestsList()
        ));

        assertThrows(NullPointerException.class, () -> new Person(
                new Name(VALID_NAME_BOB), new Phone(VALID_PHONE_BOB), new Email(VALID_EMAIL_BOB),
                null, new Address("VALID ADDRESS"), new Gender("F"), new Age("22"),
                new InterestsList()
        ));

        assertThrows(NullPointerException.class, () -> new Person(
                new Name(VALID_NAME_BOB), new Phone(VALID_PHONE_BOB), null,
                new IsCalled("true"), new Address("VALID ADDRESS"), new Gender("F"), new Age("22"),
                new InterestsList()
        ));

        assertThrows(NullPointerException.class, () -> new Person(
                new Name(VALID_NAME_BOB), null, new Email(VALID_EMAIL_BOB),
                new IsCalled("true"), new Address("VALID ADDRESS"), new Gender("F"), new Age("22"),
                new InterestsList()
        ));

        assertThrows(NullPointerException.class, () -> new Person(
                null, new Phone(VALID_PHONE_BOB), new Email(VALID_EMAIL_BOB),
                new IsCalled("true"), new Address("VALID ADDRESS"), new Gender("F"), new Age("22"),
                new InterestsList()
        ));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
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
    public void toStringNoHeaders() {
        Person test = new PersonBuilder().build();
        String expectedString = new StringBuilder().append("Amy Bee;").append("85355255;")
                .append("amy@gmail.com;").append("False;").toString();
        assertEquals(test.toStringNoHeaders(), expectedString);
    }
}
