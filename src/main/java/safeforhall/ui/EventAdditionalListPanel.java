package safeforhall.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import safeforhall.commons.core.LogsCenter;
import safeforhall.model.event.Event;

/**
 * Panel containing the list of Events.
 */
public class EventAdditionalListPanel extends UiPart<Region> {
    private static final String FXML = "EventAdditionalListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(EventAdditionalListPanel.class);

    @FXML
    private ListView<Event> eventAdditionalListView;

    /**
     * Creates a {@code EventAdditionalListPanel} with the given {@code ObservableList}.
     */
    public EventAdditionalListPanel(ObservableList<Event> eventList) {
        super(FXML);
        eventAdditionalListView.setItems(eventList);
        eventAdditionalListView.setCellFactory(listView -> new EventAdditionalListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Event} using a {@code EventAdditionalCard}.
     */
    class EventAdditionalListViewCell extends ListCell<Event> {
        @Override
        protected void updateItem(Event event, boolean empty) {
            super.updateItem(event, empty);

            if (empty || event == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new EventAdditionalCard(event).getRoot());
            }
        }
    }

}
