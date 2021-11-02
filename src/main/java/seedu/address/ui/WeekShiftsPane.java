package seedu.address.ui;

import java.time.DayOfWeek;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.parser.ParserUtil;
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
        Period period = ParserUtil.initializePeriodToThisWeek();
        setChildren(staffList, period);
    }

    public void setChildren(ObservableList<Person> staffList, Period period) {
        weekShiftsPane.getChildren().clear();
        weekShiftsPane.getChildren().addAll(new DayCard(DayOfWeek.MONDAY, staffList, period).getRoot(),
                new DayCard(DayOfWeek.TUESDAY, staffList, period).getRoot(),
                new DayCard(DayOfWeek.WEDNESDAY, staffList, period).getRoot(),
                new DayCard(DayOfWeek.THURSDAY, staffList, period).getRoot(),
                new DayCard(DayOfWeek.FRIDAY, staffList, period).getRoot(),
                new DayCard(DayOfWeek.SATURDAY, staffList, period).getRoot(),
                new DayCard(DayOfWeek.SUNDAY, staffList, period).getRoot());
    }
}
