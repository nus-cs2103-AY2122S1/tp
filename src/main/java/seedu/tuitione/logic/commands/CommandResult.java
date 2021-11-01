package seedu.tuitione.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.commons.core.Messages.HEADER_ALERT;
import static seedu.tuitione.commons.core.Messages.HEADER_ERROR;
import static seedu.tuitione.commons.core.Messages.HEADER_SUCCESS;
import static seedu.tuitione.commons.core.Messages.HEADER_UPDATE;

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

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    /**
     * Provides a {@code CommandResult} with the alert header template in its message.
     */
    public static CommandResult createAlertResult(String feedbackToUser) {
        return new CommandResult(HEADER_ALERT + feedbackToUser);
    }

    /**
     * Provides a {@code CommandResult} with the error header template in its message.
     */
    public static CommandResult createErrorResult(String feedbackToUser) {
        return new CommandResult(HEADER_ERROR + feedbackToUser);
    }

    /**
     * Provides a {@code CommandResult} with the success header template in its message.
     */
    public static CommandResult createSuccessResult(String feedbackToUser) {
        return new CommandResult(HEADER_SUCCESS + feedbackToUser);
    }

    /**
     * Provides a {@code CommandResult} with the update header template in its message.
     */
    public static CommandResult createUpdateResult(String feedbackToUser) {
        return new CommandResult(HEADER_UPDATE + feedbackToUser);
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
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

    @Override
    public String toString() {
        return "CommandResult{"
                + "feedbackToUser='" + feedbackToUser + '\''
                + '}';
    }
}
