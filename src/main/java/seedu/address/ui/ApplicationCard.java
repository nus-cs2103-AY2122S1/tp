package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.application.Application;


/**
 * An UI component that displays information of an {@code Application}.
 */
public class ApplicationCard extends UiPart<Region> {

    private static final String FXML = "ApplicationListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     */

    public final Application application;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label position;
    @FXML
    private Label deadline;
    @FXML
    private Label completion;
    @FXML
    private Label status;
    @FXML
    private Label priority;
    @FXML
    private FlowPane requirements;
    @FXML
    private FlowPane interviewDateAndTime;

    /**
     * Creates an {@code ApplicationCode} with the given {@code Application} and index to display.
     */
    public ApplicationCard(Application application, int displayedIndex) {
        super(FXML);
        this.application = application;
        id.setText(displayedIndex + ". ");
        name.setText(application.getCompany().fullCompanyName);
        position.setText(application.getPosition().value);
        deadline.setText(application.getDeadline().toFormattedString());
        completion.setText(application.getCompletion().toDisplayString());
        status.setText(application.getStatus().value);
        priority.setText(application.getPriority().toDisplayString());
        application.getRequirements().stream()
                .sorted(Comparator.comparing(requirement -> requirement.value))
                .forEach(requirement -> requirements.getChildren().add(new Label(requirement.value)));
        application.getInterviewDateAndTime().stream()
                .sorted(Comparator.comparing(dt -> dt.value))
                .forEach(dateAndTime -> interviewDateAndTime.getChildren()
                        .add(new Label(dateAndTime.toFormattedString())));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ApplicationCard)) {
            return false;
        }

        // state check
        ApplicationCard card = (ApplicationCard) other;
        return id.getText().equals(card.id.getText())
                && application.equals(card.application);
    }
}
