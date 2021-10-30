package seedu.academydirectory.ui.creator;

import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import seedu.academydirectory.model.AdditionalInfo;
import seedu.academydirectory.model.student.Student;
import seedu.academydirectory.model.student.StudioRecord;
import seedu.academydirectory.model.tag.Tag;

public class ViewCreator extends Creator {

    private static final String FXML = "creator/ViewCreator.fxml";

    private final Student student;

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
        this.student = (Student) additionalInfo.get();
        fullName.setText(student.getName().fullName);
        phone.setText("- Phone number: " + student.getPhone().toString());
        email.setText("- Email address: " + student.getEmail().value);
        telegram.setText("- Telegram handle: " + student.getTelegram().value);
        Set<Tag> tagsList = student.getTags();
        for (Tag tag : tagsList) {
            Label tagWrapper = new Label(tag.tagName + " ");
            tags.getChildren().add(tagWrapper);
        }
        StudioRecord studioRecord = student.getStudioRecord();
        Label displayInfoPlaceHolder = new Label(studioRecord.getExtendedStudioRecords());
        participation.setContent(displayInfoPlaceHolder);
        String assessments = student.getAssessment().getVisualizerDisplay();
        testScores.setContent(new Label(assessments));
    }

    @Override
    public Node create() {
        return this.getRoot();
    }
}
