package seedu.address.ui;

import javafx.fxml.FXML;

import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.participant.Participant;

public class ParticipantListPanel extends UiPart<Region> {
    private static final String FXML = "ParticipantListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Participant> participantListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public ParticipantListPanel(ObservableList<Participant> participantList) {
        super(FXML);
        participantListView.setItems(participantList);
        participantListView.setCellFactory(listView -> new ParticipantListPanel.ParticipantListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class ParticipantListViewCell extends ListCell<Participant> {
        @Override
        protected void updateItem(Participant participant, boolean empty) {
            super.updateItem(participant, empty);

            if (empty || participant == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(participant, getIndex() + 1).getRoot());
            }
        }
    }

}
