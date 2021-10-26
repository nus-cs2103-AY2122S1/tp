package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.schedule.Appointment;

/**
 * An UI component that displays information of a {@code Appointment}.
 */
public class AppointmentCard extends UiPart<Region> {
    private static final Logger logger = LogsCenter.getLogger(AppointmentCard.class);

    private static final String FXML = "AppointmentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Appointment appointment;

    @FXML
    private HBox appointmentCardPane;
    @FXML
    private Label description;
    @FXML
    private Label id;
    @FXML
    private TitledPane clientsPane;
    @FXML
    private Label address;
    @FXML
    private Label date;
    @FXML
    private Label time;

    /**
     * Creates a {@code AppointmentCard} with the given {@code Appointment} and index to display.
     */
    public AppointmentCard(Appointment appointment, int displayedIndex) {
        super(FXML);
        this.appointment = appointment;
        id.setText(displayedIndex + ". ");
        description.setText(appointment.getDescription());
        address.setText(appointment.getLocation().value);
        date.setText(appointment.getStartDateTimeString()); // Changed from date.setText
        time.setText(appointment.getEndDateTimeString());

        UniquePersonList clients = appointment.getClients();
        PersonListPanel clientsDetail = new PersonListPanel(clients.asUnmodifiableObservableList());
        String clientNames = clients.toString();

        clientsPane.setText(clientNames);
        clientsPane.setContent(clientsDetail.getRoot());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentCard)) {
            return false;
        }

        // state check
        AppointmentCard card = (AppointmentCard) other;
        return id.getText().equals(card.id.getText())
                && clientsPane.equals(card.clientsPane);
    }
}
