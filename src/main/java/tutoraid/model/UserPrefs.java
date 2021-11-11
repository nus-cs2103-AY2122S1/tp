package tutoraid.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import tutoraid.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path studentBookFilePath = Paths.get("data" , "tutorAidStudents.json");
    private Path lessonBookFilePath = Paths.get("data", "tutorAidLessons.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setStudentBookFilePath(newUserPrefs.getStudentBookFilePath());
        setLessonBookFilePath(newUserPrefs.getLessonBookFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getStudentBookFilePath() {
        return studentBookFilePath;
    }

    public void setStudentBookFilePath(Path studentBookFilePath) {
        requireNonNull(studentBookFilePath);
        this.studentBookFilePath = studentBookFilePath;
    }

    public Path getLessonBookFilePath() {
        return lessonBookFilePath;
    }

    public void setLessonBookFilePath(Path lessonBookFilePath) {
        requireNonNull(lessonBookFilePath);
        this.lessonBookFilePath = lessonBookFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && studentBookFilePath.equals(o.studentBookFilePath)
                && lessonBookFilePath.equals(o.lessonBookFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, studentBookFilePath, lessonBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal student data file location : " + studentBookFilePath);
        sb.append("\nLocal lesson data file location : " + lessonBookFilePath);
        return sb.toString();
    }

}
