package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean isShowHelp;

    /** The application should exit. */
    private final boolean isExit;

    /** The application should open the current user's Telegram **/
    private final boolean isTelegram;

    /** The application should open the current user's GitHub profile **/
    private final boolean isGithub;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser,
                         boolean isShowHelp, boolean isExit, boolean isTelegram, boolean isGithub) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.isShowHelp = isShowHelp;
        this.isExit = isExit;
        this.isTelegram = isTelegram;
        this.isGithub = isGithub;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     *
     * @param feedbackToUser is the feedback to give to the user.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false);
    }

    /**
     * Returns the feedback that is to be given to the user.
     *
     * @return feedbackToUser
     */
    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return isShowHelp;
    }

    public boolean isExit() {
        return isExit;
    }

    public boolean isTelegram() {
        return isTelegram;
    }

    public boolean isGithub() {
        return isGithub;
    }

    /**
     * Method to compare two CommandResult objects.
     *
     * @param other is the object that is going to be compared
     *              to the CommandResult object that called this method.
     * @return boolean representation of whether the CommandResult
     * object is equal to the other object passed as parameter.
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
                && isShowHelp == otherCommandResult.isShowHelp
                && isExit == otherCommandResult.isExit
                && isTelegram == otherCommandResult.isTelegram
                && isGithub == otherCommandResult.isGithub;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, isShowHelp, isExit, isTelegram, isGithub);
    }

}
