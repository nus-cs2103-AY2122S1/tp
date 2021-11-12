package seedu.academydirectory.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import seedu.academydirectory.commons.core.LogsCenter;
import seedu.academydirectory.logic.commands.HelpCommand;
import seedu.academydirectory.logic.commands.HistoryCommand;
import seedu.academydirectory.logic.commands.RedoCommand;
import seedu.academydirectory.logic.commands.ShowCommand;
import seedu.academydirectory.logic.commands.UndoCommand;
import seedu.academydirectory.logic.commands.VisualizeCommand;
import seedu.academydirectory.logic.commands.exceptions.CommandException;
import seedu.academydirectory.logic.parser.exceptions.ParseException;

public class AppMenu extends UiPart<Region> {
    public static final String FXML = "Menu.fxml";

    private final Logger logger = LogsCenter.getLogger(AppMenu.class);

    private final CommandExecutor commandExecutor;

    /**
     * Create an application Menu for Academy Directory
     * @param commandExecutor command executor
     */
    public AppMenu(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
    }

    private void handleButtons(String equivalentCommandText) {
        try {
            commandExecutor.execute(equivalentCommandText);
        } catch (ParseException | CommandException e) {
            logger.info("Button does not do anything");
        }
    }

    /**
     * A menu item that quickly executes the equivalent command `show RA1`
     */
    @FXML
    public void showRA1() {
        logger.info("Menu for Show RA1 clicked");
        handleButtons(ShowCommand.COMMAND_WORD + " RA1");
    }

    /**
     * A menu item that quickly executes the equivalent command `show RA2`
     */
    @FXML
    public void showRA2() {
        logger.info("Menu for Show RA2 clicked");
        handleButtons(ShowCommand.COMMAND_WORD + " RA2");
    }

    /**
     * A menu item that quickly executes the equivalent command `show MIDTERM`
     */
    @FXML
    public void showMidterm() {
        logger.info("Menu for Show Midterm clicked");
        handleButtons(ShowCommand.COMMAND_WORD + " MIDTERM");
    }

    /**
     * A menu item that quickly executes the equivalent command `show PE`
     */
    @FXML
    public void showPE() {
        logger.info("Menu for Show PE clicked");
        handleButtons(ShowCommand.COMMAND_WORD + " PE");
    }

    /**
     * A menu item that quickly executes the equivalent command `show FINAL`
     */
    @FXML
    public void showFinal() {
        logger.info("Menu for Show Final clicked");
        handleButtons(ShowCommand.COMMAND_WORD + " FINAL");
    }

    /**
     * A menu item that quickly executes the equivalent command `visualize`
     */
    @FXML
    public void showStatistics() {
        logger.info("Menu for Show statistics clicked");
        handleButtons(VisualizeCommand.COMMAND_WORD);
    }

    /**
     * A menu item that quickly executes the equivalent command `history`
     */
    @FXML
    public void showHistory() {
        logger.info("Menu for Show history clicked");
        handleButtons(HistoryCommand.COMMAND_WORD);
    }

    /**
     * A menu item that quickly executes the equivalent command `undo`
     */
    @FXML
    public void undo() {
        logger.info("Menu for undo changes clicked");
        handleButtons(UndoCommand.COMMAND_WORD);
    }

    /**
     * A menu item that quickly executes the equivalent command `redo`
     */
    @FXML
    public void redo() {
        logger.info("Menu for redo clicked");
        handleButtons(RedoCommand.COMMAND_WORD);
    }

    /**
     * A menu item that quickly executes the equivalent command `help`
     */
    @FXML
    public void showHelp() {
        logger.info("Menu for Show help clicked");
        handleButtons(HelpCommand.COMMAND_WORD);
    }
}
