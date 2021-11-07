package safeforhall.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import safeforhall.commons.core.LogsCenter;
import safeforhall.commons.core.index.Index;
import safeforhall.logic.Logic;
import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.logic.commands.view.ViewEventCommand;
import safeforhall.model.event.Event;

/**
 * Panel containing the list of Events.
 */
public class EventListPanel extends UiPart<Region> {
    private static final String FXML = "EventListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(EventListPanel.class);
    private boolean isResidentTab = true;
    private final Logic logic;

    @FXML
    private ListView<Event> eventListView;

    /**
     * Creates a {@code EventListPanel} with the given {@code ObservableList}.
     */
    public EventListPanel(ObservableList<Event> eventList, Logic logic) {
        super(FXML);
        eventListView.setItems(eventList);
        eventListView.setCellFactory(listView -> new EventListViewCell());
        this.logic = logic;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Event} using a {@code EventCard}.
     */
    class EventListViewCell extends ListCell<Event> {
        @Override
        protected void updateItem(Event event, boolean empty) {
            super.updateItem(event, empty);

            if (empty || event == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new EventCard(event, getIndex() + 1).getRoot());
            }
        }
    }

    @FXML
    private void setSingleEvent() {
        if (!isResidentTab) {
            try {
                new ViewEventCommand(
                        Index.fromZeroBased(eventListView.getSelectionModel().getSelectedIndex()))
                    .setSingleEvent(logic.getModel());
            } catch (IndexOutOfBoundsException e) {
                logger.info("Non card area selected");
            } catch (CommandException e) {
                logger.info("Invalid card selected");
            }
        }
    }

    public void setIsResidentTab(boolean isResidentTab) {
        this.isResidentTab = isResidentTab;
    }
}
