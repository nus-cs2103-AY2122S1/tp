package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.assessment.Assessment;

public class AssessmentCard extends UiPart<Region> {

    private static final String FXML = "AssessmentCard.fxml";

    public final Assessment assessment;

    @FXML
    private Label name;

    @FXML
    private Label id;

    @FXML
    private Label grade;

    @FXML
    private Label percentage;

    @FXML
    private VBox assessmentCard;

    /**
     * Creates a {@code AssessmentCard} with the give {@code Assessment} and index to display.
     */
    public AssessmentCard(Assessment assessment, int displayedIndex) {
        super(FXML);
        this.assessment = assessment;
        ObservableList<String> assessmentCardStyleClass = assessmentCard.getStyleClass();
        ObservableList<String> percentageStyleClass = percentage.getStyleClass();
        if (assessment.getScore().isFail()) {
            assessmentCardStyleClass.add("flag");
            percentageStyleClass.add("fail");
        } else {
            assessmentCardStyleClass.add("normal-card");
            percentageStyleClass.add("pass");
        }
        id.setText(String.format("%d", displayedIndex));
        name.setText(assessment.getAssessmentName().toString());
        grade.setText(assessment.getScore().toString());
        percentage.setText(assessment.getScore().getPercentage() + "%");
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssessmentCard)) {
            return false;
        }

        // state check
        AssessmentCard card = (AssessmentCard) other;
        return id.getText().equals(card.id.getText())
            && assessment.equals(card.assessment);
    }
}
