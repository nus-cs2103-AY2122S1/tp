package seedu.academydirectory.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import seedu.academydirectory.commons.core.LogsCenter;
import seedu.academydirectory.logic.commands.ExitCommand;
import seedu.academydirectory.logic.commands.HelpCommand;
import seedu.academydirectory.logic.commands.HistoryCommand;
import seedu.academydirectory.logic.commands.RedoCommand;
import seedu.academydirectory.logic.commands.ShowCommand;
import seedu.academydirectory.logic.commands.UndoCommand;
import seedu.academydirectory.logic.commands.VisualizeCommand;

public class AppMenu extends UiPart<Region> {
    public static final String FXML = "Menu.fxml";

    private final Logger logger = LogsCenter.getLogger(AppMenu.class);

    private final CommandBox commandBox;

    /**
     * Create an application Menu for Academy Directory
     * @param commandBox command executor
     */
    public AppMenu(CommandBox commandBox) {
        super(FXML);
        this.commandBox = commandBox;
    }

    /**
     * A menu item that quickly executes the equivalent command `show RA1`
     */
    @FXML
    public void showRA1() {
        logger.info("Menu for Show RA1 clicked");
        commandBox.execute(ShowCommand.COMMAND_WORD + " RA1");
    }

    /**
     * A menu item that quickly executes the equivalent command `show RA2`
     */
    @FXML
    public void showRA2() {
        logger.info("Menu for Show RA2 clicked");
        commandBox.execute(ShowCommand.COMMAND_WORD + " RA2");
    }

    /**
     * A menu item that quickly executes the equivalent command `show MIDTERM`
     */
    @FXML
    public void showMidterm() {
        logger.info("Menu for Show Midterm clicked");
        commandBox.execute(ShowCommand.COMMAND_WORD + " MIDTERM");
    }

    /**
     * A menu item that quickly executes the equivalent command `show PE`
     */
    @FXML
    public void showPE() {
        logger.info("Menu for Show PE clicked");
        commandBox.execute(ShowCommand.COMMAND_WORD + " PE");
    }

    /**
     * A menu item that quickly executes the equivalent command `show FINAL`
     */
    @FXML
    public void showFinal() {
        logger.info("Menu for Show Final clicked");
        commandBox.execute(ShowCommand.COMMAND_WORD + " FINAL");
    }

    /**
     * A menu item that quickly executes the equivalent command `visualize`
     */
    @FXML
    public void showStatistics() {
        logger.info("Menu for Show statistics clicked");
        commandBox.execute(VisualizeCommand.COMMAND_WORD);
    }

    /**
     * A menu item that quickly executes the equivalent command `history`
     */
    @FXML
    public void showHistory() {
        logger.info("Menu for Show history clicked");
        commandBox.execute(HistoryCommand.COMMAND_WORD);
    }

    /**
     * A menu item that quickly executes the equivalent command `undo`
     */
    @FXML
    public void undo() {
        logger.info("Menu for undo changes clicked");
        commandBox.execute(UndoCommand.COMMAND_WORD);
    }

    /**
     * A menu item that quickly executes the equivalent command `redo`
     */
    @FXML
    public void redo() {
        logger.info("Menu for redo clicked");
        commandBox.execute(RedoCommand.COMMAND_WORD);
    }

    /**
     * A menu item that quickly executes the equivalent command `help`
     */
    @FXML
    public void showHelp() {
        logger.info("Menu for Show help clicked");
        commandBox.execute(HelpCommand.COMMAND_WORD);
    }

    /**
     * A menu item that quickly executes the equivalent command `exit`
     */
    @FXML
    public void exit() {
        commandBox.execute(ExitCommand.COMMAND_WORD);
    }
}
