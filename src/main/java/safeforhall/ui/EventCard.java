package safeforhall.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import safeforhall.model.event.Event;
import safeforhall.model.event.EventDate;

/**
 * An UI component that displays information of a {@code Event}.
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
    private Label id;
    @FXML
    private HBox informationContainer;
    @FXML
    private VBox vaccinatedContainer;
    @FXML
    private Label date;


    /**
     * Creates a {@code EventCode} with the given {@code Event} and index to display.
     */
    public EventCard(Event event, int displayedIndex) {
        super(FXML);
        this.event = event;
        id.setText(displayedIndex + ". ");
        name.setText(event.getEventName().eventName);
        date.setText(EventDate.DESC + event.getEventDate().eventDate);


        if (event.hasUnvaccinatedResident()) {
            this.getRoot().setStyle("-fx-border-color: derive(#FF0000, 70%); -fx-border-width: 1 1 1 7;"
                    + "-fx-border-radius: 5");
            Label textBox = new Label("Un-Vaccinated: " + event.numOfUnvaccinatedResidents());
            vaccinatedContainer.getChildren().add(textBox);
        }

        if (event.isOver()) {
            this.getRoot().setStyle("-fx-border-color: derive(#A9A9A9, 70%); -fx-border-width: 1 1 1 7;"
                    + "-fx-border-radius: 5");
        }
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
        return id.getText().equals(card.id.getText())
                && event.equals(card.event);
    }
}
