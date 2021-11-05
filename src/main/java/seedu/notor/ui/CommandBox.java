package seedu.notor.ui;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.commands.exceptions.CommandException;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.parser.exceptions.ParseException;




/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";
    private final CommandHistory commandHistory;


    private final CommandExecutor commandExecutor;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandHistory = new CommandHistory();
        this.commandExecutor = commandExecutor;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
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

        try {
            commandExecutor.execute(commandText);
            commandHistory.addCommandText(commandText);
            commandHistory.resetIndex();
            commandTextField.setText("");
        } catch (CommandException | ParseException | ExecuteException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Handles UP and DOWN key input.
     * @param event Key input.
     */
    @FXML
    public void handleOnKeyPressed(KeyEvent event) {
        KeyCode keyCode = event.getCode();
        if (keyCode.equals(KeyCode.UP)) {
            commandHistory.incrementIndex();
        } else if (keyCode == KeyCode.DOWN) {
            commandHistory.decrementIndex();
        } else {
            return;
        }
        commandTextField.setText(commandHistory.getCommandText(commandTextField.getText()));
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
         * @see seedu.notor.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException, ExecuteException;
    }

    private static class CommandHistory {
        private int index = 0;
        private final ArrayList<String> commandHistory;

        CommandHistory() {
            this.commandHistory = new ArrayList<>();
        }

        void addCommandText(String commandText) {
            commandHistory.add(commandText);
        }

        private int historyLength() {
            return commandHistory.size();
        }

        String getCommandText(String curCommandText) {
            if (index > 0) {
                return commandHistory.get(historyLength() - index);
            } else if (index == 0 && historyLength() > 0) {
                return "";
            } else {
                return curCommandText;
            }
        }

        /**
         * Increments the current index by 1 if the current index is less than current commandHistory size.
         */
        void incrementIndex() {
            index = min(index + 1, historyLength());
        }

        /**
         * Decrements the index by 1 of commandHistory if the current index is more than 0.
         */
        void decrementIndex() {
            index = max(index - 1, 0);
        }

        void resetIndex() {
            index = 0;
        }
    }

}
