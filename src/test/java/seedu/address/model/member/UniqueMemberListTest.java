package seedu.address.model.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMembers.ALICE;
import static seedu.address.testutil.TypicalMembers.ALICE_DIFFERENT_NAME;
import static seedu.address.testutil.TypicalMembers.ALICE_DIFFERENT_PHONE;
import static seedu.address.testutil.TypicalMembers.AMY;
import static seedu.address.testutil.TypicalMembers.BOB;
import static seedu.address.testutil.TypicalMembers.CARL;
import static seedu.address.testutil.TypicalMembers.CHOO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.member.exceptions.DuplicateMemberException;
import seedu.address.model.member.exceptions.MemberNotFoundException;
import seedu.address.testutil.MemberBuilder;

public class UniqueMemberListTest {

    private final UniqueMemberList uniqueMemberList = new UniqueMemberList();

    @Test
    public void contains_nullMember_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMemberList.contains(null));
    }

    @Test
    public void contains_memberNotInList_returnsFalse() {
        assertFalse(uniqueMemberList.contains(ALICE));
    }

    @Test
    public void contains_memberInList_returnsTrue() {
        uniqueMemberList.add(ALICE);
        assertTrue(uniqueMemberList.contains(ALICE));
    }

    @Test
    public void contains_memberWithSameIdentityFieldsInList_returnsTrue() {
        uniqueMemberList.add(ALICE);
        Member editedAlice = new MemberBuilder(ALICE).build();
        assertTrue(uniqueMemberList.contains(editedAlice));
    }

    @Test
    public void add_nullMember_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMemberList.add(null));
    }

    @Test
    public void add_duplicateMember_throwsDuplicateMemberException() {
        uniqueMemberList.add(ALICE);
        assertThrows(DuplicateMemberException.class, () -> uniqueMemberList.add(ALICE));
    }

    @Test
    public void setMember_nullTargetMember_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMemberList.setMember(null, ALICE));
    }

    @Test
    public void setMember_nullEditedMember_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMemberList.setMember(ALICE, null));
    }

    @Test
    public void setMember_targetMemberNotInList_throwsMemberNotFoundException() {
        assertThrows(MemberNotFoundException.class, () -> uniqueMemberList.setMember(ALICE, ALICE));
    }

    @Test
    public void setMember_editedMemberIsSameMember_success() {
        uniqueMemberList.add(ALICE);
        uniqueMemberList.setMember(ALICE, ALICE);
        UniqueMemberList expectedUniqueMemberList = new UniqueMemberList();
        expectedUniqueMemberList.add(ALICE);
        assertEquals(expectedUniqueMemberList, uniqueMemberList);
    }

    @Test
    public void setMember_editedMemberHasSameIdentity_success() {
        uniqueMemberList.add(ALICE);
        Member editedAlice = new MemberBuilder(ALICE).build();
        uniqueMemberList.setMember(ALICE, editedAlice);
        UniqueMemberList expectedUniqueMemberList = new UniqueMemberList();
        expectedUniqueMemberList.add(editedAlice);
        assertEquals(expectedUniqueMemberList, uniqueMemberList);
    }

    @Test
    public void setMember_editedMemberHasDifferentIdentity_success() {
        uniqueMemberList.add(ALICE);
        uniqueMemberList.setMember(ALICE, BOB);
        UniqueMemberList expectedUniqueMemberList = new UniqueMemberList();
        expectedUniqueMemberList.add(BOB);
        assertEquals(expectedUniqueMemberList, uniqueMemberList);
    }

    @Test
    public void setMember_editedMemberHasNonUniqueIdentity_throwsDuplicateMemberException() {
        uniqueMemberList.add(ALICE);
        uniqueMemberList.add(BOB);
        assertThrows(DuplicateMemberException.class, () -> uniqueMemberList.setMember(ALICE, BOB));
    }

    @Test
    public void getSameMember_memberWithSameNameInSportsPa_returnsMemberWithSameName() {
        uniqueMemberList.add(ALICE);
        assertEquals(uniqueMemberList.getSameMember(ALICE_DIFFERENT_PHONE), ALICE);
    }

    @Test
    public void getSameMember_memberWithSamePhoneInSportsPa_returnsMemberWithSameName() {
        uniqueMemberList.add(ALICE);
        assertEquals(uniqueMemberList.getSameMember(ALICE_DIFFERENT_NAME), ALICE);
    }

    @Test
    public void getSameMember_memberWithSameNameNotInSportsPa_returnsNull() {
        assertNull(uniqueMemberList.getSameMember(BOB));
    }

    @Test
    public void isValidImport_invalidImport_returnsFalse() {
        uniqueMemberList.add(AMY);
        uniqueMemberList.add(BOB);
        Member toTest = new MemberBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(uniqueMemberList.isValidImport(toTest));
    }

    @Test
    public void isValidImport_validImport_returnsTrue() {
        uniqueMemberList.add(BOB);
        uniqueMemberList.add(CARL);
        Member firstToTest = new MemberBuilder().withName(VALID_NAME_AMY).build();
        Member secondToTest = new MemberBuilder().withPhone(VALID_PHONE_BOB).build();
        Member thirdToTest = new MemberBuilder().build();

        assertTrue(uniqueMemberList.isValidImport(firstToTest));
        assertTrue(uniqueMemberList.isValidImport(secondToTest));
        assertTrue(uniqueMemberList.isValidImport(thirdToTest));
    }

    @Test
    public void remove_nullMember_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMemberList.remove(null));
    }

    @Test
    public void remove_memberDoesNotExist_throwsMemberNotFoundException() {
        assertThrows(MemberNotFoundException.class, () -> uniqueMemberList.remove(ALICE));
    }

    @Test
    public void remove_existingMember_removesMember() {
        uniqueMemberList.add(ALICE);
        uniqueMemberList.remove(ALICE);
        UniqueMemberList expectedUniqueMemberList = new UniqueMemberList();
        assertEquals(expectedUniqueMemberList, uniqueMemberList);
    }

    @Test
    public void setMember_nullUniqueMemberList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMemberList.setMembers((UniqueMemberList) null));
    }

    @Test
    public void setMembers_uniqueMemberList_replacesOwnListWithProvidedUniqueMemberList() {
        uniqueMemberList.add(ALICE);
        UniqueMemberList expectedUniqueMemberList = new UniqueMemberList();
        expectedUniqueMemberList.add(BOB);
        uniqueMemberList.setMembers(expectedUniqueMemberList);
        assertEquals(expectedUniqueMemberList, uniqueMemberList);
    }

    @Test
    public void setMembers_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMemberList.setMembers((List<Member>) null));
    }

    @Test
    public void setMembers_list_replacesOwnListWithProvidedList() {
        uniqueMemberList.add(ALICE);
        List<Member> memberList = Collections.singletonList(BOB);
        uniqueMemberList.setMembers(memberList);
        UniqueMemberList expectedUniqueMemberList = new UniqueMemberList();
        expectedUniqueMemberList.add(BOB);
        assertEquals(expectedUniqueMemberList, uniqueMemberList);
    }

    @Test
    public void setMembers_listWithDuplicateMembers_throwsDuplicateMemberException() {
        List<Member> listWithDuplicateMembers = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateMemberException.class, () -> uniqueMemberList.setMembers(listWithDuplicateMembers));
    }

    @Test
    public void sortMemberByName_sortedCorrectly() {
        uniqueMemberList.add(BOB);
        uniqueMemberList.add(ALICE);
        uniqueMemberList.sortMembersByName();
        UniqueMemberList expectedUniqueMemberList = new UniqueMemberList();
        expectedUniqueMemberList.add(ALICE);
        expectedUniqueMemberList.add(BOB);
        assertEquals(expectedUniqueMemberList, uniqueMemberList);
    }

    @Test
    public void sortMembersByTag_sortedCorrectly() {
        uniqueMemberList.add(AMY);
        uniqueMemberList.add(BOB);
        uniqueMemberList.add(CHOO);
        uniqueMemberList.sortMembersByTags();
        UniqueMemberList expectedUniqueMemberList = new UniqueMemberList();
        expectedUniqueMemberList.add(BOB);
        expectedUniqueMemberList.add(AMY);
        expectedUniqueMemberList.add(CHOO);
        assertEquals(expectedUniqueMemberList, uniqueMemberList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueMemberList.asUnmodifiableObservableList().remove(0));
    }
}
