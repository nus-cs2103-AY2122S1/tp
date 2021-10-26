package seedu.address.ui;

import java.time.DayOfWeek;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.person.Slot;

/**
 * Panel containing the list of persons.
 */
public class WeekShiftsPane extends UiPart<Region> {
    private static final String FXML = "WeekShiftsPane.fxml";
    private static DayCard monday;
    private static DayCard tuesday;
    private static DayCard wednesday;
    private static DayCard thursday;
    private static DayCard friday;
    private static DayCard saturday;
    private static DayCard sunday;
    private static WeekShiftsPane main_instance;
    private final Logger logger = LogsCenter.getLogger(WeekShiftsPane.class);

    @FXML
    private HBox weekShiftsPane;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    private WeekShiftsPane(ObservableList<Person> staffList) {
        super(FXML);
        monday = new DayCard(DayOfWeek.MONDAY, staffList);
        tuesday = new DayCard(DayOfWeek.TUESDAY, staffList);
        wednesday = new DayCard(DayOfWeek.WEDNESDAY, staffList);
        thursday = new DayCard(DayOfWeek.THURSDAY, staffList);
        friday = new DayCard(DayOfWeek.FRIDAY, staffList);
        saturday = new DayCard(DayOfWeek.SATURDAY, staffList);
        sunday = new DayCard(DayOfWeek.SUNDAY, staffList);
        weekShiftsPane.getChildren().addAll(monday.getRoot(), tuesday.getRoot(), wednesday.getRoot(),
                thursday.getRoot(), friday.getRoot(), saturday.getRoot(), sunday.getRoot());
    }

    public static WeekShiftsPane getInstance(ObservableList<Person> stafflist) {
        if (main_instance == null) {
            main_instance = new WeekShiftsPane(stafflist);
        }
        return main_instance;
    }

    public static void refreshDayCard(DayOfWeek day, Slot slot) {
        switch (day) {
        case MONDAY:
            monday.refreshSlotCard(slot);
            break;
        case TUESDAY:
            tuesday.refreshSlotCard(slot);
            break;
        case WEDNESDAY:
            wednesday.refreshSlotCard(slot);
            break;
        case THURSDAY:
            thursday.refreshSlotCard(slot);
            break;
        case FRIDAY:
            friday.refreshSlotCard(slot);
            break;
        case SATURDAY:
            saturday.refreshSlotCard(slot);
            break;
        case SUNDAY:
            sunday.refreshSlotCard(slot);
            break;
        }
    }
}

