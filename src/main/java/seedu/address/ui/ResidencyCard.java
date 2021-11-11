package seedu.address.ui;

import java.time.format.DateTimeFormatter;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.residency.Residency;


public class ResidencyCard extends UiPart<Region> {

    private static final String FXML = "ResidencyListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Residency residency;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label number;

    @FXML
    private Label in;
    @FXML
    private Label out;
    @FXML
    private FlowPane personInfo;


    /**
     * Creates a {@code ResidencyCode} with the given {@code Residency} and index to display.
     */
    public ResidencyCard(Residency residency, int displayedIndex) {
        super(FXML);
        this.residency = residency;

        id.setText(displayedIndex + ". ");
        number.setText(residency.getRoom().getRoomNumber().value);
        in.setText(residency.getCheckInTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        out.setText(residency.getCheckOutTime().get().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        Set<Person> guests = residency.getGuests();
        guests.stream().map(person -> {
            String cellInfo = String.format("%s\n%s\n%s\n%s\n%s",
                    person.getName().toString(),
                    person.getNric().toString(),
                    person.getPhone().toString(),
                    person.getAddress().toString(),
                    person.getEmail().toString()
            );
            return cellInfo;
        }).forEach(nricString -> personInfo.getChildren().add(new Label(nricString)));

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ResidencyCard)) {
            return false;
        }

        // state check
        ResidencyCard card = (ResidencyCard) other;
        return id.getText().equals(card.id.getText())
                && residency.equals(card.residency);
    }
}
