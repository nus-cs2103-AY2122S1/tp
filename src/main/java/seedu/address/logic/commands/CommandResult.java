package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    /**
     * Special Commands that UI has to treat in different ways.
     */
    public enum RESULT_TYPE {
        NORMAL,
        SHOW_HELP,
        EXIT,
        EXPORT_CSV
    }
    private final String feedbackToUser;
    private final RESULT_TYPE resultType;

    /**
     * Constructs a {@code CommandResult} with the specified specialType.
     */
    public CommandResult (String feedbackToUser, RESULT_TYPE specialType) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.resultType = specialType;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean exportCsv) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        if (showHelp) {
            this.resultType = RESULT_TYPE.SHOW_HELP;
        } else if (exit) {
            this.resultType = RESULT_TYPE.EXIT;
        } else if (exportCsv) {
            this.resultType = RESULT_TYPE.EXPORT_CSV;
        } else {
            this.resultType = RESULT_TYPE.NORMAL;
        }
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, showHelp, exit, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, RESULT_TYPE.NORMAL);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return resultType.equals(RESULT_TYPE.SHOW_HELP);
    }

    public boolean isExit() {
        return resultType.equals(RESULT_TYPE.EXIT);
    }

    public boolean isChooseFile() {
        return resultType.equals(RESULT_TYPE.EXPORT_CSV);
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
                && resultType.equals(otherCommandResult.resultType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, resultType);
    }
}
