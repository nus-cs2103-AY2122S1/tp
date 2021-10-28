package seedu.edrecord.logic;

import java.nio.file.Path;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import seedu.edrecord.commons.core.GuiSettings;
import seedu.edrecord.logic.commands.CommandResult;
import seedu.edrecord.logic.commands.exceptions.CommandException;
import seedu.edrecord.logic.parser.exceptions.ParseException;
import seedu.edrecord.model.ReadOnlyEdRecord;
import seedu.edrecord.model.module.Module;
import seedu.edrecord.model.person.Person;
import seedu.edrecord.ui.PersonListPanel;

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
     * Returns EdRecord.
     *
     * @see seedu.edrecord.model.Model#getEdRecord()
     */
    ReadOnlyEdRecord getEdRecord();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns the selected view */
    ObservableValue<PersonListPanel.View> getSelectedView();

    /**
     * Returns the user prefs' edrecord file path.
     */
    Path getEdRecordFilePath();

    /**
     * Returns the currently selected module.
     */
    ObservableValue<Module> getSelectedModule();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
