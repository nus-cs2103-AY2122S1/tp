package seedu.smartnus.ui;

import static seedu.smartnus.ui.UiPart.FXML_FILE_FOLDER;

import javafx.scene.Scene;
import javafx.stage.Stage;
import seedu.smartnus.commons.core.GuiSettings;
import seedu.smartnus.commons.core.theme.Theme;
import seedu.smartnus.logic.Logic;

public class UiUtils {

    /**
     * Sets the GUI setting.
     * @param logic The Logic model to set the current GUI settings.
     * @param stage The current stage.
     */
    public static void setGuiSettings(Logic logic, Stage stage) {
        GuiSettings guiSettings = new GuiSettings(stage.getWidth(), stage.getHeight(),
                (int) stage.getX(), (int) stage.getY());
        logic.setGuiSettings(guiSettings);
    }

    /**
     * Sets the theme of the UI (light or dark).
     * @param theme The theme of the UI.
     * @param stage The current stage.
     */
    public static void setTheme(Theme theme, Stage stage) {
        Scene scene = stage.getScene();
        String pathToTheme = FXML_FILE_FOLDER;
        String themeCssFile = pathToTheme + theme.getThemeCssFile();
        String extensionCssFile = pathToTheme + theme.getExtensionsCssFile();
        scene.getStylesheets().remove(1, scene.getStylesheets().size());
        scene.getStylesheets().add(themeCssFile);
        scene.getStylesheets().add(extensionCssFile);
    }

}
