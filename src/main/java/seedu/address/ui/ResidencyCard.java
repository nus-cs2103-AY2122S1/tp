package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.residency.Residency;

import java.util.Comparator;
import java.util.Set;

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
    private FlowPane personInfo;



    /*
    @FXML
    private FlowPane tags;
    */

    /**
     * Creates a {@code ResidencyCode} with the given {@code Residency} and index to display.
     */
    public ResidencyCard(Residency residency, int displayedIndex) {
        super(FXML);
        this.residency = residency;

        id.setText(displayedIndex + ". ");
        number.setText(residency.getRoom().getRoomNumber().value);

        Set<Person> guests = residency.getGuests();
        guests.stream().map(person -> {
            String cellInfo = String.format("%s\n%s\n%s\n%s\n%s",
                    person.getName().toString(),
                    person.getNric().toString(),
                    person.getAddress().toString(),
                    person.getPhone().toString(),
                    person.getEmail().toString()
            );
            return cellInfo;
        }).forEach(nricString -> personInfo.getChildren().add(new Label(nricString)));


        /*
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        */
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
