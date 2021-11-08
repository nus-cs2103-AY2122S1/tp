package seedu.awe.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.awe.commons.core.Messages.MESSAGE_DELETEEXPENSECOMMAND_CANNOT_BE_DELETED;
import static seedu.awe.commons.core.Messages.MESSAGE_DELETEEXPENSECOMMAND_SUCCESS;
import static seedu.awe.commons.core.Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX;
import static seedu.awe.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.awe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.awe.logic.commands.CommandTestUtil.showExpenseAtIndex;
import static seedu.awe.testutil.Assert.assertThrows;
import static seedu.awe.testutil.TypicalExpenses.getTypicalAwe;
import static seedu.awe.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.awe.testutil.TypicalIndexes.INDEX_SECOND;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.awe.commons.core.GuiSettings;
import seedu.awe.commons.core.Messages;
import seedu.awe.commons.core.index.Index;
import seedu.awe.logic.commands.exceptions.CommandException;
import seedu.awe.model.Model;
import seedu.awe.model.ModelManager;
import seedu.awe.model.ReadOnlyAwe;
import seedu.awe.model.ReadOnlyUserPrefs;
import seedu.awe.model.UserPrefs;
import seedu.awe.model.expense.Cost;
import seedu.awe.model.expense.Expense;
import seedu.awe.model.expense.ExpenseList;
import seedu.awe.model.group.Group;
import seedu.awe.model.group.GroupName;
import seedu.awe.model.group.exceptions.DuplicateGroupException;
import seedu.awe.model.payment.Payment;
import seedu.awe.model.person.Person;
import seedu.awe.model.transactionsummary.TransactionSummary;
import seedu.awe.ui.MainWindow;
import seedu.awe.ui.UiView;

public class DeleteExpenseCommandTest {

    private Model model = new ModelManager(getTypicalAwe(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() throws Exception {
        MainWindow.setViewEnum(UiView.EXPENSE_PAGE);
        ModelStubWithExpense modelStub = new ModelStubWithExpense();
        Group deleteExpenseGroup = model.getGroupByName(new GroupName("Bali"));

        ArrayList<Expense> expenses = deleteExpenseGroup.getExpenses();
        ExpenseList expensesList = new ExpenseList();
        expensesList.addAll(expenses);
        Expense expenseToDelete = expenses.get(0);
        expensesList.remove(expenseToDelete);

        CommandResult commandResult = new DeleteExpenseCommand(INDEX_FIRST).execute(modelStub);

        assertEquals(String.format(MESSAGE_DELETEEXPENSECOMMAND_SUCCESS,
                expenseToDelete.getDescription().getFullDescription()), commandResult.getFeedbackToUser());
        assertEquals(expensesList, modelStub.expenses);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        MainWindow.setViewEnum(UiView.EXPENSE_PAGE);
        ModelStubWithExpense modelStub = new ModelStubWithExpense();
        Index outOfBoundIndex = Index.fromOneBased(modelStub.getExpenses().size() + 1);
        DeleteExpenseCommand deleteExpenseCommand = new DeleteExpenseCommand(outOfBoundIndex);

        assertThrows(CommandException.class,
                MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX, () -> deleteExpenseCommand.execute(modelStub));
    }

    @Test
    public void execute_inValidViewPage_throwsCommandException() {
        MainWindow.setViewEnum(UiView.CONTACT_PAGE);
        ModelStubWithExpense modelStub = new ModelStubWithExpense();
        DeleteExpenseCommand deleteExpenseCommand = new DeleteExpenseCommand(INDEX_FIRST);
        assertThrows(CommandException.class,
                MESSAGE_DELETEEXPENSECOMMAND_CANNOT_BE_DELETED, () -> deleteExpenseCommand.execute(modelStub));
    }

    @Test
    public void execute_validIndexFilteredList_success() throws Exception {
        MainWindow.setViewEnum(UiView.EXPENSE_PAGE);
        model.setExpenses(model.getGroupByName(new GroupName("Bali")));
        showExpenseAtIndex(model, INDEX_FIRST);

        Expense expenseToDelete = model.getExpenses().get(INDEX_FIRST.getZeroBased());
        DeleteExpenseCommand deleteExpenseCommand = new DeleteExpenseCommand(INDEX_FIRST);

        Model expectedModel = new ModelManager(getTypicalAwe(), new UserPrefs());
        expectedModel.setExpenses(expectedModel.getGroupByName(new GroupName("Bali")));
        Group group = expectedModel.getGroupByName(new GroupName("Bali"));
        Group newGroup = group.deleteExpense(expenseToDelete);
        expectedModel.setGroup(group, newGroup);
        expectedModel.deleteExpense(expenseToDelete, newGroup);
        showNoExpense(expectedModel);
        String expectedFeedback = String.format(MESSAGE_DELETEEXPENSECOMMAND_SUCCESS,
                expenseToDelete.getDescription().getFullDescription());
        CommandResult expectedCommandResult = new CommandResult(expectedFeedback, false,
            false, false, false,
            true, false, false);

        assertCommandSuccess(deleteExpenseCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        MainWindow.setViewEnum(UiView.EXPENSE_PAGE);
        model.setExpenses(model.getGroupByName(new GroupName("Bali")));
        showExpenseAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of awe book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAwe().getExpenseList().size());

        DeleteExpenseCommand deleteExpenseCommand = new DeleteExpenseCommand(outOfBoundIndex);

        assertCommandFailure(deleteExpenseCommand, model, Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteExpenseCommand deleteFirstCommand = new DeleteExpenseCommand(INDEX_FIRST);
        DeleteExpenseCommand deleteSecondCommand = new DeleteExpenseCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteExpenseCommand deleteFirstCommandCopy = new DeleteExpenseCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different expense -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }


    /**
     * Updates {@code model}'s filtered expense list to show no expenses.
     */
    private void showNoExpense(Model model) {
        model.updateFilteredExpenseList(p -> false);
        assertTrue(model.getExpenses().isEmpty());
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
        public Path getAweFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAweFilePath(Path aweFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAwe(ReadOnlyAwe newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAwe getAwe() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Group getActiveGroupFromAwe() throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTransactionSummary(HashMap<Person, Cost> summary) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAllMembersOfGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPayments(List<Payment> payments) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns an unmodifiable view of the list of {@code Group} backed by the internal list of
         * {@code versionedAwe}
         */
        @Override
        public ObservableList<Group> getFilteredGroupList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredGroupList(Predicate<Group> predicate) {
        }

        @Override
        public Group getGroupByName(GroupName groupName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGroup(Group group, Group newGroup) throws DuplicateGroupException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addExpense(Expense expense, Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteExpense(Expense expense, Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Payment> getPayments() {
            return null;
        }

        @Override
        public ObservableList<Expense> getExpenses() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExpenses(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredExpenseList(Predicate<Expense> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<TransactionSummary> getTransactionSummary() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isCurrentExpenseList(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Expense getExpense(int index) {
            return null;
        }
    }

    /**
     * A Model stub that contains the expense list of the Bali group.
     */
    private class ModelStubWithExpense extends ModelStub {
        private final ExpenseList expenses;
        private FilteredList<Expense> filteredExpensesList;
        private Group group;

        ModelStubWithExpense() {
            expenses = new ExpenseList();
            group = model.getGroupByName(new GroupName("Bali"));
            setExpenses(group);
            filteredExpensesList = new FilteredList<>(expenses.asUnmodifiableObservableList());
        }

        @Override
        public void setExpenses(Group group) {
            requireNonNull(group);
            expenses.clear();
            expenses.addAll(group.getExpenses());
            filteredExpensesList = new FilteredList<>(expenses.asUnmodifiableObservableList());
        }

        @Override
        public Expense getExpense(int index) {
            return filteredExpensesList.get(index);
        }

        @Override
        public ObservableList<Expense> getExpenses() {
            return filteredExpensesList;
        }

        @Override
        public void deleteExpense(Expense expense, Group group) {
            expenses.remove(expense);
            filteredExpensesList.remove(expense);
        }

        @Override
        public Group getActiveGroupFromAwe() {
            return group;
        }

        @Override
        public void setGroup(Group group, Group newGroup) {
            this.group = newGroup;
        }

        @Override
        public void updateFilteredExpenseList(Predicate<Expense> predicate) {
            filteredExpensesList.setPredicate(predicate);
        }

    }
}
