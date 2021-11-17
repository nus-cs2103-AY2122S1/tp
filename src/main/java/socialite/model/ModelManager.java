package socialite.model;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import socialite.commons.core.GuiSettings;
import socialite.commons.core.LogsCenter;
import socialite.commons.util.CollectionUtil;
import socialite.model.person.Person;

/**
 * Represents the in-memory model of the contact list data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ContactList contactList;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final CommandHistory commandHistory;
    private final ProfilePictureSyncModel profilePictureSyncModel;

    /**
     * Initializes a ModelManager with the given contactList, userPrefs and commandHistory.
     */
    public ModelManager(
            ReadOnlyContactList contactList,
            ReadOnlyUserPrefs userPrefs,
            ReadOnlyCommandHistory commandHistory) {
        super();
        CollectionUtil.requireAllNonNull(contactList, userPrefs);

        this.contactList = new ContactList(contactList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.contactList.getPersonList());
        this.commandHistory = new CommandHistory(commandHistory);
        this.profilePictureSyncModel = new ProfilePictureSyncModel();


        logger.fine("Initializing with:"
                + "\ncontact list: " + contactList
                + "\nuser prefs: " + userPrefs
                + "\ncommand history: " + commandHistory);
    }

    public ModelManager() {
        this(new ContactList(), new UserPrefs(), new CommandHistory());
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
    public Path getContactListFilePath() {
        return userPrefs.getContactListFilePath();
    }

    @Override
    public void setContactListFilePath(Path contactListFilePath) {
        requireNonNull(contactListFilePath);
        userPrefs.setContactListFilePath(contactListFilePath);
    }

    //=========== ContactList ================================================================================

    @Override
    public void setContactList(ReadOnlyContactList contactList) {
        this.contactList.resetData(contactList);
    }

    @Override
    public ReadOnlyContactList getContactList() {
        return contactList;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return contactList.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        contactList.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        contactList.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        CollectionUtil.requireAllNonNull(target, editedPerson);

        contactList.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedContactList}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        // Sort the contact list
        contactList.sortPersons();
        filteredPersons.setPredicate(predicate);
    }

    //=========== Command History ============================================================================

    @Override
    public CommandHistory getCommandHistory() {
        return commandHistory;
    }

    @Override
    public void addCommandToHistory(String command) {
        commandHistory.addCommand(command);
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
        return contactList.equals(other.contactList)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

    //=========== Profile Picture ============================================================================

    @Override
    public void deleteProfilePicture(Path name) {
        this.profilePictureSyncModel.deleteProfilePicture(name);
    }

    @Override
    public ProfilePictureSyncModel.ProfilePictureEditDescriptor getProfilePictureEditDescriptor() {
        return this.profilePictureSyncModel.getProfilePictureEditDescriptor();
    }

    @Override
    public void saveProfilePicture(File file, String name) {
        this.profilePictureSyncModel.saveProfilePicture(file, name);
    }

    @Override
    public void clearProfilePictureModel() {
        this.profilePictureSyncModel.clear();
    }


    //=========== Pin & Unpin ================================================================================
    @Override
    public void pinPerson(Person person) {
        contactList.pinPerson(person);
    }

    @Override
    public void unpinPerson(Person person) {
        contactList.unpinPerson(person);
    }

}
