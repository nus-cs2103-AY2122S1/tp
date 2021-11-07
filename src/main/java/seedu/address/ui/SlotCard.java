package seedu.address.ui;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Period;
import seedu.address.model.person.Person;
import seedu.address.model.person.Slot;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class SlotCard extends UiPart<Region> {

    private static final String FXML = "SlotCard.fxml";
    private final Logger logger = LogsCenter.getLogger(SlotCard.class);

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private final DayOfWeek day;
    private final LocalDate date;
    private final Slot slot;
    private ObservableList<Person> stafflist;

    @FXML
    private VBox slotCard;
    @FXML
    private Label shiftName;
    @FXML
    private ListView<Person> staffWorkingList;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public SlotCard(DayOfWeek day, Slot slot, ObservableList<Person> stafflist, Period period, LocalDate date) {
        super(FXML);
        this.day = day;
        this.date = date;
        this.slot = slot;
        this.stafflist = stafflist;
        shiftName.setText(slot.toString());
        ObservableList<Person> filteredList =
                stafflist.filtered(p -> p.isWorking(day, slot.getOrder(), period));
        staffWorkingList.setItems(filteredList);
        staffWorkingList.refresh();
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
                setText(null);
                setStyle("");
            } else {
                setText(staff.getName().toString());
                if (staff.wasAbsent(date)) {
                    setStyle("-fx-background-color: #FF7F7F; -fx-text-fill: black");
                } else {
                    setStyle("");
                }
            }
        }
    }
}
