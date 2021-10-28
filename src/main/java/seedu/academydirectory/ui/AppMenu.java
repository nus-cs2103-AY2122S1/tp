package seedu.academydirectory.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import seedu.academydirectory.logic.commands.HelpCommand;
import seedu.academydirectory.logic.commands.HistoryCommand;
import seedu.academydirectory.logic.commands.RedoCommand;
import seedu.academydirectory.logic.commands.ShowCommand;
import seedu.academydirectory.logic.commands.UndoCommand;
import seedu.academydirectory.logic.commands.VisualizeCommand;

public class AppMenu extends UiPart<Region> {
    public static final String FXML = "Menu.fxml";

    private final CommandBox commandBox;

    /**
     * Create an application Menu for Academy Directory
     * @param commandBox command executor
     */
    public AppMenu(CommandBox commandBox) {
        super(FXML);
        this.commandBox = commandBox;
    }

    @FXML
    public void showRA1() {
        commandBox.execute(ShowCommand.COMMAND_WORD + " RA1");
    }

    @FXML
    public void showRA2() {
        commandBox.execute(ShowCommand.COMMAND_WORD + " RA2");
    }

    @FXML
    public void showMidterm() {
        commandBox.execute(ShowCommand.COMMAND_WORD + " MIDTERM");
    }

    @FXML
    public void showPE() {
        commandBox.execute(ShowCommand.COMMAND_WORD + " PE");
    }

    @FXML
    public void showFinal() {
        commandBox.execute(ShowCommand.COMMAND_WORD + " FINAL");
    }

    @FXML
    public void showStatistics() {
        commandBox.execute(VisualizeCommand.COMMAND_WORD);
    }

    @FXML
    public void showHistory() {
        commandBox.execute(HistoryCommand.COMMAND_WORD);
    }

    @FXML
    public void undo() {
        commandBox.execute(UndoCommand.COMMAND_WORD);
    }

    @FXML
    public void redo() {
        commandBox.execute(RedoCommand.COMMAND_WORD);
    }

    @FXML
    public void showHelp() {
        commandBox.execute(HelpCommand.COMMAND_WORD);
    }
}
