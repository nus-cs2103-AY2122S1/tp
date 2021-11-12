package teletubbies.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import teletubbies.commons.exceptions.DataConversionException;
import teletubbies.logic.commands.exceptions.CommandException;
import teletubbies.logic.commands.uieffects.UiConsumer;
import teletubbies.ui.MainWindow;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    /**
     * UiEffects that commands can have on the UI
     */
    public enum UiEffect {
        SHOW_HELP, // Help information should be shown to the user.
        EXIT, // The application should exit.
        EXPORT, // The application should export data
        IMPORT, // The application should import data
        MERGE, //The application should merge data
        NONE
    }
    private final UiConsumer uiConsumer;
    private final UiEffect uiEffect;
    private final String feedbackToUser;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     *
     * @param feedbackToUser String feedback to be displayed to user
     * @param uiEffect type of uiEffect from command
     * @param uiConsumer consumer of MainWindow for command to interact
     *                   with ui
     */
    public CommandResult(String feedbackToUser, UiEffect uiEffect, UiConsumer uiConsumer) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.uiEffect = uiEffect;
        this.uiConsumer = uiConsumer;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, UiEffect uiEffect) {
        this(feedbackToUser, uiEffect, mainWindow -> {});
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, UiEffect.NONE);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    /**
     * Execute uiConsumer if result has a uiEffect
     *
     * @param mainWindow reference to app's ui window
     */
    public void executeUiEffect(MainWindow mainWindow) throws CommandException {
        if (!uiEffect.equals(UiEffect.NONE)) {
            try {
                this.uiConsumer.accept(mainWindow);
            } catch (DataConversionException e) {
                throw new CommandException(ImportCommand.MESSAGE_INCORRECT_FORMAT);
            }
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
                && uiEffect.equals(otherCommandResult.uiEffect);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, uiEffect);
    }

}
