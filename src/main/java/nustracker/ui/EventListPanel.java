package nustracker.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import nustracker.model.event.Event;

/**
 * Panel containing the list of events.
 */
public class EventListPanel extends UiPart<Region> {

    private static final String FXML = "EventListPanel.fxml";

    @FXML
    private ListView<Event> eventListView;

    /**
     * Creates a {@code EventListPanel} with the given {@code ObservableList}.
     */
    public EventListPanel(ObservableList<Event> eventList) {
        super(FXML);
        eventListView.setItems(eventList);
        eventListView.setCellFactory(listView -> new EventListViewCell());

        focusOnItem(0);
    }

    /**
     * Selects the first event, upon execution
     */
    public void focusOnItem(int index) {
        eventListView.getSelectionModel().select(index);
        eventListView.getFocusModel().focus(index);
        eventListView.scrollTo(index);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of an {@code Event} using an {@code EventCard}.
     */
    class EventListViewCell extends ListCell<Event> {
        @Override
        protected void updateItem(Event event, boolean empty) {
            super.updateItem(event, empty);

            if (empty || event == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new EventCard(event).getRoot());
            }
        }
    }
}
