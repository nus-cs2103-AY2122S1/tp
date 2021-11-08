package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Contact;
import seedu.address.ui.util.InputHistory;


/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private final InputHistory inputHistory;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        inputHistory = InputHistory.getInstance();
    }

    /**
     * Handles up and down arrow button pressed event.
     * @param key The key that is pressed when CommandBox is active.
     */
    @FXML
    private void handleKeyPress(KeyEvent key) {
        KeyCode keycode = key.getCode();
        switch(keycode) {
        case UP:
            String recentInput = inputHistory.getPreviousInput();
            setTextAndCaret(recentInput);
            break;
        case DOWN:
            String nextInput = inputHistory.getNextInput();
            setTextAndCaret(nextInput);
            break;
        default:
            break;
        }
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
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        } finally {
            String text = commandTextField.getText();
            inputHistory.addToHistory(text);
            commandTextField.setText("");
        }
    }

    /**
     * Handles the View Selection.
     */
    public void handleViewSelected(MultipleSelectionModel<Contact> selectedContactModel) {
        int index = selectedContactModel.getSelectedIndices().get(0);
        int indexOneOff = index + 1;
        String commandText = ViewCommand.COMMAND_WORD + " " + indexOneOff;

        try {
            commandExecutor.execute(commandText);
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
     * Sets the text of the command box to the given string and moves the caret to after the last char of the text.
     * @param text String to be shown on command box.
     */
    private void setTextAndCaret(String text) {
        commandTextField.setText(text);
        commandTextField.end();
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
