package seedu.address.ui;

import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.friend.Friend;

/**
 * Panel containing the list of persons.
 */
public class FriendListPanel extends UiPart<Region> {
    private static final String FXML = "FriendListPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(FriendListPanel.class);

    @FXML
    private ListView<Friend> personListView;

    /**
     * Creates a {@code FriendListPanel} with the given {@code ObservableList}.
     */
    public FriendListPanel(ObservableList<Friend> friendList) {
        super(FXML);
        personListView.setItems(friendList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        personListView.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<Friend>() {
                    @Override
                    public void changed(ObservableValue<? extends Friend> observable, Friend oldFriend,
                                        Friend newFriend) {
                        // TODO: call API from MainWindow to change the friend mounted on MainCard
                        System.out.println(newFriend);
                    }
                });
    }


    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Friend> {
        @Override
        protected void updateItem(Friend friend, boolean empty) {
            super.updateItem(friend, empty);

            if (empty || friend == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FriendCard(friend, getIndex() + 1).getRoot());
            }
        }
    }

}
