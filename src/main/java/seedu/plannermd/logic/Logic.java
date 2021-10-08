package seedu.plannermd.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.plannermd.commons.core.GuiSettings;
import seedu.plannermd.logic.commands.CommandResult;
import seedu.plannermd.logic.commands.exceptions.CommandException;
import seedu.plannermd.logic.parser.exceptions.ParseException;
import seedu.plannermd.model.Model.State;
import seedu.plannermd.model.ReadOnlyPlannerMd;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.patient.Patient;

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
     * Returns the State of Model
     *
     * @see seedu.plannermd.model.Model#getState()
     */
    State getState();

    /**
     * Returns the PlannerMd.
     *
     * @see seedu.plannermd.model.Model#getPlannerMd()
     */
    ReadOnlyPlannerMd getPlannerMd();

    /** Returns an unmodifiable view of the filtered list of patients */
    ObservableList<Patient> getFilteredPatientList();

    /** Returns an unmodifiable view of the filtered list of Doctors */
    ObservableList<Doctor> getFilteredDoctorList();

    /**
     * Returns the user prefs' plannermd file path.
     */
    Path getPlannerMdFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
