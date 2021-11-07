package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.guest.Archive;
import seedu.address.model.guest.Guest;
import seedu.address.model.guest.GuestBook;
import seedu.address.model.guest.PassportNumber;
import seedu.address.model.guest.ReadOnlyGuestBook;
import seedu.address.model.vendor.ReadOnlyVendorBook;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.vendor.VendorBook;
import seedu.address.model.vendor.VendorId;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final GuestBook guestBook;
    private final VendorBook vendorBook;
    private final Archive archive;
    private final UserPrefs userPrefs;
    private final FilteredList<Guest> filteredGuests;
    private final FilteredList<Vendor> filteredVendors;

    /**
     * Initializes a ModelManager with the given guestBook, vendorBook and userPrefs.
     */
    public ModelManager(ReadOnlyGuestBook guestBook, ReadOnlyVendorBook vendorBook,
                        ReadOnlyUserPrefs userPrefs, ReadOnlyGuestBook archive) {
        super();
        requireAllNonNull(guestBook, vendorBook, userPrefs);

        logger.fine("Initializing with Pocket Hotel: " + guestBook + " and user prefs " + userPrefs);

        this.guestBook = new GuestBook(guestBook);
        this.vendorBook = new VendorBook(vendorBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.archive = new Archive(archive);
        filteredGuests = new FilteredList<>(this.guestBook.getGuestList());
        filteredVendors = new FilteredList<>(this.vendorBook.getVendorList());
    }

    public ModelManager() {
        this(new GuestBook(), new VendorBook(), new UserPrefs(), new Archive());
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
    public Path getGuestBookFilePath() {
        return userPrefs.getGuestBookFilePath();
    }

    @Override
    public void setGuestBookFilePath(Path guestBookFilePath) {
        requireNonNull(guestBookFilePath);
        userPrefs.setGuestBookFilePath(guestBookFilePath);
    }

    //=========== Guest Book ================================================================================

    @Override
    public void setGuestBook(ReadOnlyGuestBook guestBook) {
        this.guestBook.resetData(guestBook);
    }

    @Override
    public ReadOnlyGuestBook getGuestBook() {
        return guestBook;
    }

    @Override
    public boolean hasGuest(Guest guest) {
        requireNonNull(guest);
        return guestBook.hasGuest(guest);
    }

    @Override
    public void deleteGuest(Guest target) {
        guestBook.removeGuest(target);
    }

    @Override
    public void addGuest(Guest guest) {
        guestBook.addGuest(guest);
        updateFilteredGuestList(PREDICATE_SHOW_ALL_GUESTS);
    }

    @Override
    public Optional<Guest> getGuest(PassportNumber passportNumber) {
        return guestBook.getGuest(passportNumber);
    }

    @Override
    public void setGuest(Guest target, Guest editedGuest) {
        requireAllNonNull(target, editedGuest);
        guestBook.setGuest(target, editedGuest);
    }

    //=========== Archive ================================================================================

    @Override
    public void setArchive(Archive archive) {
        this.archive.resetData(archive);
    }

    @Override
    public Archive getArchive() {
        return archive;
    }

    @Override
    public boolean hasArchivedGuest(Guest guest) {
        requireNonNull(guest);
        return archive.hasGuest(guest);
    }

    @Override
    public void deleteArchivedGuest(Guest target) {
        archive.removeGuest(target);
    }

    @Override
    public void addArchivedGuest(Guest guest) {
        archive.addGuest(guest);
        //        updateFilteredGuestList(PREDICATE_SHOW_ALL_GUESTS);
    }

    @Override
    public Optional<Guest> getArchivedGuest(PassportNumber passportNumber) {
        return archive.getGuest(passportNumber);
    }

    @Override
    public void setArchivedGuest(Guest target, Guest editedGuest) {
        requireAllNonNull(target, editedGuest);
        archive.setGuest(target, editedGuest);
    }

    @Override
    public Path getArchiveFilePath() {
        return userPrefs.getArchiveFilePath();
    }

    //=========== Vendor Book ================================================================================
    @Override
    public boolean hasVendor(Vendor vendor) {
        requireNonNull(vendor);
        return vendorBook.hasVendor(vendor);
    }

    @Override
    public void deleteVendor(Vendor target) {
        vendorBook.removeVendor(target);
    }

    @Override
    public void addVendor(Vendor vendor) {
        vendorBook.addVendor(vendor);
        updateFilteredVendorList(PREDICATE_SHOW_ALL_VENDORS);
    }

    @Override
    public Optional<Vendor> getVendor(VendorId vendorId) {
        return vendorBook.getVendor(vendorId);
    }

    @Override
    public void setVendor(Vendor target, Vendor editedVendor) {
        requireAllNonNull(target, editedVendor);
        vendorBook.setVendor(target, editedVendor);
    }

    //=========== Filtered Guest/Vendor List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Guest} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Guest> getFilteredGuestList() {
        return filteredGuests;
    }

    @Override
    public ObservableList<Vendor> getFilteredVendorList() {
        return filteredVendors;
    }

    @Override
    public void updateFilteredGuestList(Predicate<Guest> predicate) {
        requireNonNull(predicate);
        filteredGuests.setPredicate(predicate);
    }

    @Override
    public void updateFilteredVendorList(Predicate<Vendor> predicate) {
        requireNonNull(predicate);
        filteredVendors.setPredicate(predicate);
    }

    @Override
    public void setVendorBook(ReadOnlyVendorBook vendorBook) {
        this.vendorBook.resetData(vendorBook);
    }

    @Override
    public ReadOnlyVendorBook getVendorBook() {
        return vendorBook;
    }

    @Override
    public Path getVendorBookFilePath() {
        return userPrefs.getVendorBookFilePath();
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

        return guestBook.equals(other.guestBook)
                && vendorBook.equals(other.vendorBook)
                && userPrefs.equals(other.userPrefs)
                && filteredGuests.equals(other.filteredGuests)
                && filteredVendors.equals(other.filteredVendors)
                && archive.equals(other.archive);
    }

}
