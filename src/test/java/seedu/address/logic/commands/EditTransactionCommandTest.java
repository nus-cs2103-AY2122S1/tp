package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEMBER;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TRANSACTION;
import static seedu.address.testutil.TypicalMembers.getTypicalEzFoodie;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditTransactionCommand.EditTransactionDescriptor;
import seedu.address.model.Account;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.member.Credit;
import seedu.address.model.member.Member;
import seedu.address.model.transaction.Transaction;
import seedu.address.testutil.EditTransactionDescriptorBuilder;
import seedu.address.testutil.MemberBuilder;
import seedu.address.testutil.TransactionBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code EditTransactionCommand}.
 */
public class EditTransactionCommandTest {

    private Model model = new ModelManager(new Account(), getTypicalEzFoodie(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Member memberToEdit = model.getUpdatedMemberList().get(INDEX_FIRST_MEMBER.getZeroBased());
        Transaction transactionToEdit = memberToEdit.getTransactions().get(INDEX_FIRST_TRANSACTION.getZeroBased());
        Transaction editedTransaction = new TransactionBuilder().withId(transactionToEdit.getId().value)
                .withTimestamp(transactionToEdit.getTimestamp().value)
                .withBilling(transactionToEdit.getBilling().value).build();

        EditTransactionDescriptor descriptor = new EditTransactionDescriptorBuilder(editedTransaction).build();
        EditTransactionCommand editCommand =
                new EditTransactionCommand(memberToEdit.getId(), transactionToEdit.getId(), descriptor);

        ModelManager expectedModel = new ModelManager(new Account(), model.getEzFoodie(), new UserPrefs());
        List<Transaction> expectedTransactions = new ArrayList<>(memberToEdit.getTransactions());
        expectedTransactions.stream().filter(transaction -> transaction.isSameId(transactionToEdit)).findAny()
                .ifPresent(transaction -> expectedTransactions
                        .set(expectedTransactions.indexOf(transaction), editedTransaction));
        Credit expectedCredit = new Credit("" + Math.min(expectedTransactions.stream()
                .mapToInt(t -> (int) t.getBilling().getDoubleValue()).sum(), Credit.MAX));
        Member expectedMember = new MemberBuilder(memberToEdit).withCredit(expectedCredit.value)
                .withTransactions(expectedTransactions.toArray(Transaction[]::new)).build();
        expectedModel.setMember(memberToEdit, expectedMember);
        Transaction updatedTransaction = expectedMember.getTransactions().stream()
                .filter(transaction -> transaction.isSameId(transactionToEdit)).findAny().orElse(null);
        String expectedMessage = String.format(EditTransactionCommand.MESSAGE_SUCCESS, "Id: " + expectedMember.getId()
                + "; Name: " + expectedMember.getName()
                + "; Transaction: " + "[" + updatedTransaction + "]");

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }
}
