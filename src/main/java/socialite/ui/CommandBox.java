package socialite.ui;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import socialite.logic.Logic;
import socialite.logic.commands.CommandResult;
import socialite.logic.commands.exceptions.CommandException;
import socialite.logic.parser.exceptions.ParseException;
import socialite.model.ReadOnlyCommandHistory;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private final List<String> commandHistory;
    private int historyIdx;
    private String cachedCommand = "";


    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor} and given {@Code ReadOnlyCommandHistory}.
     */
    public CommandBox(CommandExecutor commandExecutor, ReadOnlyCommandHistory commandHistory) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        this.commandHistory = commandHistory.getCommandHistory();
        historyIdx = this.commandHistory.size();
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
        historyIdx = commandHistory.size() + 1;

        try {
            commandExecutor.execute(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Handles the up/down key arrow pressed event.
     */
    @FXML
    private void handleCommandKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.UP) {
            commandTextField.setText(getPreviousCommand());
            commandTextField.positionCaret(commandTextField.getLength());
        }

        if (event.getCode() == KeyCode.DOWN) {
            commandTextField.setText(getNextCommand());
            commandTextField.positionCaret(commandTextField.getLength());
        }

    }

    private String getPreviousCommand() {
        if (historyIdx == commandHistory.size()) {
            cachedCommand = commandTextField.getText();
        }
        historyIdx = Math.max(historyIdx - 1, 0);
        return commandHistory.get(historyIdx);
    }

    private String getNextCommand() {
        try {
            historyIdx = Math.min(historyIdx + 1, commandHistory.size());
            return commandHistory.get(historyIdx);
        } catch (IndexOutOfBoundsException e) {
            return cachedCommand;
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
         * @see Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
