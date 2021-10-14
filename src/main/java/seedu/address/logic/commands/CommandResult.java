package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.person.Person;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** Schedule should be shown to the user. */
    private final boolean showSchedule;

    /** The application should exit. */
    private final boolean exit;

    /** Lesson information of student should be shown to the user. */
    private final Person student;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    private CommandResult(String feedbackToUser, boolean showHelp, boolean showSchedule, boolean exit, Person student) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.showSchedule = showSchedule;
        this.exit = exit;
        this.student = student;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean showSchedule, boolean exit) {
        this(feedbackToUser, showHelp, showSchedule, exit, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, Person student) {
        this(feedbackToUser, false, false, false, student);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isShowSchedule() {
        return showSchedule;
    }

    public boolean isDisplayStudent() {
        return student != null;
    }

    public Person getStudent() {
        return student;
    }

    public boolean isShowStudentList() {
        return !showSchedule;
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
                && showSchedule == otherCommandResult.showSchedule
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, showSchedule, exit);
    }

    @Override
    public String toString() {
        return "CommandResult: feedbackToUser = " + feedbackToUser + '\'' + ", showHelp = " + showHelp
                + ", exit = " + exit;
    }
}
