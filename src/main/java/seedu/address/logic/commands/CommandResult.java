package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.Category;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    private final CommandType commandType;

    private final Category info;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, CommandType commandType, Category info) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.commandType = commandType;
        this.info = info;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, CommandType commandType) {
        this(feedbackToUser, commandType, null);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public Category getInfo() {
        return info;
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
                && commandType == otherCommandResult.commandType
                && info == otherCommandResult.info;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, commandType, info);
    }

}
