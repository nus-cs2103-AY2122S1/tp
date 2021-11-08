package seedu.address.model.module.member;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMembers.ALICE;
import static seedu.address.testutil.TypicalMembers.BOB;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.testutil.MemberBuilder;
import seedu.address.testutil.TypicalTasks;

public class MemberTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Member member = new MemberBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> member.getPositions().remove(0));
    }

    @Test
    public void isSameType() {
        // same object -> returns true
        Assertions.assertTrue(ALICE.isSameType(ALICE));

        // null -> returns false
        Assertions.assertFalse(ALICE.isSameType(null));

        // same name, all other attributes different -> returns true
        Member editedAlice = new MemberBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withPositions(VALID_POSITION_HUSBAND).build();
        Assertions.assertTrue(ALICE.isSameType(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new MemberBuilder(ALICE).withName(VALID_NAME_BOB).build();
        Assertions.assertFalse(ALICE.isSameType(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Member editedBob = new MemberBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        Assertions.assertTrue(BOB.isSameType(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new MemberBuilder(BOB).withName(nameWithTrailingSpaces).build();
        Assertions.assertFalse(BOB.isSameType(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Member aliceCopy = new MemberBuilder(ALICE).build();
        Assertions.assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        Assertions.assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        Assertions.assertFalse(ALICE.equals(null));

        // different type -> returns false
        Assertions.assertFalse(ALICE.equals(5));

        // different member -> returns false
        Assertions.assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Member editedAlice = new MemberBuilder(ALICE).withName(VALID_NAME_BOB).build();
        Assertions.assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new MemberBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        Assertions.assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new MemberBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        Assertions.assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new MemberBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        Assertions.assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new MemberBuilder(ALICE).withPositions(VALID_POSITION_HUSBAND).build();
        Assertions.assertFalse(ALICE.equals(editedAlice));

        // different task list -> return true (do not count tasks as part of member)
        editedAlice = new MemberBuilder(ALICE).withTaskList(TypicalTasks.getTypicalTasksDone()).build();
        Assertions.assertTrue(ALICE.equals(editedAlice));
    }
}
