package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

import seedu.address.model.person.Person;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean isShowHelp;

    /** Schedule should be shown to the user. */
    private final boolean isShowSchedule;

    /** Reminder of upcoming lessons should be shown to the user. */
    private final boolean isShowReminder;

    /** The application should exit. */
    private final boolean isExit;

    /** Lesson information of student should be shown to the user. */
    private final Person student;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    private CommandResult(String feedbackToUser, boolean isShowHelp, boolean isShowSchedule,
                         boolean isShowReminder, boolean isExit, Person student) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.isShowHelp = isShowHelp;
        this.isShowSchedule = isShowSchedule;
        this.isShowReminder = isShowReminder;
        this.isExit = isExit;
        this.student = student;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean isShowHelp, boolean isShowSchedule, boolean isShowReminder,
                         boolean isExit) {
        this(feedbackToUser, isShowHelp, isShowSchedule, isShowReminder, isExit, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code student},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, Person student) {
        this(feedbackToUser, false, false, false, false, student);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return isShowHelp;
    }

    public boolean isShowSchedule() {
        return isShowSchedule;
    }

    public boolean isShowStudentList() {
        return !isShowSchedule;
    }

    public boolean isShowReminder() {
        return isShowReminder;
    }

    public Optional<Person> getStudent() {
        return Optional.ofNullable(student);
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
                && isShowHelp == otherCommandResult.isShowHelp
                && isShowSchedule == otherCommandResult.isShowSchedule
                && isShowReminder == otherCommandResult.isShowReminder
                && isExit == otherCommandResult.isExit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, isShowHelp, isShowSchedule, isShowReminder, isExit);
    }

    @Override
    public String toString() {
        return "CommandResult: feedbackToUser = " + feedbackToUser + '\'' + ", showHelp = " + isShowHelp
                + ", exit = " + isExit;
    }
}
