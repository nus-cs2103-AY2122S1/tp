package seedu.address.testutil;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import seedu.address.logic.Logic;
import seedu.address.ui.MainWindow;
import seedu.address.ui.Ui;

public class UiStub implements Ui {
    private Logic logic;
    private MainWindow mainWindow;

    public UiStub() {
        this.logic = null;
        this.mainWindow = null;
    }

    public UiStub(Logic logic) {
        this.logic = logic;
        this.mainWindow = null;
    }

    @Override
    public void start(Stage primaryStage) {
    }

    public static boolean showDeleteDialogAndWait(String contentText) {
        return true;
    }
}
