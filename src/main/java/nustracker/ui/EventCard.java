package nustracker.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import nustracker.model.event.Event;

/**
 * A UI component that displays information of an {@code Event}.
 */
public class EventCard extends UiPart<Region> {

    private static final String FXML = "EventListCard.fxml";

    public final Event event;

    @FXML
    private HBox cardPane;
    @FXML
    private Text name;
    @FXML
    private Text date;
    @FXML
    private Text time;
    @FXML
    private FlowPane participants;
    @FXML
    private FlowPane blacklist;

    /**
     * Creates an {@code EventCard} with the given {@code Event} and index to display.
     */
    public EventCard(Event event) {
        super(FXML);
        this.event = event;
        name.setText(event.getName().getEventName());
        date.setText(event.getDate().toString());
        time.setText(event.getTime().toString());

        setSpacing();

        event.getParticipants().stream()
                .sorted(Comparator.comparing(participant -> participant.studentId))
                .forEach(participant -> participants.getChildren().add(
                        new Label(participant.studentId)));

        event.getBlacklist().stream()
                .sorted(Comparator.comparing(blacklisted -> blacklisted.studentId))
                .forEach(blacklisted -> blacklist.getChildren().add(
                        new Label(blacklisted.studentId)));

    }

    /**
     * Sets the spacing of the events and participant labels between each other . Always set to a value of 4.0.
     */
    private void setSpacing() {
        participants.setVgap(4.0);
        participants.setHgap(4.0);
        blacklist.setVgap(4.0);
        blacklist.setHgap(4.0);
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
        return event.equals(card.event);
    }
}
