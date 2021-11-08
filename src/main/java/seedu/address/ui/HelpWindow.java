package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Represents for Controlling a help page.
 */
public class HelpWindow extends UiPart<Stage> {

    /**
     * Uses FXML to identify HelpWindow.
     */
    private static final String FXML = "HelpWindow.fxml";

    /**
     * Uses logger to log events happen in HelpWindow.
     */
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);

    /**
     * Stands for components to be used in FXML.
     */
    @FXML
    private StackPane helpBoxPlaceholder;

    /**
     * Constructs a new {@code HelpWindow}.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        HelpBox helpBox = new HelpBox();
        helpBoxPlaceholder.getChildren().add(helpBox.getRoot());
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            System.out.println(event.getCode());
            if (event.getCode() == KeyCode.ESCAPE) {
                Stage stage = (Stage) getRoot().getScene().getWindow();
                stage.close();
            }
        });
    }

    /**
     * Constructs a new {@code HelpWindow}.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     *
     * @throws IllegalStateException if the user input does not conform the expected format.
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
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the opened help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on current opened help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }


}
