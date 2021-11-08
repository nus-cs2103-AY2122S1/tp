package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
        setName(student);
        setGroupName(student);
        telegramHandle.setText(student.getTelegramHandle().value);
        email.setText(student.getEmail().value);
        notes.setText(student.getNote().toString());
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

    private void setName(Student student) {
        String studentName = student.getName().fullName;
        Text txt = new Text(studentName);
        double txtWidth = txt.getLayoutBounds().getWidth();
        double ratio = 200 / txtWidth;
        if (txtWidth > 200) {
            double newFontSize = 70 * ratio;
            if (ratio < 0.2) {
                newFontSize *= (1 - 1 / ratio);
            }
            name.setStyle(String.format("-fx-font-size:%f;", newFontSize));
        }
        name.setText(studentName);
    }

    private void setGroupName(Student student) {
        String studentGroup = student.getGroupName().toString();
        Text text = new Text(studentGroup);
        double txtWidth = text.getLayoutBounds().getWidth();
        ObservableList<String> styleClass = groupName.getStyleClass();
        if (txtWidth > 200) {
            styleClass.add("smaller_group_name");
        } else {
            styleClass.add("bigger_group_name");
        }
        groupName.setText(studentGroup);
    }
}

