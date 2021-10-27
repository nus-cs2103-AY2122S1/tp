package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.GuiSettings.Theme;
import seedu.address.commons.core.LogsCenter;

public class SettingsWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(SettingsWindow.class);
    private static final String FXML = "SettingsWindow.fxml";

    private final MainWindow mainWindow;
    private final GuiSettings settings;

    @FXML
    private ComboBox<String> themeChooser;

    /**
     * Creates a new SettingsWindow.
     *
     * @param root Stage to use as the root of the SettingsWindow.
     */
    public SettingsWindow(Stage root, MainWindow window, GuiSettings settings) {
        super(FXML, root);
        mainWindow = window;
        this.settings = settings;
        initThemeChooser();
    }

    /**
     * Creates a new SettingsWindow.
     */
    public SettingsWindow(MainWindow window, GuiSettings settings) {
        this(new Stage(), window, settings);
    }

    /**
     * Shows the settings window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing settings for the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the settings window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the settings window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the settings window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    private void initThemeChooser() {
        themeChooser.setValue(settings.getTheme().toString());
        themeChooser.setOnAction(t ->
            mainWindow.setTheme(Theme.valueOf(themeChooser.getValue()))
        );
    }
}
