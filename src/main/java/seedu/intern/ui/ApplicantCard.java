package seedu.intern.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.intern.model.applicant.Applicant;

/**
 * An UI component that displays information of a {@code Applicant}.
 */
public class ApplicantCard extends UiPart<Region> {

    private static final String FXML = "ApplicantListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Applicant applicant;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label job;
    @FXML
    private Label status;


    /**
     * Creates a {@code ApplicantCode} with the given {@code Applicant} and index to display.
     */
    public ApplicantCard(Applicant applicant, int displayedIndex) {
        super(FXML);
        this.applicant = applicant;
        this.cardPane.setStyle(setColour(displayedIndex));
        id.setText(displayedIndex + ". ");
        name.setText(applicant.getName().fullName);
        phone.setText(applicant.getPhone().value);
        email.setText(applicant.getEmail().value);
        job.setText(applicant.getJob().jobName);
        status.setText(applicant.getApplicationStatus().value.toString());
        status.setStyle(String.format("-fx-background-color: %s;", applicant.getApplicationStatus().getColour()));
    }

    public String setColour(int index) {
        if (index % 2 == 0) {
            return "-fx-background-color: #decbc6;";
        } else {
            return "-fx-background-color: #c9ada7;";
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ApplicantCard)) {
            return false;
        }

        // state check
        ApplicantCard card = (ApplicantCard) other;
        return id.getText().equals(card.id.getText())
                && applicant.equals(card.applicant);
    }
}
