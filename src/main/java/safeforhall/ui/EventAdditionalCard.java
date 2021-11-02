package safeforhall.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import safeforhall.model.event.Capacity;
import safeforhall.model.event.Event;
import safeforhall.model.event.EventDate;
import safeforhall.model.event.EventTime;
import safeforhall.model.event.ResidentList;
import safeforhall.model.event.Venue;

public class EventAdditionalCard extends UiPart<Region> {

    private static final String FXML = "EventAdditionalCard.fxml";

    public final Event event;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label date;
    @FXML
    private Label time;
    @FXML
    private Label venue;
    @FXML
    private Label capacity;
    @FXML
    private Label residents;

    /**
     * Creates a {@code EventAdditionalCard} with the given {@code Member} and index to display.
     */
    public EventAdditionalCard(Event event) {
        super(FXML);
        this.event = event;
        name.setText(event.getEventName().eventName);
        name.setWrapText(true);

        date.setText(EventDate.DESC + event.getEventDate().eventDate);
        time.setText(EventTime.DESC + event.getEventTime().eventTime);

        venue.setText(Venue.DESC + event.getVenue().venue);
        venue.setWrapText(true);

        capacity.setText(Capacity.DESC + event.getCapacity().capacity);
        capacity.setWrapText(true);

        residents.setText(ResidentList.DESC + event.getResidentList().getResidentsDisplay());
        residents.setWrapText(true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventAdditionalCard)) {
            return false;
        }

        // state check
        EventAdditionalCard card = (EventAdditionalCard) other;
        return event.equals(card.event);
    }

}
