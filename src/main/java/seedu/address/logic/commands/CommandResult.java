package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    public static final String WARNING_FORMAT = "[WARNING] %1$s\n";

    private final String feedbackToUser;

    /** Summary statistics should be shown to the user. */
    private final boolean showSummary;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** Download information should be shown to the user. */
    private final boolean showDownload;

    /** The application should exit. */
    private final boolean exit;

    private final Optional<CommandWarning> warning;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * Overloaded method where by default the result is normal
     */
    public CommandResult(String feedbackToUser, boolean showSummary, boolean showHelp,
                         boolean showDownload, boolean exit) {
        this(feedbackToUser, showSummary, showHelp, showDownload, exit, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showSummary, boolean showHelp,
                         boolean showDownload, boolean exit, CommandWarning warning) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showSummary = showSummary;
        this.showHelp = showHelp;
        this.showDownload = showDownload;
        this.exit = exit;
        this.warning = (warning != null) ? Optional.of(warning) : Optional.empty();
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}, and warning
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, CommandWarning warning) {
        this(feedbackToUser, false, false, false, false, warning);
    }

    public String getFeedbackToUser() {
        if (hasWarning()) {
            return String.format(WARNING_FORMAT, getWarning()) + feedbackToUser;
        } else {
            return feedbackToUser;
        }
    }

    public boolean isShowSummary() {
        return showSummary;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isShowDownload() {
        return showDownload;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean hasWarning() {
        return !warning.isEmpty();
    }

    public String getWarning() {
        if (hasWarning()) {
            return warning.get().getValue();
        } else {
            return "";
        }
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
                && showSummary == otherCommandResult.showSummary
                && showHelp == otherCommandResult.showHelp
                && showDownload == otherCommandResult.showDownload
                && exit == otherCommandResult.exit
                && warning == otherCommandResult.warning;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showSummary, showHelp, showDownload, exit, warning);
    }

}
