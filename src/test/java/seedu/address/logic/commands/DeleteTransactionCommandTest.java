package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEMBER;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TRANSACTION;
import static seedu.address.testutil.TypicalMembers.getTypicalEzFoodie;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.Account;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.member.Credit;
import seedu.address.model.member.Member;
import seedu.address.model.member.Point;
import seedu.address.model.transaction.Transaction;
import seedu.address.testutil.MemberBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteTransactionCommandTest {

    private Model model = new ModelManager(new Account(), getTypicalEzFoodie(), new UserPrefs());

    @Test
    public void execute_validMemberIdTransactionIdUnfilteredList_success() {
        Member memberToEdit = model.getUpdatedMemberList().get(INDEX_FIRST_MEMBER.getZeroBased());
        Transaction transactionToDelete = memberToEdit.getTransactions().get(INDEX_FIRST_TRANSACTION.getZeroBased());
        DeleteTransactionCommand deleteCommand =
                new DeleteTransactionCommand(memberToEdit.getId(), transactionToDelete.getId());

        ModelManager expectedModel = new ModelManager(new Account(), model.getEzFoodie(), new UserPrefs());
        List<Transaction> expectedTransactions = memberToEdit.getTransactions().stream()
                .filter(transaction -> !transaction.equals(transactionToDelete)).collect(Collectors.toList());
        Credit expectedCredit = new Credit("" + Math.min(expectedTransactions.stream()
                .mapToInt(t -> (int) t.getBilling().getDoubleValue()).sum(), Credit.MAX));
        Point expectedPoint = memberToEdit.getPoint();
        Member expectedMember = new MemberBuilder(memberToEdit).withCredit(expectedCredit.value)
                .withPoint(expectedPoint.value).withTransactions(expectedTransactions.toArray(Transaction[]::new))
                .build();
        expectedModel.setMember(memberToEdit, expectedMember);
        String expectedMessage = String.format(DeleteTransactionCommand.MESSAGE_SUCCESS, "Id: " + expectedMember.getId()
                + "; Name: " + expectedMember.getName()
                + "; Transaction: " + "[" + transactionToDelete + "]");

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }
}
