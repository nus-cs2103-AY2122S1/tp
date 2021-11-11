package nustracker.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help window should be shown to the user. */
    private final boolean showHelp;

    /** Settings window should be shown to the user */
    private final boolean showSettings;

    /** The application should exit. */
    private final boolean exit;

    /** The theme should toggle between Light and Dark theme */
    private final boolean toggleTheme;

    /** The main window should refresh */
    private final boolean toggleRefresh;

    /** The student list should be displayed. */
    private final boolean toggleStudents;

    /** The event list should be displayed. */
    private final boolean toggleEvents;
    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit,
                         boolean showSettings, boolean toggleTheme, boolean toggleRefresh,
                         boolean toggleStudents, boolean toggleEvents) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.showSettings = showSettings;
        this.exit = exit;

        // Cannot toggle both the student and event list at the same time.
        assert !toggleStudents && !toggleEvents || toggleStudents != toggleEvents;

        this.toggleTheme = toggleTheme;
        this.toggleRefresh = toggleRefresh;
        this.toggleStudents = toggleStudents;
        this.toggleEvents = toggleEvents;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser,
                false, false, false, false, false, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isShowSettings() {
        return showSettings;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isToggleStudents() {
        return toggleStudents;
    }

    public boolean isToggleTheme() {
        return toggleTheme;
    }

    public boolean isToggleRefresh() {
        return toggleRefresh;
    }

    public boolean isToggleEvents() {
        return toggleEvents;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && showSettings == otherCommandResult.showSettings
                && exit == otherCommandResult.exit
                && toggleTheme == otherCommandResult.toggleTheme
                && toggleRefresh == otherCommandResult.toggleRefresh
                && toggleStudents == otherCommandResult.toggleStudents
                && toggleEvents == otherCommandResult.toggleEvents;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, showSettings, exit, toggleStudents, toggleTheme,
                toggleRefresh, toggleEvents);
    }

}
