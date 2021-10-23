package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.student.Student;

public class DetailedStudentCard extends UiPart<Region> {

    private static final String FXML = "DetailedStudentCard.fxml";

    public final Student student;

    @FXML
    private HBox cardPane;
    @FXML
    private VBox detailsCard;
    @FXML
    private VBox noteCard;
    @FXML
    private Label name;
    @FXML
    private Label telegramHandle;
    @FXML
    private Label email;
    @FXML
    private Label groupName;
    @FXML
    private TextArea notes;

    /**
     * Creates a {@code DetailedStudentCard} with the given {@code Student}.
     */
    public DetailedStudentCard(Student student) {
        super(FXML);
        this.student = student;
        if (student.isWeak()) {
            ObservableList<String> styleClass = name.getStyleClass();
            styleClass.add("flag");
        }
        name.setText(student.getName().fullName);
        groupName.setText(student.getGroup().getGroupName().toString());
        telegramHandle.setText(student.getTelegramHandle().value);
        email.setText(student.getEmail().value);
        notes.setText(student.getNote().getNote());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DetailedStudentCard)) {
            return false;
        }

        // state check
        DetailedStudentCard card = (DetailedStudentCard) other;
        return student.equals(card.student);
    }
}
