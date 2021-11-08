package seedu.awe.model;

import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.awe.commons.core.GuiSettings;
import seedu.awe.commons.core.LogsCenter;
import seedu.awe.logic.commands.exceptions.CommandException;
import seedu.awe.model.expense.Cost;
import seedu.awe.model.expense.Expense;
import seedu.awe.model.group.Group;
import seedu.awe.model.group.GroupName;
import seedu.awe.model.payment.Payment;
import seedu.awe.model.person.Person;
import seedu.awe.model.transactionsummary.TransactionSummary;

/**
 * Represents the in-memory model of the awe book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Awe awe;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Group> filteredGroups;
    private final FilteredList<Expense> filteredExpenses;
    private final FilteredList<TransactionSummary> transactionSummary;
    private final FilteredList<Payment> payments;

    /**
     * Initializes a ModelManager with the given awe and userPrefs.
     */
    public ModelManager(ReadOnlyAwe awe, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(awe, userPrefs);

        logger.fine("Initializing with awe book: " + awe + " and user prefs " + userPrefs);

        this.awe = new Awe(awe);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.awe.getPersonList());
        filteredGroups = new FilteredList<>(this.awe.getGroupList());
        filteredExpenses = new FilteredList<>(this.awe.getExpenseList());
        transactionSummary = new FilteredList<>(this.awe.getTransactionSummaryList());
        payments = new FilteredList<>(this.awe.getPaymentList());
    }

    public ModelManager() {
        this(new Awe(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAweFilePath() {
        return userPrefs.getAweFilePath();
    }

    @Override
    public void setAweFilePath(Path aweFilePath) {
        requireNonNull(aweFilePath);
        userPrefs.setAweFilePath(aweFilePath);
    }

    //=========== Awe ================================================================================

    @Override
    public void setAwe(ReadOnlyAwe awe) {
        this.awe.resetData(awe);
    }

    @Override
    public ReadOnlyAwe getAwe() {
        return awe;
    }

    @Override
    public Group getActiveGroupFromAwe() throws CommandException {
        return awe.getGroupFromExpenseList().orElseThrow(() ->
                new CommandException("Sorry! The operation could not be completed!"));
    }

    //=========== Person ================================================================================

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return awe.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        awe.removePerson(target);
        deletePersonFromGroups(target);
    }

    @Override
    public void addPerson(Person person) {
        awe.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        awe.setPerson(target, editedPerson);
    }

    @Override
    public void setAllMembersOfGroup(Group group) {
        requireNonNull(group);

        for (Person member : group.getMembers()) {
            awe.setPerson(member, member);
        }
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAwe}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Groups ================================================================================

    /**
     * Adds group into awe.
     * Assumption is that group name is unique.
     *
     * @param group Group object representing members going on a trip.
     */
    @Override
    public void addGroup(Group group) {
        awe.addGroup(group);
        updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
    }

    /**
     * Deletes group based on groupName.
     * Assumption is that group name is unique.
     *
     * @param group Group object representing members going on a trip.
     */
    @Override
    public void deleteGroup(Group group) {
        awe.removeGroup(group);
    }

    /**
     * Returns boolean representing if a given group is in the model.
     *
     * @param group Group object representing members going on a trip.
     * @return boolean object representing if a given group is in the model.
     */
    @Override
    public boolean hasGroup(Group group) {
        requireNonNull(group);
        return awe.hasGroup(group);
    }

    @Override
    public void setGroup(Group group, Group newGroup) {
        requireNonNull(group);
        awe.setGroup(group, newGroup);
        updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
    }
    //=========== TransactionSummary List Accessors =============================================================

    @Override
    public void setTransactionSummary(HashMap<Person, Cost> summary) {
        requireNonNull(summary);
        awe.setTransactionSummary(summary);
    }

    @Override
    public ObservableList<TransactionSummary> getTransactionSummary() {
        return transactionSummary;
    }

    //=========== Payment List Accessors =============================================================

    @Override
    public ObservableList<Payment> getPayments() {
        return payments;
    }

    private void deletePersonFromGroups(Person person) {
        List<Group> groupList = getGroupsOfPerson(person);
        for (int j = 0; j < groupList.size(); j++) {
            Group group = groupList.get(j);
            Group newGroup = group.removeMember(person);
            if (newGroup.getMembers().isEmpty()) {
                deleteGroup(group);
            } else {
                setGroup(group, newGroup);
            }
        }
    }

    private List<Group> getGroupsOfPerson(Person person) {
        List<Group> groupList = new ArrayList<>();
        List<Group> groups = awe.getGroupList();
        for (int i = 0; i < groups.size(); i++) {
            Group group = groups.get(i);
            if (group.isPartOfGroup(person)) {
                groupList.add(group);
            }
        }
        return groupList;
    }

    @Override
    public void setPayments(List<Payment> payments) {
        requireNonNull(payments);
        awe.setPayments(payments);
    }


    //=========== Filtered Group List Accessors =============================================================



    /**
     * Returns an unmodifiable view of the list of {@code Group} backed by the internal list of
     * {@code versionedAwe}
     */
    @Override
    public ObservableList<Group> getFilteredGroupList() {
        return filteredGroups;
    }

    @Override
    public void updateFilteredGroupList(Predicate<Group> predicate) {
        requireNonNull(predicate);
        filteredGroups.setPredicate(predicate);
    }

    @Override
    public Group getGroupByName(GroupName groupName) {
        requireNonNull(groupName);
        return awe.getGroupByName(groupName);
    }

    //=========== Expenses List Accessors ===================================================================

    /**
     * Adds expense into expense list in address book, if
     * the current list of expenses belongs to the
     * specified group.
     *
     * @param expense The expense to add.
     * @param group The group which the expense belongs to.
     */
    @Override
    public void addExpense(Expense expense, Group group) {
        awe.addExpense(expense, group);
        updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);
    }

    /**
     * Deletes expense from the expense list in address book, if
     * the current list of expenses belongs to the specified
     * group.
     *
     * @param expense The expense to delete
     */
    @Override
    public void deleteExpense(Expense expense, Group group) {
        awe.deleteExpense(expense, group);
    }

    //=========== Filtered Expense List Accessors =============================================================

    /** Checks if the current expense list belongs to the
     * specified group.
     *
     * @return true if expense list belongs to the group.
     */
    @Override
    public boolean isCurrentExpenseList(Group group) {
        return awe.isCurrentExpenseList(group);
    }

    /**
     * Returns the current list of expenses.
     *
     * @return expenseslist The list of expenses.
     */
    @Override
    public ObservableList<Expense> getExpenses() {
        return filteredExpenses;
    }

    @Override
    public Expense getExpense(int index) {
        return filteredExpenses.get(index);
    }

    @Override
    public void updateFilteredExpenseList(Predicate<Expense> predicate) {
        requireNonNull(predicate);
        filteredExpenses.setPredicate(predicate);
    }

    @Override
    public void setExpenses(Group group) {
        awe.setExpenses(group);
        updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return awe.equals(other.awe)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredGroups.equals(other.filteredGroups);
    }

}
