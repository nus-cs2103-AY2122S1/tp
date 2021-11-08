package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NUS_NETWORK_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;
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

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE)
                .withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .withGitHubId(VALID_GITHUB_ID_BOB)
                .withNusNetworkId(VALID_NUS_NETWORK_ID_BOB)
                .withStudentId(VALID_STUDENT_ID_BOB)
                .withTutorialId(VALID_TUTORIAL_ID_BOB)
                .withType(VALID_TYPE_BOB)
                .build();

        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE)
                .withName(VALID_NAME_BOB)
                .build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB)
                .withName(VALID_NAME_BOB.toLowerCase())
                .build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB)
                .withName(nameWithTrailingSpaces)
                .build();
        assertFalse(BOB.isSamePerson(editedBob));

        // same person
        assertTrue(ALICE.isSamePerson(ALICE));
    }

    @Test
    public void compare() {

        //compare name
        assertTrue(ALICE.compare(BENSON, new Prefix("n/")) < 0);

        //compare phone
        assertTrue(ALICE.compare(BENSON, new Prefix("p/")) < 0);

        //compare email
        assertTrue(ALICE.compare(BENSON, new Prefix("e/")) < 0);

        //compare number of Tags
        assertTrue(ALICE.compare(BENSON, new Prefix("t/")) < 0);

        //compare address
        assertTrue(ALICE.compare(BENSON, new Prefix("a/")) < 0);

        //compare Student ID
        assertTrue(ALICE.compare(BENSON, new Prefix("s/")) < 0);

        //compare Github ID
        assertTrue(ALICE.compare(BENSON, new Prefix("g/")) < 0);

        //compare Tutorial ID
        assertTrue(ALICE.compare(BENSON, new Prefix("T/")) < 0);

        //compare type
        Person editedBenson = new PersonBuilder(BENSON).withType("tutor").build();
        assertTrue(ALICE.compare(editedBenson, new Prefix("r/")) < 0);

        //compare NUS Network ID
        assertTrue(ALICE.compare(BENSON, new Prefix("N/")) < 0);

        //compare if equal Tutorial ID
        editedBenson = new PersonBuilder(BENSON).withTutorialId("00").build();
        assertTrue(ALICE.compare(editedBenson, new Prefix("r/")) < 0);
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

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));

        // different gitHubId -> returns false
        editedAlice = new PersonBuilder(ALICE).withGitHubId(VALID_GITHUB_ID_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different nusNetworkId -> returns false
        editedAlice = new PersonBuilder(ALICE).withNusNetworkId(VALID_NUS_NETWORK_ID_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different type -> returns false
        editedAlice = new PersonBuilder(ALICE).withType(VALID_TYPE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different studentId -> returns false
        editedAlice = new PersonBuilder(ALICE).withStudentId(VALID_STUDENT_ID_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tutorialId -> returns false
        editedAlice = new PersonBuilder(ALICE).withTutorialId(VALID_TUTORIAL_ID_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void hashCodeTest() {
        assertEquals(ALICE.hashCode(), ALICE.hashCode());
        assertNotEquals(ALICE.hashCode(), BOB.hashCode());
    }
}
