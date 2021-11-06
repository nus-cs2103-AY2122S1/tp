package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.friend.Day;
import seedu.address.model.friend.Friend;

/**
 * A panel containing the list of days.
 */
public class FriendSchedulePanel extends UiPart<Region> {
    private static final String FXML = "FriendSchedulePanel.fxml";

    public final Friend friend;
    private final Logger logger = LogsCenter.getLogger(FriendSchedulePanel.class);

    @FXML
    private ListView<Day> dayListView;

    /**
     * Creates a {@code FriendSchedulePanel} with the given {@code Friend}.
     */
    public FriendSchedulePanel(Friend friend) {
        super(FXML);
        this.friend = friend;
        dayListView.setItems(friend.getSchedule().getSchedule());
        dayListView.setCellFactory(listView -> new DayListViewCell());
    }


    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class DayListViewCell extends ListCell<Day> {
        @Override
        protected void updateItem(Day day, boolean empty) {
            super.updateItem(day, empty);

            if (empty || day == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FriendScheduleDayCard(day).getRoot());
            }
        }
    }

}
