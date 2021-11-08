package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.member.Member;

/**
 * Represents the in-memory model of the ezFoodie data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Account account;
    private final EzFoodie ezFoodie;
    private final UserPrefs userPrefs;
    private final FilteredList<Member> filteredMembers;
    private final FilteredList<Member> filteredMembersForView;
    private final SortedList<Member> sortedMembers;

    /**
     * Initializes a ModelManager with the given account, ezFoodie and userPrefs.
     * Constructs a {@code ModelManager} with input variables {@code ReadOnlyAccount},
     * {@code ReadOnlyEzFoodie} and {@code ReadOnlyUserPrefs}.
     */
    public ModelManager(ReadOnlyAccount account, ReadOnlyEzFoodie ezFoodie, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(account, ezFoodie, userPrefs);

        logger.fine("Initializing with account: " + account + ", + ezFoodie: " + ezFoodie
                + " and user prefs " + userPrefs);

        this.account = new Account(account);
        this.ezFoodie = new EzFoodie(ezFoodie);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredMembers = new FilteredList<>(this.ezFoodie.getMemberList());
        filteredMembersForView = new FilteredList<>(this.ezFoodie.getMemberList());
        sortedMembers = new SortedList<>(filteredMembers); // Wrap the FilteredList in a SortedList
        sortedMembers.setComparator(COMPARATOR_SORT_MEMBERS_BY_ID_ASC);
    }

    /**
     * Constructs a {@code ModelManager} without any input.
     */
    public ModelManager() {
        this(new Account(), new EzFoodie(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    /**
     * Returns the user prefs.
     */
    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    /**
     * Returns the user prefs' GUI settings.
     */
    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    /**
     * Sets the user prefs' GUI settings.
     */
    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    /**
     * Returns the user prefs' account path.
     */
    @Override
    public Path getAccountFilePath() {
        return userPrefs.getAccountFilePath();
    }

    /**
     * Sets the user prefs' account path.
     */
    @Override
    public void setAccountFilePath(Path accountFilePath) {
        requireNonNull(accountFilePath);
        userPrefs.setAccountFilePath(accountFilePath);
    }

    /**
     * Returns the user prefs' ezFoodie file path.
     */
    @Override
    public Path getEzFoodieFilePath() {
        return userPrefs.getEzFoodieFilePath();
    }

    /**
     * Sets the user prefs' ezFoodie file path.
     */
    @Override
    public void setEzFoodieFilePath(Path ezFoodieFilePath) {
        requireNonNull(ezFoodieFilePath);
        userPrefs.setEzFoodieFilePath(ezFoodieFilePath);
    }

    //=========== Account ====================================================================================

    /**
     * Replaces account with the data in {@code account}.
     */
    @Override
    public void setAccount(ReadOnlyAccount account) {
        requireNonNull(account);
        this.account.resetData(account);
    }

    /**
     * Returns the Account.
     */
    @Override
    public ReadOnlyAccount getAccount() {
        return account;
    }

    //=========== EzFoodie ===================================================================================

    /**
     * Replaces ezFoodie data with the data in {@code ezFoodie}.
     */
    @Override
    public void setEzFoodie(ReadOnlyEzFoodie ezFoodie) {
        this.ezFoodie.resetData(ezFoodie);
    }

    /**
     * Returns the EzFoodie.
     */
    @Override
    public ReadOnlyEzFoodie getEzFoodie() {
        return ezFoodie;
    }

    /**
     * Returns true if a member with the same identity as {@code member} exists in the ezFoodie.
     */
    @Override
    public boolean hasMember(Member member) {
        requireNonNull(member);
        return ezFoodie.hasMember(member);
    }

    /**
     * Returns true if a member with the same identity as {@code member} exists in the filtered ezFoodie.
     * {@code predicate} is the filter condition for the filtered ezFoodie.
     */
    @Override
    public boolean hasMember(Member member, Predicate<Member> predicate) {
        requireNonNull(member);
        return ezFoodie.hasMember(member, predicate);
    }

    /**
     * Deletes the given member.
     * The member must exist in the ezFoodie.
     */
    @Override
    public void deleteMember(Member target) {
        ezFoodie.removeMember(target);
    }

    /**
     * Adds the given member.
     * {@code member} must not already exist in the ezFoodie.
     */
    @Override
    public void addMember(Member member) {
        ezFoodie.addMember(member);
        updateFilteredMemberList(PREDICATE_SHOW_ALL_MEMBERS);
    }

    /**
     * Replaces the given member {@code target} with {@code editedMember}.
     * {@code target} must exist in the ezFoodie.
     * The member identity of {@code editedMember} must not be the same as another existing member in the ezFoodie.
     */
    @Override
    public void setMember(Member target, Member editedMember) {
        requireAllNonNull(target, editedMember);

        ezFoodie.setMember(target, editedMember);
    }

    //=========== Updated Member List for display ============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Member} backed by the internal list of
     * {@code versionedEzFoodie}.
     */
    @Override
    public ObservableList<Member> getUpdatedMemberList() {
        return sortedMembers;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Member} backed by the internal list of
     * {@code versionedEzFoodie} for viewCommand to use only.
     */
    @Override
    public ObservableList<Member> getUpdatedMemberListForView() {
        return filteredMembersForView;
    }

    //=========== Filtered Member List Accessors =============================================================

    /**
     * Updates the filter of the filtered member list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    @Override
    public void updateFilteredMemberList(Predicate<Member> predicate) {
        requireNonNull(predicate);
        filteredMembers.setPredicate(predicate);
    }

    /**
     * Updates the filter of the filtered member list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    @Override
    public void updateFilteredMemberListForView(Predicate<Member> predicate) {
        requireNonNull(predicate);
        filteredMembersForView.setPredicate(predicate);
    }

    //=========== Sorted Member List Accessors ===============================================================

    /**
     * Updates the sort of the sorted member list to sort by the given {@code comparator}.
     *
     * @throws NullPointerException if {@code comparator} is null.
     */
    @Override
    public void updateSortedMemberList(Comparator<Member> comparator) {
        requireNonNull(comparator);
        sortedMembers.setComparator(comparator);
    }

    /**
     * Override the equals method.
     */
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
        return ezFoodie.equals(other.ezFoodie)
                && userPrefs.equals(other.userPrefs)
                && filteredMembers.equals(other.filteredMembers)
                && sortedMembers.equals(other.sortedMembers);
    }

}
