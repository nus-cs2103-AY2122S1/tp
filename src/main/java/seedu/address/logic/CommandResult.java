package seedu.address.logic;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;
    private final CommandType commandType;

    public enum CommandType {
        HELP,
        EXIT,
        GENERAL,
        LIST_C,
        LIST_P,
        LIST_I,
        FIND_C,
        FIND_P,
        FIND_I,
        CANDIDATE,
        POSITION,
        INTERVIEW,
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, CommandType commandType) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.commandType = commandType;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public CommandType getCommandType() {
        return commandType;
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
                && commandType.equals(otherCommandResult.commandType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, commandType);
    }

}
