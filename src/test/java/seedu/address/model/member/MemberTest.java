package seedu.address.model.member;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMembers.ALICE;
import static seedu.address.testutil.TypicalMembers.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MemberBuilder;

public class MemberTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
<<<<<<< HEAD:src/test/java/seedu/address/model/person/PersonTest.java
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getPositions().remove(0));
=======
        Member member = new MemberBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> member.getTags().remove(0));
>>>>>>> master:src/test/java/seedu/address/model/member/MemberTest.java
    }

    @Test
    public void isSameMember() {
        // same object -> returns true
        assertTrue(ALICE.isSameMember(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameMember(null));

        // same name, all other attributes different -> returns true
<<<<<<< HEAD:src/test/java/seedu/address/model/person/PersonTest.java
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withPositions(VALID_POSITION_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));
=======
        Member editedAlice = new MemberBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameMember(editedAlice));
>>>>>>> master:src/test/java/seedu/address/model/member/MemberTest.java

        // different name, all other attributes same -> returns false
        editedAlice = new MemberBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameMember(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Member editedBob = new MemberBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameMember(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new MemberBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameMember(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Member aliceCopy = new MemberBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different member -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Member editedAlice = new MemberBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new MemberBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new MemberBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new MemberBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

<<<<<<< HEAD:src/test/java/seedu/address/model/person/PersonTest.java
        // different positions -> returns false
        editedAlice = new PersonBuilder(ALICE).withPositions(VALID_POSITION_HUSBAND).build();
=======
        // different tags -> returns false
        editedAlice = new MemberBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
>>>>>>> master:src/test/java/seedu/address/model/member/MemberTest.java
        assertFalse(ALICE.equals(editedAlice));
    }
}
