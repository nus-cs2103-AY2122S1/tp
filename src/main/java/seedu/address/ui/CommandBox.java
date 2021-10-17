package seedu.address.ui;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {
    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private final ArrayList<String> commandHistory;
    private Index index;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);

        this.commandExecutor = commandExecutor;
        commandHistory = new ArrayList<>();

        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());

        commandTextField.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
            case UP:
                showPreviousCommand();
                break;

            case DOWN:
                showNextCommand();
                break;

            default: // Do nothing
            }
        });
    }

    /**
     * Displays the next command in command history, with respect to the command displayed currently. <br><br>
     * This function will just return when the command history is empty.
     * When the command displayed currently is the last command in the history, invoking this method will clear the
     * value in commandTextField. Immediate invocation of this function, just after the value in commandTextField
     * cleared due to the reason stated previously, will just return.
     */
    private void showNextCommand() {
        if (index == null) {
            return;
        } else if (index.getOneBased() == commandHistory.size()) {
            index = Index.fromOneBased(index.getOneBased() + 1);
            commandTextField.setText("");
            return;
        } else if (index.getOneBased() > commandHistory.size()) {
            return;
        }

        index = Index.fromOneBased(index.getOneBased() + 1);
        commandTextField.setText(commandHistory.get(index.getZeroBased()));
    }

    /**
     * Displays the previous command in command history, with respect to the command displayed currently. <br><br>
     * This function will just return when the command history is empty; or when the command displayed currently is the
     * first command in the history.
     */
    private void showPreviousCommand() {
        if (index == null || index.getOneBased() <= 1) {
            return;
        }

        index = Index.fromOneBased(index.getOneBased() - 1);
        commandTextField.setText(commandHistory.get(index.getZeroBased()));
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getText();
        if (commandText.equals("")) {
            return;
        }

        // Store the command, even if invalid, and reset the index pointer to the end
        commandHistory.add(commandText);
        index = Index.fromOneBased(commandHistory.size());

        try {
            commandExecutor.execute(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();
        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.address.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }
}
