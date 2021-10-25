package safeforhall.ui;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import safeforhall.logic.Logic;
import safeforhall.logic.commands.ClearCommand;
import safeforhall.logic.commands.Command;
import safeforhall.logic.commands.CommandResult;
import safeforhall.logic.commands.ExitCommand;
import safeforhall.logic.commands.FindCommand;
import safeforhall.logic.commands.HelpCommand;
import safeforhall.logic.commands.IncludeCommand;
import safeforhall.logic.commands.ListCommand;
import safeforhall.logic.commands.add.AddEventCommand;
import safeforhall.logic.commands.add.AddPersonCommand;
import safeforhall.logic.commands.delete.DeleteEventCommand;
import safeforhall.logic.commands.delete.DeletePersonCommand;
import safeforhall.logic.commands.edit.EditEventCommand;
import safeforhall.logic.commands.edit.EditPersonCommand;
import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.logic.commands.view.ViewEventCommand;
import safeforhall.logic.commands.view.ViewPersonCommand;
import safeforhall.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;

    private ArrayList<String> historicals = new ArrayList<>();
    private int current = 0;
    private String currentString = "";

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

        handleInput();
        handleHistory();

    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getText();
        historicals.add(commandText);
        current = historicals.size() - 1;
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
     * Handles the up and down key events to re-instate past commands.
     */
    private void handleHistory() {
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (historicals.isEmpty() || !(event.getTarget() instanceof TextField)) {
                return;
            }

            // Simplified if statement not used to allow other key pressed to propagate
            if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN) {
                String newText;
                if (event.getCode() == KeyCode.UP) {
                    if (current > 0) {
                        current--;
                    }
                    newText = historicals.get(current);
                } else {
                    if (current < historicals.size() - 1) {
                        current++;
                        newText = historicals.get(current);
                    } else {
                        current = historicals.size();
                        newText = "";
                    }
                }
                event.consume();
                commandTextField.setText(newText);
                commandTextField.end();
            }
        });
    }

    private void handleInput() {
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().isLetterKey() || event.getCode().isDigitKey()) {
                currentString += event.getText();
                event.consume();
            } else if (event.getCode() == KeyCode.BACK_SPACE && currentString.length() > 0) {
                currentString = currentString.substring(0, currentString.length() - 1);
            }
        });
        getRoot().addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (event.getCharacter().matches("^[a-zA-Z0-9_]*$")) {
                event.consume();
            }
        });
        getRoot().addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            String suggestion = mapSuggestion(currentString, true);
            if (!suggestion.equals("")) {
                String output = currentString + " " + suggestion;
                commandTextField.setText(output);
            } else {
                commandTextField.setText(currentString);
            }
            commandTextField.positionCaret(currentString.length());
            event.consume();
        });

    }

    private String mapSuggestion(String currentString, boolean isResidentTab) {
        if (isResidentTab) {
            switch (currentString) {
            case AddPersonCommand.COMMAND_WORD:
                return AddPersonCommand.PARAMETERS;
            case DeletePersonCommand.COMMAND_WORD:
                return DeletePersonCommand.PARAMETERS;
            case EditPersonCommand.COMMAND_WORD:
                return EditPersonCommand.PARAMETERS;
            case ViewPersonCommand.COMMAND_WORD:
                return ViewPersonCommand.PARAMETERS;
            case ClearCommand.COMMAND_WORD:
                return ClearCommand.PARAMETERS;
            case ExitCommand.COMMAND_WORD:
                return ExitCommand.PARAMETERS;
            case FindCommand.COMMAND_WORD:
                return FindCommand.PARAMETERS;
            case HelpCommand.COMMAND_WORD:
                return HelpCommand.PARAMETERS;
            case IncludeCommand.COMMAND_WORD:
                return IncludeCommand.PARAMETERS;
            case ListCommand.COMMAND_WORD:
                return ListCommand.PARAMETERS;
            default:
                return "";
            }
        } else {
            switch (currentString) {
            case AddEventCommand.COMMAND_WORD:
                return AddEventCommand.PARAMETERS;
            case DeleteEventCommand.COMMAND_WORD:
                return DeleteEventCommand.PARAMETERS;
            case EditEventCommand.COMMAND_WORD:
                return EditEventCommand.PARAMETERS;
            case ViewEventCommand.COMMAND_WORD:
                return ViewEventCommand.PARAMETERS;
            case ClearCommand.COMMAND_WORD:
                return ClearCommand.PARAMETERS;
            case ExitCommand.COMMAND_WORD:
                return ExitCommand.PARAMETERS;
            case FindCommand.COMMAND_WORD:
                return FindCommand.PARAMETERS;
            case HelpCommand.COMMAND_WORD:
                return HelpCommand.PARAMETERS;
            case IncludeCommand.COMMAND_WORD:
                return IncludeCommand.PARAMETERS;
            case ListCommand.COMMAND_WORD:
                return ListCommand.PARAMETERS;
            default:
                return "";
            }
        }
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see Logic#execute(String, Boolean)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
