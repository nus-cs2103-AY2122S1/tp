package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {
    public enum DisplayState {
        CLIENT, TASK, ORDER, UNIMPORTANT
    }

    private final String feedbackToUser;

    private final DisplayState displayState;

    /** Total orders should be shown to the user. */
    private final boolean totalOrders;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, DisplayState displayState, boolean totalOrders,
                         boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.displayState = requireNonNull(displayState);
        this.totalOrders = totalOrders;
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, DisplayState displayState) {
        this(feedbackToUser, displayState, false, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public DisplayState getState() {
        return displayState;
    }

    public boolean isShowTotalOrders() {
        return totalOrders;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
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
                && totalOrders == otherCommandResult.totalOrders
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, totalOrders, showHelp, exit);
    }

}
