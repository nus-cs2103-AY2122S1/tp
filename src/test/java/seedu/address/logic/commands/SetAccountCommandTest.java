package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEMBER;
import static seedu.address.testutil.TypicalMembers.getTypicalEzFoodie;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SetAccountCommand.EditAccountDescriptor;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Account;
import seedu.address.model.EzFoodie;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.member.Member;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.transaction.Transaction;
import seedu.address.testutil.EditMemberDescriptorBuilder;
import seedu.address.testutil.MemberBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code SummaryCommand}.
 */
public class SetAccountCommandTest {
    private Model model = new ModelManager(new Account(), getTypicalEzFoodie(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Member memberToEdit = model.getUpdatedMemberList().get(INDEX_FIRST_MEMBER.getZeroBased());
        Member editedMember = new MemberBuilder().withId(memberToEdit.getId().value)
                .withTimestamp(memberToEdit.getTimestamp().value)
                .withCredit(memberToEdit.getCredit().value)
                .withPoint(memberToEdit.getPoint().value)
                .withTransactions(memberToEdit.getTransactions().toArray(Transaction[]::new))
                .withReservations(memberToEdit.getReservations().toArray(Reservation[]::new)).build();
        EditMemberCommand.EditMemberDescriptor descriptor = new EditMemberDescriptorBuilder(editedMember).build();
        EditMemberCommand editCommand = new EditMemberCommand(INDEX_FIRST_MEMBER, descriptor);

        String expectedMessage = String.format(EditMemberCommand.MESSAGE_SUCCESS, editedMember);

        Model expectedModel = new ModelManager(new Account(), new EzFoodie(model.getEzFoodie()), new UserPrefs());
        expectedModel.setMember(memberToEdit, editedMember);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() throws ParseException {

        EditAccountDescriptor firstEditAccountDescriptor = new EditAccountDescriptor();
        firstEditAccountDescriptor.setPassword(ParserUtil.parsePassword("123456"));
        EditAccountDescriptor secondAccountDescriptor = new EditAccountDescriptor();
        secondAccountDescriptor.setPassword(ParserUtil.parsePassword("654321"));

        SetAccountCommand setAccountFirstCommand = new SetAccountCommand(firstEditAccountDescriptor);
        SetAccountCommand setAccountSecondCommand = new SetAccountCommand(secondAccountDescriptor);

        // same object -> returns true
        assertTrue(setAccountFirstCommand.equals(setAccountFirstCommand));

        // same values -> returns true
        EditAccountDescriptor firstEditAccountDescriptorCopy = new EditAccountDescriptor();
        firstEditAccountDescriptorCopy.setPassword(ParserUtil.parsePassword("123456"));
        SetAccountCommand setAccountFirstCommandCopy = new SetAccountCommand(firstEditAccountDescriptorCopy);
        assertTrue(setAccountFirstCommand.equals(setAccountFirstCommandCopy));

        // different types -> returns false
        assertFalse(setAccountFirstCommand.equals(1));

        // null -> returns false
        assertFalse(setAccountFirstCommand.equals(null));

        // different member -> returns false
        assertFalse(setAccountFirstCommand.equals(setAccountSecondCommand));
    }
}
