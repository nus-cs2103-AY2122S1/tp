package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;
    private final String additionalText;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** The command issued is to show a single person's task list */
    private boolean displaySingleTaskList;

    /** The command issued is to show every person's task list */
    private boolean displayAllTaskList;

    /** The command issued modifies/writes the application's data */
    private boolean writeCommand;

    /** The application should change the text in CommandBox. */
    private final boolean changeCommandBox;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     *
     * @param feedbackToUser The feedback given by the program to the user.
     * @param showHelp The boolean that determines whether the {@code HelpWindow} is shown.
     * @param exit The boolean that determines whether the program should exit.
     * @param changeCommandBox The boolean that determines whether the commandBox should be emptied.
     * @param additionalText Extra information to show to the user.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean changeCommandBox,
                         String additionalText) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.changeCommandBox = changeCommandBox;
        this.additionalText = additionalText;
    }

    /**
     * Constructs a {@code CommandResult} without changeCommandBox.
     *
     * @param feedbackToUser The feedback given by the program to the user.
     * @param showHelp The boolean that determines whether the {@code HelpWindow} is shown.
     * @param exit The boolean that determines whether the program should exit.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.displayAllTaskList = false;
        this.displaySingleTaskList = false;
        this.writeCommand = false;
        this.changeCommandBox = false;
        this.additionalText = "";
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     *
     * @param feedbackToUser The feedback given by the program to the user.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    public void setDisplayAllTaskList() {
        this.displayAllTaskList = true;
        this.displaySingleTaskList = false;
    }

    public void setDisplaySingleTaskList() {
        this.displaySingleTaskList = true;
        this.displayAllTaskList = false;
    }

    public void setWriteCommand() {
        writeCommand = true;
    }

    public boolean isDisplayAllTaskList() {
        return displayAllTaskList;
    }

    public boolean isDisplaySingleTaskList() {
        return displaySingleTaskList;
    }

    public boolean isWriteCommand() {
        return writeCommand;
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

    public boolean isChangeCommandBox() {
        return changeCommandBox;
    }

    public String getAdditionalText() {
        return additionalText;
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
                && changeCommandBox == otherCommandResult.changeCommandBox
                && additionalText.equals(otherCommandResult.additionalText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, changeCommandBox, additionalText);
    }

}
