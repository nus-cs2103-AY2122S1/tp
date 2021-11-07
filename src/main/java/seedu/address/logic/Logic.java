package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.guest.Guest;
import seedu.address.model.guest.ReadOnlyGuestBook;
import seedu.address.model.vendor.ReadOnlyVendorBook;
import seedu.address.model.vendor.Vendor;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;


    /**
     * Returns the GuestBook.
     *
     * @see seedu.address.model.Model#getGuestBook()
     */
    ReadOnlyGuestBook getGuestBook();

    /**
     * Returns an unmodifiable view of the filtered list of guests
     */
    ObservableList<Guest> getFilteredGuestList();

    /**
     * Returns the Archive.
     *
     * @see seedu.address.model.Model#getArchive()
     */
    ReadOnlyGuestBook getArchive();

    /**
     * Returns the VendorBook.
     *
     * @see seedu.address.model.Model#getVendorBook()
     */
    ReadOnlyVendorBook getVendorBook();

    /**
     * Returns an unmodifiable view of the filtered list of vendors
     */
    ObservableList<Vendor> getFilteredVendorList();

    /**
     * Returns the user prefs' guest book file path.
     */
    Path getGuestBookFilePath();

    /**
     * Returns the user prefs' vendor book file path.
     */
    Path getVendorBookFilePath();

    /**
     * Returns the user prefs' archive file path.
     */
    Path getArchiveFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
