package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Student;

/**
 * Controller for a student display page
 */
public class StudentWindow extends UiPart<Stage> {

    public static final String VIEW_MESSAGE = "Currently viewing: ";

    private static final Logger logger = LogsCenter.getLogger(StudentWindow.class);
    private static final String FXML = "StudentWindow.fxml";

    @FXML
    private HBox cardPane;

    @FXML
    private Label message;

    @FXML
    private Label name;

    @FXML
    private Label classCode;

    @FXML
    private Label address;

    @FXML
    private Label email;

    @FXML
    private Label phone;

    @FXML
    private FlowPane tags;

    /**
     * Creates a new StudentWindow.
     *
     * @param root Stage to use as the root of the StudentWindow.
     */
    public StudentWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new StudentWindow.
     */
    public StudentWindow() {
        this(new Stage());
    }

    /**
     * Shows the student window.
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
    public void show(Student student) {
        tags.getChildren().clear();
        logger.fine("Showing student information.");
        message.setText(VIEW_MESSAGE);
        name.setText(student.getName().fullName);
        classCode.setText(student.getClassCode().value);
        address.setText(student.getAddress().value);
        email.setText(student.getEmail().value);
        phone.setText(student.getPhone().value);
        student.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the student window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the student window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the student window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
