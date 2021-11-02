package seedu.address.ui;

import java.time.DayOfWeek;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Period;
import seedu.address.model.person.Person;
import seedu.address.model.person.Slot;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class DayCard extends UiPart<Region> {

    private static final String FXML = "DayCard.fxml";
    private final Logger logger = LogsCenter.getLogger(DayCard.class);

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private final DayOfWeek day;

    @FXML
    private VBox slotPane;
    @FXML
    private Label dayLabel;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public DayCard(DayOfWeek day, ObservableList<Person> stafflist, Period currentPeriod) {
        super(FXML);
        this.day = day;
        dayLabel.setText(day.toString().substring(0, 3));
        slotPane.getChildren().addAll(new SlotCard(day, Slot.MORNING, stafflist, currentPeriod).getRoot(),
                new SlotCard(day, Slot.AFTERNOON, stafflist, currentPeriod).getRoot());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DayCard)) {
            return false;
        }

        // state check
        DayCard card = (DayCard) other;
        return day.equals(card.day);
    }
}
