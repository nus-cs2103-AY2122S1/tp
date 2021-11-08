package seedu.programmer.ui.windows;

import javafx.stage.Stage;
import seedu.programmer.ui.UiPart;

/**
 * Controller for a popup page.
 */
public abstract class PopupWindow extends UiPart<Stage> {

    /**
     * Creates a new PopupWindow.
     *
     * @param root Stage to use as the root of the PopupWindow.
     */
    public PopupWindow(String fxml, Stage root) {
        super(fxml, root);
    }

    /**
     * Shows the popup window.
     *
     * @throws IllegalStateException if the state of the popup window is not valid.
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
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the popup window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the popup window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the popup window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
