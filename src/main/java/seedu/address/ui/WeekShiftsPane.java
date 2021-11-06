package seedu.address.ui;

import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Period;
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
        setChildren(staffList, Period.oneWeekFrom(LocalDate.now()));
    }

    public void setChildren(ObservableList<Person> staffList, Period period) {
        LocalDate firstDay = period.getStartDate();
        weekShiftsPane.getChildren().clear();
        for (int i = 0; i < 7; i++) {
            DayCard nextDayCard = new DayCard(firstDay, i, staffList, period);
            weekShiftsPane.getChildren().add(nextDayCard.getRoot());
            HBox.setHgrow(nextDayCard.getRoot(), Priority.ALWAYS);
        }
    }
}
