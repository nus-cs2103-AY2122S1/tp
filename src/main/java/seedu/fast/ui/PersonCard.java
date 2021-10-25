package seedu.fast.ui;

import static seedu.fast.ui.UiUtil.ADDRESS_IMAGE;
import static seedu.fast.ui.UiUtil.DATE_IMAGE;
import static seedu.fast.ui.UiUtil.EMAIL_IMAGE;
import static seedu.fast.ui.UiUtil.PHONE_IMAGE;
import static seedu.fast.ui.UiUtil.TAG_IMAGE;
import static seedu.fast.ui.UiUtil.TIME_IMAGE;
import static seedu.fast.ui.UiUtil.VENUE_IMAGE;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.fast.model.person.Appointment;
import seedu.fast.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private VBox phone;
    @FXML
    private VBox address;
    @FXML
    private VBox email;
    @FXML
    private VBox tags;
    @FXML
    private Label remark;
    @FXML
    private Label appointmentHeader;
    @FXML
    private VBox appointmentDate;
    @FXML
    private VBox appointmentTime;
    @FXML
    private VBox appointmentVenue;
    @FXML
    private Label appointmentCount;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.getChildren().add(new ItemComponent(person.getPhone().value, PHONE_IMAGE));
        address.getChildren().add(new ItemComponent(person.getAddress().value, ADDRESS_IMAGE));
        email.getChildren().add(new ItemComponent(person.getEmail().value, EMAIL_IMAGE));
        remark.setText(person.getRemark().value);
        tags.getChildren().add(new TagComponent(person.getTags(), TAG_IMAGE));
        appointmentDate.getChildren().add(new ItemComponent(checkDateAndAddHeader(person.getAppointment().getDate()),
                DATE_IMAGE));
        appointmentTime.getChildren().add(new ItemComponent(
                checkTimeVenueAndAddHeader(
                        person.getAppointment().getTimeFormatted(), "Time",
                        person.getAppointment().getDate()), TIME_IMAGE));
        appointmentVenue.getChildren().add(new ItemComponent(
                checkTimeVenueAndAddHeader(person.getAppointment().getVenue(), "Venue",
                        person.getAppointment().getDate()), VENUE_IMAGE));
        appointmentCount.setText("Appointment: " + person.getCount());
    }

    /**
     * Check if appointment has been scheduled with this contact and modify the displayed header.
     * If appointment is scheduled, appointment header remains as "Upcoming Appointment"
     * and adds "Date: " indicator before the specified date.
     * If appointment is not scheduled yet, appointment header will show "No Appointment Scheduled yet"
     * and no date detail is shown.
     *
     * @param date The date data retrieved.
     * @return A String representing the date data if it is present.
     */
    private String checkDateAndAddHeader(String date) {
        String emptyDate = "";
        String header = "Date";

        if (date.equalsIgnoreCase(Appointment.NO_APPOINTMENT)) {
            appointmentHeader.setText(Appointment.NO_APPOINTMENT);
            date = emptyDate;
        }

        if (!date.equals(Appointment.NO_APPOINTMENT) && !date.equals(emptyDate)) {
            date = header + ": " + date;
        }

        return date;
    }

    /**
     * Check if appointment has been scheduled with this contact and if time and venue data are present.
     * If appointment is scheduled and time and/or venue data is present, add the header before the data.
     * If appointment is scheduled and time and/or venue data is not present,
     * display a '-' to indicate that the data is missing.
     * If appointment is not scheduled, hides the time and venue field as it is not supposed to be there.
     *
     * @param text The time/venue data retrieved.
     * @param header Either "Time " or "Venue: "
     * @param date The date data retrieved.
     * @return A String representing the data if it is present.
     */
    private String checkTimeVenueAndAddHeader(String text, String header, String date) {
        if (text.equals(Appointment.NO_TIME) && !date.equalsIgnoreCase(Appointment.NO_APPOINTMENT)) {
            text = "-";
        }

        if (!text.equals(Appointment.NO_TIME)) {
            text = header + ": " + text;
        }

        return text;
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
