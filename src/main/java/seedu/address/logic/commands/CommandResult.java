package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.contact.Contact;
import seedu.address.model.summary.Summary;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** Command Summary should be shown to the user */
    private final boolean showCommandSummary;

    /** The application should update display panel. */
    private final boolean display;

    private final boolean displaySummary;

    private Contact contactToDisplay;

    private Summary summary;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showCommandSummary, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showCommandSummary = showCommandSummary;
        this.showHelp = showHelp;
        this.exit = exit;
        this.display = false;
        this.displaySummary = false;
        this.summary = null;
        this.contactToDisplay = null;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, false, showHelp, exit);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     *  the specified {@code contactToDisplay} and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, Contact contactToDisplay) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.display = true;
        this.displaySummary = false;
        this.summary = null;
        this.contactToDisplay = contactToDisplay;
        this.showCommandSummary = false;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     *  the specified {@code Summary} and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, Summary summary) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.display = false;
        this.displaySummary = true;
        this.summary = summary;
        this.showCommandSummary = false;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowCommandSummary() {
        return showCommandSummary;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isDisplayContact() {
        return display;
    }

    public boolean isDisplaySummary() {
        return displaySummary;
    }

    public Contact getContactToDisplay() {
        return contactToDisplay;
    }

    public Summary getSummaryToDisplay() {
        return summary;
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

        boolean isSummary = !displaySummary || !otherCommandResult.displaySummary;

        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showCommandSummary == otherCommandResult.showCommandSummary
                && (isSummary || summary.equals(otherCommandResult.summary))
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showCommandSummary, showHelp, exit);
    }

}
