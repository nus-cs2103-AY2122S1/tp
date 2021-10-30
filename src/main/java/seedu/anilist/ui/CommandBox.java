package seedu.anilist.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.anilist.logic.commands.CommandResult;
import seedu.anilist.logic.commands.exceptions.CommandException;
import seedu.anilist.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    public static final String PROMPT_TEXT_COMMAND_BOX_ENABLED = "Enter command here...";
    public static final String PROMPT_TEXT_COMMAND_BOX_DISABLED = "Command box disabled — viewing user statistics";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private final AnimeListPanel animeListPanel;
    private boolean executionState = false;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor, AnimeListPanel animeListPanel) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.animeListPanel = animeListPanel;
        executionState = false;
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
            executionState = true;
            commandTextField.setText("");
            animeListPanel.setActiveTab();
            executionState = false;
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Disables the command text field
     */
    public void disableCommandTextField() {
        commandTextField.setDisable(true);
        commandTextField.setPromptText(PROMPT_TEXT_COMMAND_BOX_DISABLED);
    }

    /**
     * Enables the command text field
     */
    public void enableCommandTextField() {
        commandTextField.setDisable(false);
        commandTextField.setPromptText(PROMPT_TEXT_COMMAND_BOX_ENABLED);
    }

    public boolean isExecuting() {
        return executionState;
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
         * @see seedu.anilist.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
