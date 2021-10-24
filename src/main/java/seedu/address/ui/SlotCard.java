package seedu.address.ui;

import java.time.DayOfWeek;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;
import seedu.address.model.person.Slot;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class SlotCard extends UiPart<Region> {

    private static final String FXML = "SlotCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private final DayOfWeek day;
    private final Slot slot;

    @FXML
    private VBox slotPane;
    @FXML
    private Label shiftName;
    @FXML
    private ListView<Person> staffWorkingList;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public SlotCard(DayOfWeek day, Slot slot, ObservableList<Person> stafflist) {
        super(FXML);
        this.day = day;
        this.slot = slot;
        shiftName.setText(slot.toString());
        ObservableList<Person> filteredList = stafflist.filtered(p -> p.isWorking(day, slot.getOrder()));
        staffWorkingList.setItems(filteredList);
        staffWorkingList.setCellFactory(listView -> new PersonNameCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonNameCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person staff, boolean empty) {
            super.updateItem(staff, empty);

            if (empty || staff == null) {
                setGraphic(null);
                setText(null);
            } else {
                setText(staff.getName().toString());
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SlotCard)) {
            return false;
        }

        // state check
        SlotCard card = (SlotCard) other;
        return day.equals(card.day) && slot.equals(card.slot);
    }
}
