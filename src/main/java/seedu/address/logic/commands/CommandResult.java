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
    public enum ResultType {
        NORMAL,
        SHOW_HELP,
        EXIT,
        EXPORT_CSV
    }
    private final String feedbackToUser;
    private final ResultType resultType;

    /**
     * Constructs a {@code CommandResult} with the specified specialType.
     *
     * @param feedbackToUser Message that is shown to user.
     * @param specialType The type of message.
     */
    public CommandResult (String feedbackToUser, ResultType specialType) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.resultType = specialType;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     *
     * @param feedbackToUser Message that is shown to user.
     * @param showHelp boolean indicating if user indicated for help.
     * @param exit boolean indicating if user indicated an exit.
     * @param exportCsv boolean indicating if user indicating to export contacts to Csv.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean exportCsv) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        if (showHelp) {
            this.resultType = ResultType.SHOW_HELP;
        } else if (exit) {
            this.resultType = ResultType.EXIT;
        } else if (exportCsv) {
            this.resultType = ResultType.EXPORT_CSV;
        } else {
            this.resultType = ResultType.NORMAL;
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
        this(feedbackToUser, ResultType.NORMAL);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    /**
     * Checks if {@code this} is a {@code ResultType.SHOW_HELP}.
     *
     * @return if {@code this} is a {@code ResultType.SHOW_HELP}.
     */
    public boolean isShowHelp() {
        return resultType.equals(ResultType.SHOW_HELP);
    }

    /**
     * Checks if {@code this} is a {@code ResultType.EXIT}.
     *
     * @return if {@code this} is a {@code ResultType.EXIT}.
     */
    public boolean isExit() {
        return resultType.equals(ResultType.EXIT);
    }

    /**
     * Checks if {@code this} is a {@code ResultType.EXPORT_CSV}.
     *
     * @return if {@code this} is a {@code ResultType.EXPORT_CSV}.
     */
    public boolean isChooseFile() {
        return resultType.equals(ResultType.EXPORT_CSV);
    }

    /**
     * Checks if {@code other} is equal to {@code this}.
     *
     * @param other the object to check if it is equal to {@code this}.
     * @return {@code boolean} indicating if it is equal.
     */
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
