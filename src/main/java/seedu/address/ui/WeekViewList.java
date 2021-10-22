package seedu.address.ui;

import java.time.DayOfWeek;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.person.Shift;

/**
 * Panel containing the list of persons.
 */
public class WeekViewList extends UiPart<Region> {
    private static final String FXML = "WeekViewList.fxml";
    private final Logger logger = LogsCenter.getLogger(WeekViewList.class);

    @FXML
    private ListView<DayCard> weekViewList;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public WeekViewList(ObservableList<Person> staffList) {
        super(FXML);
        weekViewList = new ListView<>();
        weekViewList.getItems().addAll(new DayCard(DayOfWeek.MONDAY, staffList),
                new DayCard(DayOfWeek.TUESDAY, staffList), new DayCard(DayOfWeek.WEDNESDAY, staffList),
                new DayCard(DayOfWeek.THURSDAY, staffList), new DayCard(DayOfWeek.FRIDAY, staffList),
                new DayCard(DayOfWeek.SATURDAY, staffList), new DayCard(DayOfWeek.SUNDAY, staffList));
    }
}
