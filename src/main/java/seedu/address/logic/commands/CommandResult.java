package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.person.student.Student;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** Medical information should be shown to the user. */
    private final boolean showMedical;

    /** The application should exit. */
    private final boolean exit;

    /** The student to show medical history. */
    private Student student;

    /** Meeting window should be shown to the user. */
    private final boolean showMeeting;

    /** Meeting window should exit, and the main window should be shown to the user. */
    private final boolean quitMeeting;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean showMedical,
            boolean showMeeting, boolean quitMeeting) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.showMedical = showMedical;
        this.showMeeting = showMeeting;
        this.quitMeeting = quitMeeting;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean showMedical, Student student,
            boolean showMeeting, boolean quitMeeting) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.showMedical = showMedical;
        this.student = student;
        this.showMeeting = showMeeting;
        this.quitMeeting = quitMeeting;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false, false);
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

    public boolean isShowMedical() {
        return showMedical;
    }

    public Student getStudent() {
        return student;
    }

    public boolean isShowMeeting() {
        return showMeeting;
    }

    public boolean isQuitMeeting() {
        return quitMeeting;
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
                && exit == otherCommandResult.exit
                && showMedical == otherCommandResult.showMedical
                && student == otherCommandResult.student
                && showMeeting == otherCommandResult.showMeeting
                && quitMeeting == otherCommandResult.quitMeeting;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, showMedical, student, showMeeting,
                quitMeeting);
    }

}
