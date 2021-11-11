package seedu.address.ui;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.model.residency.Residency;
import seedu.address.model.room.Room;


/**
 * A UI component that displays information of a {@code Room}.
 */
public class RoomCard extends UiPart<Region> {

    private static final String FXML = "RoomListCard.fxml";

    public final Room room;

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private HBox cardPane;

    @FXML
    private Label number;

    @FXML
    private Label vacancy;

    @FXML
    private Label id;

    @FXML
    private FlowPane tags;

    @FXML
    private FlowPane guests;

    /**
     * Creates a {@code RoomCard} with the given {@code Room} and index to display.
     */
    public RoomCard(Room room, int displayedIndex) {
        super(FXML);
        this.room = room;
        id.setText(displayedIndex + ". ");
        number.setText(room.getRoomNumber().value);
        vacancy.setText(room.getVacancy().toString());
        room.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));


        Set<Person> allGuests = new HashSet<>();
        Optional<Residency> residency = ModelManager.getInstance().getResidency(room);
        if (residency.isPresent()) {
            allGuests.addAll(residency.get().getGuests());
            allGuests.stream().map(person -> person.getName().toString())
                    .forEach(nameString -> {
                        guests.getChildren().add(new Label(nameString));
                    });
        }

        if (room.getVacancy().toString().equals("Vacant")) {
            vacancy.setStyle("-fx-text-fill: #1be3b2 !important;");
        } else {
            vacancy.setStyle("-fx-text-fill: #f51d50 !important; -fx-font-style: italic !important;");
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RoomCard)) {
            return false;
        }

        // state check
        RoomCard card = (RoomCard) other;
        return id.getText().equals(card.id.getText())
                && room.equals(card.room);
    }
}
