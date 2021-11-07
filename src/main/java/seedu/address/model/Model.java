package seedu.address.model;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.guest.Archive;
import seedu.address.model.guest.Guest;
import seedu.address.model.guest.PassportNumber;
import seedu.address.model.guest.ReadOnlyGuestBook;
import seedu.address.model.vendor.ReadOnlyVendorBook;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.vendor.VendorId;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Guest> PREDICATE_SHOW_ALL_GUESTS = unused -> true;
    Predicate<Vendor> PREDICATE_SHOW_ALL_VENDORS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' guest manager file path.
     */
    Path getGuestBookFilePath();

    /**
     * Sets the user prefs' guest manager file path.
     */
    void setGuestBookFilePath(Path guestManagerFilePath);

    /**
     * Replaces pocket hotel data with the data in {@code guestBook}.
     */
    void setGuestBook(ReadOnlyGuestBook guestBook);

    /**
     * Returns the GuestBook
     */
    ReadOnlyGuestBook getGuestBook();

    // ==================== Guest operations =====================

    /**
     * Returns true if a guest with the same identity as {@code guest} exists in the address book.
     */
    boolean hasGuest(Guest guest);

    /**
     * Deletes the given guest.
     * The guest must exist in the address book.
     */
    void deleteGuest(Guest target);

    /**
     * Adds the given guest.
     * {@code guest} must not already exist in the address book.
     */
    void addGuest(Guest guest);

    /**
     * Gets guest given the passport number
     */
    Optional<Guest> getGuest(PassportNumber passportNumber);

    /**
     * Replaces the given guest {@code target} with {@code editedGuest}.
     * {@code target} must exist in the address book.
     * The guest identity of {@code editedGuest} must not be the same as another existing guest in the address book.
     */
    void setGuest(Guest target, Guest editedGuest);

    /**
     * Returns an unmodifiable view of the filtered guest list
     */
    ObservableList<Guest> getFilteredGuestList();

    /**
     * Updates the filter of the filtered guest list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredGuestList(Predicate<Guest> predicate);

    // =========== Archive operations ================================================================================

    void setArchive(Archive archive);

    Archive getArchive();

    boolean hasArchivedGuest(Guest guest);

    void deleteArchivedGuest(Guest target);

    void addArchivedGuest(Guest guest);

    Optional<Guest> getArchivedGuest(PassportNumber passportNumber);

    void setArchivedGuest(Guest target, Guest editedGuest);

    Path getArchiveFilePath();

    // ==================== Vendor operations =====================

    /**
     * Returns true if a tag with the same identity as {@code vendor} exists in the address book.
     */
    boolean hasVendor(Vendor vendor);

    /**
     * Deletes the given vendor.
     * The vendor must exist in the address book.
     */
    void deleteVendor(Vendor target);

    /**
     * Adds the given vendor.
     * {@code vendor} must not already exist in the address book.
     */
    void addVendor(Vendor vendor);

    /**
     * Replaces the given vendor {@code target} with {@code editedVendor}.
     * {@code target} must exist in the address book.
     * The vendor identity of {@code editedVendor} must not be the same as another existing vendor in the address book.
     */
    void setVendor(Vendor target, Vendor editedVendor);

    /**
     * Gets vendor given the vendor id
     */
    Optional<Vendor> getVendor(VendorId vendorId);

    /**
     * Returns an unmodifiable view of the filtered vendor list
     */
    ObservableList<Vendor> getFilteredVendorList();

    /**
     * Updates the filter of the filtered vendor list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredVendorList(Predicate<Vendor> predicate);

    /**
     * Replaces pocket hotel data with the data in {@code vendorManager}.
     */
    void setVendorBook(ReadOnlyVendorBook vendorManager);

    /**
     * Returns the VendorBook
     */
    ReadOnlyVendorBook getVendorBook();

    Path getVendorBookFilePath();
}
