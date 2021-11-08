package seedu.track2gather.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.track2gather.commons.core.GuiSettings;
import seedu.track2gather.logic.commands.CommandResult;
import seedu.track2gather.logic.commands.exceptions.CommandException;
import seedu.track2gather.logic.parser.exceptions.ParseException;
import seedu.track2gather.model.ReadOnlyTrack2Gather;
import seedu.track2gather.model.person.Person;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the Track2Gather.
     *
     * @see seedu.track2gather.model.Model#getTrack2Gather()
     */
    ReadOnlyTrack2Gather getTrack2Gather();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the user prefs' contacts list file path.
     */
    Path getTrack2GatherFilePath();

    /** Returns the total number of contacts in the contacts list. */
    int getNumPersonsTotal();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
