package dash.ui;

import java.util.ArrayList;

import dash.logic.Logic;
import dash.logic.commands.CommandResult;
import dash.logic.commands.exceptions.CommandException;
import dash.logic.parser.exceptions.ParseException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;

    /**
     * A list of Strings to set in commandTextField.
     * The list is padded with an empty String at index 0.
     * For the rest of the list, the String at index n is the nth most recent user input.
     * e.g. String at index 3 is the 3rd most recent user input.
     */
    private final ArrayList<String> paddedUserInputList;

    /**
     * The position of the current text set in the command box in the user input list.
     * When this is negative, then no input is set.
     */
    private int currentUserInputIndex = 0;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor},
     * as well as the given user input list.
     */
    public CommandBox(CommandExecutor commandExecutor, ArrayList<String> userInputList) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        userInputList.add(0, "");
        this.paddedUserInputList = userInputList;

        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());

        commandTextField.setOnKeyPressed(event -> handleUpOrDownArrowKeyPressed(event.getCode()));
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        currentUserInputIndex = 0;

        String commandText = commandTextField.getText();
        if (commandText.equals("")) {
            return;
        }

        try {
            commandExecutor.execute(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Handles the Up/Down arrow key pressed event.
     */
    @FXML
    public void handleUpOrDownArrowKeyPressed(KeyCode keyCode) {
        if (keyCode == KeyCode.DOWN) {
            if (currentUserInputIndex == 0) {
                return;
            }
            currentUserInputIndex--;
            commandTextField.setText(paddedUserInputList.get(currentUserInputIndex));
            commandTextField.end();
        }

        if (keyCode == KeyCode.UP) {
            if (currentUserInputIndex == paddedUserInputList.size() - 1) {
                commandTextField.end();
                return;
            }
            currentUserInputIndex++;
            commandTextField.setText(paddedUserInputList.get(currentUserInputIndex));
            commandTextField.end();
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
