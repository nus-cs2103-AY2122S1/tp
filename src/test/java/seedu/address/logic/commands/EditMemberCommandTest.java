package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CREDIT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMemberAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEMBER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEMBER;
import static seedu.address.testutil.TypicalMembers.getTypicalEzFoodie;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditMemberCommand.EditMemberDescriptor;
import seedu.address.model.Account;
import seedu.address.model.EzFoodie;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.member.Member;
import seedu.address.model.transaction.Transaction;
import seedu.address.testutil.EditMemberDescriptorBuilder;
import seedu.address.testutil.MemberBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code EditMemberCommand}.
 */
public class EditMemberCommandTest {

    private Model model = new ModelManager(new Account(), getTypicalEzFoodie(), new UserPrefs());

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastMember = Index.fromOneBased(model.getUpdatedMemberList().size());
        Member lastMember = model.getUpdatedMemberList().get(indexLastMember.getZeroBased());

        MemberBuilder memberInList = new MemberBuilder(lastMember);
        Member editedMember = memberInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withCredit(VALID_CREDIT_BOB).withTags(VALID_TAG_HUSBAND)
                .withTransactions(lastMember.getTransactions().toArray(Transaction[]::new))
                .withCredit(lastMember.getCredit().value).build();

        EditMemberDescriptor descriptor = new EditMemberDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditMemberCommand editCommand = new EditMemberCommand(indexLastMember, descriptor);

        String expectedMessage = String.format(EditMemberCommand.MESSAGE_SUCCESS, editedMember);

        Model expectedModel = new ModelManager(new Account(), new EzFoodie(model.getEzFoodie()), new UserPrefs());
        expectedModel.setMember(lastMember, editedMember);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditMemberCommand editCommand = new EditMemberCommand(INDEX_FIRST_MEMBER, new EditMemberDescriptor());
        Member editedMember = model.getUpdatedMemberList().get(INDEX_FIRST_MEMBER.getZeroBased());

        String expectedMessage = String.format(EditMemberCommand.MESSAGE_SUCCESS, editedMember);

        Model expectedModel = new ModelManager(new Account(), new EzFoodie(model.getEzFoodie()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showMemberAtIndex(model, INDEX_FIRST_MEMBER);

        Member memberInFilteredList = model.getUpdatedMemberList().get(INDEX_FIRST_MEMBER.getZeroBased());
        Member editedMember = new MemberBuilder(memberInFilteredList).withName(VALID_NAME_BOB).build();
        EditMemberCommand editCommand = new EditMemberCommand(INDEX_FIRST_MEMBER,
                new EditMemberDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditMemberCommand.MESSAGE_SUCCESS, editedMember);

        Model expectedModel = new ModelManager(new Account(), new EzFoodie(model.getEzFoodie()), new UserPrefs());
        expectedModel.setMember(model.getUpdatedMemberList().get(INDEX_FIRST_MEMBER.getZeroBased()), editedMember);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateMemberUnfilteredList_failure() {
        Member firstMember = model.getUpdatedMemberList().get(INDEX_FIRST_MEMBER.getZeroBased());
        EditMemberDescriptor descriptor = new EditMemberDescriptorBuilder(firstMember).build();
        EditMemberCommand editCommand = new EditMemberCommand(INDEX_SECOND_MEMBER, descriptor);

        assertCommandFailure(editCommand, model, EditMemberCommand.MESSAGE_DUPLICATE_MEMBER);
    }

    @Test
    public void execute_duplicateMemberFilteredList_failure() {
        showMemberAtIndex(model, INDEX_FIRST_MEMBER);

        // edit member in filtered list into a duplicate in ezFoodie
        Member memberInList = model.getEzFoodie().getMemberList().get(INDEX_SECOND_MEMBER.getZeroBased());
        EditMemberCommand editCommand = new EditMemberCommand(INDEX_FIRST_MEMBER,
                new EditMemberDescriptorBuilder(memberInList).build());

        assertCommandFailure(editCommand, model, EditMemberCommand.MESSAGE_DUPLICATE_MEMBER);
    }

    @Test
    public void execute_invalidMemberIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getUpdatedMemberList().size() + 1);
        EditMemberDescriptor descriptor = new EditMemberDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditMemberCommand editCommand = new EditMemberCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of ezFoodie
     */
    @Test
    public void execute_invalidMemberIndexFilteredList_failure() {
        showMemberAtIndex(model, INDEX_FIRST_MEMBER);
        Index outOfBoundIndex = INDEX_SECOND_MEMBER;
        // ensures that outOfBoundIndex is still in bounds of ezFoodie list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getEzFoodie().getMemberList().size());

        EditMemberCommand editCommand = new EditMemberCommand(outOfBoundIndex,
                new EditMemberDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditMemberCommand standardCommand = new EditMemberCommand(INDEX_FIRST_MEMBER, DESC_AMY);

        // same values -> returns true
        EditMemberDescriptor copyDescriptor = new EditMemberDescriptor(DESC_AMY);
        EditMemberCommand commandWithSameValues = new EditMemberCommand(INDEX_FIRST_MEMBER, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditMemberCommand(INDEX_SECOND_MEMBER, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditMemberCommand(INDEX_FIRST_MEMBER, DESC_BOB)));
    }

}
