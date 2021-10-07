package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Controller for a help page
 */
public class StudentWindow extends UiPart<Stage> {

    public static final String VIEW_MESSAGE = "You are currently viewing: ";

    private static final Logger logger = LogsCenter.getLogger(StudentWindow.class);
    private static final String FXML = "StudentWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label showMessage;

    @FXML
    private Label studentName;

    @FXML
    private Label studentAddress;

    @FXML
    private Label studentEmail;

    @FXML
    private Label studentPhone;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public StudentWindow(Stage root) {
        super(FXML, root);
        showMessage.setText(VIEW_MESSAGE);
    }

    /**
     * Creates a new StudentWindow.
     */
    public StudentWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
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
    public void show(Person student) {
        logger.fine("Showing student information.");
        studentName.setText(student.getName().toString());
        studentAddress.setText(student.getAddress().toString());
        studentEmail.setText(student.getEmail().toString());
        studentPhone.setText(student.getPhone().toString());
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
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}