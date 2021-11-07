package seedu.address.testutil;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.guest.Archive;
import seedu.address.model.guest.Guest;
import seedu.address.model.guest.PassportNumber;
import seedu.address.model.guest.ReadOnlyGuestBook;
import seedu.address.model.vendor.ReadOnlyVendorBook;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.vendor.VendorId;

/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {
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
    public Path getGuestBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuestBookFilePath(Path guestManagerFilePath) {
        throw new AssertionError("This method should not be called.");

    }

    @Override
    public void setGuestBook(ReadOnlyGuestBook guestBook) {
        throw new AssertionError("This method should not be called.");

    }

    @Override
    public ReadOnlyGuestBook getGuestBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addGuest(Guest guest) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Optional<Guest> getGuest(PassportNumber passportNumber) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasGuest(Guest guest) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteGuest(Guest target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuest(Guest target, Guest editedGuest) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Guest> getFilteredGuestList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredGuestList(Predicate<Guest> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setArchive(Archive archive) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Archive getArchive() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasArchivedGuest(Guest guest) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteArchivedGuest(Guest target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addArchivedGuest(Guest guest) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Optional<Guest> getArchivedGuest(PassportNumber passportNumber) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setArchivedGuest(Guest target, Guest editedGuest) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getArchiveFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasVendor(Vendor vendor) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteVendor(Vendor target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addVendor(Vendor vendor) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setVendor(Vendor target, Vendor editedVendor) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Optional<Vendor> getVendor(VendorId vendorId) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Vendor> getFilteredVendorList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredVendorList(Predicate<Vendor> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setVendorBook(ReadOnlyVendorBook vendorManager) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyVendorBook getVendorBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getVendorBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

}
