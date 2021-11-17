package socialite.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Check if the command falls under the following categories. */
    private final boolean isHelpCommand;
    private final boolean isExitCommand;
    private final boolean isPictureCommand;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(
            String feedbackToUser, boolean isHelpCommand, boolean isExitCommand, boolean isPictureCommand
    ) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.isHelpCommand = isHelpCommand;
        this.isExitCommand = isExitCommand;
        this.isPictureCommand = isPictureCommand;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isHelpCommand() {
        return isHelpCommand;
    }

    public boolean isExitCommand() {
        return isExitCommand;
    }

    public boolean isPictureCommand() {
        return this.isPictureCommand;
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
                && isHelpCommand == otherCommandResult.isHelpCommand
                && isExitCommand == otherCommandResult.isExitCommand;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, isHelpCommand, isExitCommand);
    }

}
