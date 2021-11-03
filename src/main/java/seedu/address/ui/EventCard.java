package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.module.event.Event;

/**
 * A UI component that displays information of a {@code Event}.
 */
public class EventCard extends UiPart<Region> {

    private static final String FXML = "EventListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Event event;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label index;
    @FXML
    private Label date;
    @FXML
    private FlowPane participants;

    /**
     * Creates a {@code EventCard} with the given {@code Event} and index to display.
     */
    public EventCard(Event event, int displayedIndex) {
        super(FXML);
        this.event = event;
        index.setText(displayedIndex + ". ");
        name.setText(event.getName().fullName);
        date.setText("Date: " + event.getDate().toString());
        event.getParticipants().stream()
                .sorted(Comparator.comparing(member -> member.getName().fullName))
                .forEach(member -> {
                    Label participantLabel = new Label(member.getName().fullName);
                    if (event.hasAttended(member)) {
                        participantLabel.setStyle("-fx-background-color: #3f7318");
                    } else {
                        participantLabel.setStyle("-fx-background-color: #7c0236");
                    }
                    participantLabel.setMaxWidth(100);
                    participantLabel.setWrapText(true);
                    participants.getChildren().add(participantLabel);
                });
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventCard)) {
            return false;
        }

        // state check
        EventCard card = (EventCard) other;
        return index.getText().equals(card.index.getText())
                && event.equals(card.event);
    }
}
