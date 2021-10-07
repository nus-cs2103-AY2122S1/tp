package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.participant.Participant;

/**
 * Panel containing the list of participants.
 */
public class ParticipantListPanel extends UiPart<Region> {
    private static final String FXML = "ParticipantListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ParticipantListPanel.class);

    @FXML
    private ListView<Participant> participantListView;

    /**
     * Creates a {@code ParticipantListPanel} with the given {@code ObservableList}.
     */
    public ParticipantListPanel(ObservableList<Participant> participantList) {
        super(FXML);
        participantListView.setItems(participantList);
        participantListView.setCellFactory(listView -> new ParticipantListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Participant} using a {@code ParticipantCard}.
     */
    class ParticipantListViewCell extends ListCell<Participant> {
        @Override
        protected void updateItem(Participant participant, boolean empty) {
            super.updateItem(participant, empty);

            if (empty || participant == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ParticipantCard(participant, getIndex() + 1).getRoot());
            }
        }
    }

}
