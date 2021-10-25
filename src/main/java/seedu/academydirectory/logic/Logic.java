package seedu.academydirectory.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.academydirectory.commons.core.GuiSettings;
import seedu.academydirectory.logic.commands.CommandResult;
import seedu.academydirectory.logic.commands.exceptions.CommandException;
import seedu.academydirectory.logic.parser.exceptions.ParseException;
import seedu.academydirectory.model.AdditionalInfo;
import seedu.academydirectory.model.ReadOnlyAcademyDirectory;
import seedu.academydirectory.model.student.Student;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException IModelf an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AcademyDirectory.
     *
     * @see seedu.academydirectory.model.Model#getAcademyDirectory()
     */
    ReadOnlyAcademyDirectory getAcademyDirectory();

    /** Returns an unmodifiable view of the filtered list of students */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Returns the user prefs' academy directory file path.
     */
    Path getAcademyDirectoryFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    AdditionalViewType getAdditionalViewType();

    AdditionalInfo<? extends Object> getAdditionalInfo();
}
