package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Person list will be shown to the user. */
    private final boolean isShowPersonList;

    /** Help information should be shown to the user. */
    private final boolean isShowHelp;

    /** Tag list should be shown to the user instead of the default person list. */
    private final boolean isShowTagList;

    /** Schedule should be shown to the user. */
    private final boolean isShowSchedule;

    /** The application should exit. */
    private final boolean isExit;


    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean isShowPersonList, boolean isShowHelp,
                         boolean isShowTagList, boolean isShowSchedule, boolean isExit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.isShowPersonList = isShowPersonList;
        this.isShowHelp = isShowHelp;
        this.isExit = isExit;
        this.isShowTagList = isShowTagList;
        this.isShowSchedule = isShowSchedule;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, true, false, false, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowPersonList() {
        return isShowPersonList;
    }

    public boolean isShowHelp() {
        return isShowHelp;
    }

    public boolean isShowTagList() {
        return isShowTagList;
    }

    public boolean isShowSchedule() {
        return isShowSchedule;
    }

    public boolean isExit() {
        return isExit;
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
                && isShowPersonList == otherCommandResult.isShowPersonList
                && isShowHelp == otherCommandResult.isShowHelp
                && isShowTagList == otherCommandResult.isShowTagList
                && isShowSchedule == otherCommandResult.isShowSchedule
                && isExit == otherCommandResult.isExit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, isShowPersonList, isShowHelp, isShowTagList, isShowSchedule, isExit);
    }

    @Override
    public String toString() {
        return "Feedback To User: " + feedbackToUser + "\n" + "Show Student ListL: " + isShowHelp + "\n"
                + "Show Help: " + isShowHelp + "\n" + "Show Tag List: " + isShowTagList + "\n"
                + "Show Schedule: " + isShowSchedule + "\n" + "Exit: " + isExit;
    }
}
