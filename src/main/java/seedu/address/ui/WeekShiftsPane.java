package seedu.address.ui;

import java.time.DayOfWeek;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class WeekShiftsPane extends UiPart<Region> {
    private static final String FXML = "WeekShiftsPane.fxml";
    private final Logger logger = LogsCenter.getLogger(WeekShiftsPane.class);

    @FXML
    private HBox weekShiftsPane;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public WeekShiftsPane(ObservableList<Person> staffList) {
        super(FXML);
        weekShiftsPane.getChildren().addAll(new DayCard(DayOfWeek.MONDAY, staffList).getRoot(),
                new DayCard(DayOfWeek.TUESDAY, staffList).getRoot(),
                new DayCard(DayOfWeek.WEDNESDAY, staffList).getRoot(),
                new DayCard(DayOfWeek.THURSDAY, staffList).getRoot(),
                new DayCard(DayOfWeek.FRIDAY, staffList).getRoot(),
                new DayCard(DayOfWeek.SATURDAY, staffList).getRoot(),
                new DayCard(DayOfWeek.SUNDAY, staffList).getRoot());
    }
}
