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
    private final boolean isClientCommand;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, CommandType commandType, Category info, boolean isClientCommand) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.commandType = commandType;
        this.info = info;
        this.isClientCommand = isClientCommand;
    }

    public CommandResult(String feedbackToUser, CommandType commandType) {
        this(feedbackToUser, commandType, null, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, null, null, false);
    }

    /**
     * Constructs a {@code CommandResult} by copying from the provided {@code copyFrom}.
     */
    public CommandResult(CommandResult copyFrom) {
        this(copyFrom.feedbackToUser, copyFrom.commandType, copyFrom.info, copyFrom.isClientCommand);
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

    public boolean getIsClientCommand() {
        return isClientCommand;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof CommandResult)) {
            return false;
        }

        CommandResult other = (CommandResult) obj;
        return feedbackToUser.equals(other.feedbackToUser)
                && commandType == other.commandType
                && Objects.equals(info, other.info)
                && isClientCommand == other.isClientCommand;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, commandType, info, isClientCommand);
    }
}
