package seedu.academydirectory.ui.creator;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import seedu.academydirectory.model.AdditionalInfo;
import seedu.academydirectory.model.student.Student;

public class ViewCreator extends Creator {

    public static final String PLACEHOLDER_PHONE = "1. Phone number ";
    public static final String PLACEHOLDER_EMAIL = "2. Email address: ";
    public static final String PLACEHOLDER_TELEGRAM = "3. Telegram handle: ";
    private static final String FXML = "creator/ViewCreator.fxml";

    @FXML
    private StackPane placeHolder;

    @FXML
    private Label fullName;

    @FXML
    private FlowPane tags;

    @FXML
    private ScrollPane participation;

    @FXML
    private ScrollPane testScores;

    @FXML
    private Label phone;

    @FXML
    private Label email;

    @FXML
    private Label telegram;

    /**
     * View Creator for Student Detailed Information
     * @param additionalInfo information to be passed in
     */
    public ViewCreator(AdditionalInfo<?> additionalInfo) {
        super(additionalInfo, FXML);
        Student student = (Student) additionalInfo.get();

        fullName.setText(student.getName().fullName);
        phone.setText(PLACEHOLDER_PHONE + student.getPhone().toString());
        email.setText(PLACEHOLDER_EMAIL + student.getEmail().value);
        telegram.setText(PLACEHOLDER_TELEGRAM + student.getTelegram().value);
        student.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        String studioRecord = student.getStudioRecord().visualizeForView();
        Label displayInfoPlaceHolder = new Label(studioRecord);
        participation.setContent(displayInfoPlaceHolder);

        String assessments = student.getAssessment().visualizeForView();
        Label assessmentPlaceHolder = new Label(assessments);
        testScores.setContent(assessmentPlaceHolder);
    }

    @Override
    public Node create() {
        return this.getRoot();
    }
}
