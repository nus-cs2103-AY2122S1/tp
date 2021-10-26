package seedu.address.ui;

import static javafx.collections.FXCollections.observableList;

import java.time.DayOfWeek;
import java.util.List;
import java.util.logging.Logger;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.person.Schedule;
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
    private final Slot slot;
    ObservableList<Person> filteredList;
    ObservableList<Person> stafflist;

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
        this.stafflist = stafflist;
        shiftName.setText(slot.toString());
        refresh();
        stafflist.addListener((ListChangeListener<? super Person>) change -> {
            System.out.println("stafflist change");
            refresh();
        });
        staffWorkingList.setCellFactory(listView -> new PersonNameCell());
    }

    public void refresh() {
        System.out.println("refreshed" + day.toString() + slot.toString());
        filteredList = stafflist.filtered(p -> p.isWorking(day, slot.getOrder()));
        staffWorkingList.setItems(filteredList);
        staffWorkingList.refresh();
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
            } else {
                setText(staff.getName().toString());
            }
        }
    }
}
