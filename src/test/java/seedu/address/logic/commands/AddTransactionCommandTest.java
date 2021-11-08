package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEMBER;
import static seedu.address.testutil.TypicalMembers.getTypicalEzFoodie;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Account;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAccount;
import seedu.address.model.ReadOnlyEzFoodie;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.member.Id;
import seedu.address.model.member.Member;
import seedu.address.model.transaction.Transaction;
import seedu.address.testutil.MemberBuilder;
import seedu.address.testutil.TransactionBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AddTransactionCommand}.
 */
public class AddTransactionCommandTest {

    private Model model = new ModelManager(new Account(), getTypicalEzFoodie(), new UserPrefs());

    @Test
    public void constructor_nullTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTransactionCommand(null, null));
    }

    @Test
    public void execute_transactionAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTransactionAdded modelStub = new ModelStubAcceptingTransactionAdded(model);
        Member validMember = model.getUpdatedMemberList().get(INDEX_FIRST_MEMBER.getZeroBased());
        Id validId = validMember.getId();
        Transaction validTransaction = new TransactionBuilder().build();
        CommandResult commandResult = new AddTransactionCommand(validTransaction, validId).execute(modelStub);
        Member expectedMember = model.getUpdatedMemberList().get(INDEX_FIRST_MEMBER.getZeroBased());

        assertEquals(String.format(AddTransactionCommand.MESSAGE_SUCCESS, "Id: " + expectedMember.getId()
                + "; Name: " + expectedMember.getName()
                + "; Transaction: " + "[" + validTransaction + "]"),
                commandResult.getFeedbackToUser());
        assertEquals(validTransaction,
                expectedMember.getTransactions().get(expectedMember.getTransactions().size() - 1));
    }

    @Test
    public void equals() {
        Member alice = new MemberBuilder().withName("Alice").build();
        Member bob = new MemberBuilder().withName("Bob").build();
        Transaction aliceTransaction = new TransactionBuilder().withTimestamp("1612137600000").withBilling("123.45")
                .build();
        Transaction bobTransaction = new TransactionBuilder().withTimestamp("1612224000000").withBilling("543.21")
                .build();
        AddTransactionCommand addAliceTransactionCommand = new AddTransactionCommand(aliceTransaction, alice.getId());
        AddTransactionCommand addBobTransactionCommand = new AddTransactionCommand(bobTransaction, bob.getId());

        // same object -> returns true
        assertTrue(addAliceTransactionCommand.equals(addAliceTransactionCommand));

        // same values -> returns true
        AddTransactionCommand addAliceTransactionCommandCopy =
                new AddTransactionCommand(aliceTransaction, alice.getId());
        assertTrue(addAliceTransactionCommand.equals(addAliceTransactionCommandCopy));

        // different types -> returns false
        assertFalse(addAliceTransactionCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceTransactionCommand.equals(null));

        // different member -> returns false
        assertFalse(addAliceTransactionCommand.equals(addBobTransactionCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAccountFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAccountFilePath(Path accountFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getEzFoodieFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEzFoodieFilePath(Path ezFoodieFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAccount(ReadOnlyAccount account) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAccount getAccount() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMember(Member member) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEzFoodie(ReadOnlyEzFoodie newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyEzFoodie getEzFoodie() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasMember(Member member) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasMember(Member member, Predicate<Member> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteMember(Member target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMember(Member target, Member editedMember) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Member> getUpdatedMemberList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Member> getUpdatedMemberListForView() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredMemberList(Predicate<Member> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredMemberListForView(Predicate<Member> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedMemberList(Comparator<Member> comparator) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accept the transaction being added.
     */
    private class ModelStubAcceptingTransactionAdded extends ModelStub {

        final Model model;

        public ModelStubAcceptingTransactionAdded(Model model) {
            this.model = model;
        }

        @Override
        public void setMember(Member target, Member editedMember) {
            requireAllNonNull(target, editedMember);
            model.setMember(target, editedMember);
        }

        @Override
        public ObservableList<Member> getUpdatedMemberList() {
            return model.getUpdatedMemberList();
        }

        @Override
        public void updateFilteredMemberList(Predicate<Member> predicate) {
            model.updateFilteredMemberList(predicate);
        }
    }
}
