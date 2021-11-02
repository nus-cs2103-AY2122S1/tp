package seedu.address.ui;

import java.time.LocalDate;
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
        int currentDay = LocalDate.now().getDayOfWeek().getValue();
        weekShiftsPane.getChildren().clear();
        weekShiftsPane.getChildren().addAll(new DayCard(0, staffList, period).getRoot(),
                new DayCard(1, staffList, period).getRoot(),
                new DayCard(2, staffList, period).getRoot(),
                new DayCard(3, staffList, period).getRoot(),
                new DayCard(4, staffList, period).getRoot(),
                new DayCard(5, staffList, period).getRoot(),
                new DayCard(6, staffList, period).getRoot());
    }

}
