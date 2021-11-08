package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMembers.ALICE;
import static seedu.address.testutil.TypicalMembers.getTypicalEzFoodie;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.member.Member;
import seedu.address.model.member.exceptions.DuplicateMemberException;
import seedu.address.testutil.MemberBuilder;

/**
 * Tests the functionalities of
 * {@code ezFoodie}.
 */
public class EzFoodieTest {

    private final EzFoodie ezFoodie = new EzFoodie();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), ezFoodie.getMemberList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ezFoodie.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyEzFoodie_replacesData() {
        EzFoodie newData = getTypicalEzFoodie();
        ezFoodie.resetData(newData);
        assertEquals(newData, ezFoodie);
    }

    @Test
    public void resetData_withDuplicateMembers_throwsDuplicateMemberException() {
        // Two members with the same identity fields
        Member editedAlice = new MemberBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Member> newMembers = Arrays.asList(ALICE, editedAlice);
        EzFoodieStub newData = new EzFoodieStub(newMembers);

        assertThrows(DuplicateMemberException.class, () -> ezFoodie.resetData(newData));
    }

    @Test
    public void hasMember_nullMember_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ezFoodie.hasMember(null));
    }

    @Test
    public void hasMember_memberNotInEzFoodie_returnsFalse() {
        assertFalse(ezFoodie.hasMember(ALICE));
    }

    @Test
    public void hasMember_memberInEzFoodie_returnsTrue() {
        ezFoodie.addMember(ALICE);
        assertTrue(ezFoodie.hasMember(ALICE));
    }

    @Test
    public void hasMember_memberWithSameIdentityFieldsInEzFoodie_returnsTrue() {
        ezFoodie.addMember(ALICE);
        Member editedAlice = new MemberBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(ezFoodie.hasMember(editedAlice));
    }

    @Test
    public void getMemberList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> ezFoodie.getMemberList().remove(0));
    }

    /**
     * A stub ReadOnlyEzFoodie whose members list can violate interface constraints.
     */
    private static class EzFoodieStub implements ReadOnlyEzFoodie {
        private final ObservableList<Member> members = FXCollections.observableArrayList();

        EzFoodieStub(Collection<Member> members) {
            this.members.setAll(members);
        }

        @Override
        public ObservableList<Member> getMemberList() {
            return members;
        }
    }

}
