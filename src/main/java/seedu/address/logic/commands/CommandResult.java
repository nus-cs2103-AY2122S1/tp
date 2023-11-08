package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** The application should display Facility Tab */
    private final boolean showFacilityTab;

    /** The application should display Member Tab */
    private final boolean showMemberTab;

    /**
     * Constructs a {@code CommandResult} with all its fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit,
                         boolean showFacilityTab, boolean showMemberTab) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.showFacilityTab = showFacilityTab;
        this.showMemberTab = showMemberTab;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false,
                false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}, {@code showHelp} and {@code exit},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, showHelp, exit, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * {@code showFacilityTab}, {@code showMemberTab}, and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean isHelpOrExit, boolean showFacilityTab, boolean showMemberTab) {
        this(feedbackToUser, false, false, showFacilityTab, showMemberTab);
        assert !isHelpOrExit;
        assert showFacilityTab != showMemberTab;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isShowFacilityTab() {
        return showFacilityTab;
    }

    public boolean isShowMemberTab() {
        return showMemberTab;
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
                && exit == otherCommandResult.exit
                && showFacilityTab == otherCommandResult.showFacilityTab
                && showMemberTab == otherCommandResult.showMemberTab;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, showFacilityTab, showMemberTab);
    }

}
