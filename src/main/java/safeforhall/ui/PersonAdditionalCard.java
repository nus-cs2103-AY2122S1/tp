package safeforhall.ui;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import safeforhall.logic.Logic;
import safeforhall.model.event.Event;
import safeforhall.model.person.Email;
import safeforhall.model.person.Faculty;
import safeforhall.model.person.LastDate;
import safeforhall.model.person.Person;
import safeforhall.model.person.Phone;
import safeforhall.model.person.Room;
import safeforhall.model.person.VaccStatus;

public class PersonAdditionalCard extends UiPart<Region> {

    private static final String FXML = "PersonAdditionalCard.fxml";
    private static final String EVENTS_DESC = "Events: ";

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label room;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label vaccStatus;
    @FXML
    private Label faculty;
    @FXML
    private Label events;
    @FXML
    private Label lastFetDate;
    @FXML
    private Label lastCollectionDate;
    @FXML
    private HBox informationContainer;
    @FXML
    private HBox deadlineContainer;
    @FXML
    private VBox labelBox;
    @FXML
    private VBox labelBoxInterior;

    /**
     * Creates a {@code PersonAdditionalCard} with the given {@code Member} and index to display.
     */
    public PersonAdditionalCard(Person person, Logic logic) {
        super(FXML);
        this.person = person;
        name.setText(person.getName().fullName);
        name.setWrapText(true);

        room.setText(Room.DESC + person.getRoom().room);
        phone.setText(Phone.DESC + person.getPhone().value);

        email.setText(Email.DESC + person.getEmail().value);
        email.setWrapText(true);

        faculty.setText(Faculty.DESC + person.getFaculty().faculty);
        faculty.setWrapText(true);

        vaccStatus.setText(VaccStatus.DESC + person.getVaccStatus().vaccStatus);
        lastFetDate.setText(LastDate.FET_DESC + person.getLastFetDate().date);
        lastCollectionDate.setText(LastDate.COLLECTION_DESC + person.getLastCollectionDate().date);

        ArrayList<Event> eventList = logic.getModel().getPersonEvents(person, event -> true);
        events.setText(EVENTS_DESC + (eventList.isEmpty() ? "None" : eventList
                .stream()
                .map(event -> event.getEventName().eventName)
                .reduce("", (name, acc) -> name.equals("")
                        ? name + acc
                        : name + ", " + acc)));
        events.setWrapText(true);

        if (person.hasMissedDeadline()) {
            Label textBox = new Label("Fet late by: ");
            int missedDates = person.getMissedDates();
            Label date;
            if (missedDates > 1) {
                date = new Label(missedDates + " days");
            } else {
                date = new Label(missedDates + " day");
            }
            textBox.getStyleClass().add("cell_alert");
            date.getStyleClass().add("cell_alert");
            deadlineContainer.getChildren().add(textBox);
            deadlineContainer.getChildren().add(date);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonAdditionalCard)) {
            return false;
        }

        // state check
        PersonAdditionalCard card = (PersonAdditionalCard) other;
        return person.equals(card.person);
    }

}
