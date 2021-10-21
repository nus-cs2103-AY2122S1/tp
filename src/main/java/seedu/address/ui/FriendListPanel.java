package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.friend.Friend;
import seedu.address.model.game.Game;

/**
 * Panel containing the list of persons.
 */
public class FriendListPanel extends UiPart<Region> {
    private static final String FXML = "FriendListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(FriendListPanel.class);

    /**
     * The default FriendMainCard with no friend's data to be used when a non-get command is run.
     */
    private static final FriendMainCard DEFAULT_FRIEND_MAIN_CARD = new FriendMainCard();

    private FriendMainCard friendMainCard;

    @FXML
    private ListView<Friend> personListView;

    @FXML
    private StackPane friendMainCardPlaceholder;



    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public FriendListPanel(ObservableList<Friend> friendList) {
        super(FXML);
        friendMainCard = DEFAULT_FRIEND_MAIN_CARD;
        friendMainCardPlaceholder.getChildren().add(friendMainCard.getRoot());
        personListView.setItems(friendList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Updates the {@Code FriendMainCard} to contain details about a friend.
     * @param friend The friend whose details are to be displayed.
     * @param gameObservableList The list of games to be filtered and displayed.
     */
    public void updateFriendMainCardWithFriend(Friend friend, ObservableList<Game> gameObservableList) {
        friendMainCardPlaceholder.getChildren().remove(friendMainCard.getRoot());
        friendMainCard = new FriendMainCard(friend, gameObservableList);
        friendMainCardPlaceholder.getChildren().add(friendMainCard.getRoot());
    }

    /**
     * Sets the FriendMainCard to the default setting.
     */
    public void setFriendMainCardToDefault() {
        friendMainCardPlaceholder.getChildren().remove(friendMainCard.getRoot());
        friendMainCard = DEFAULT_FRIEND_MAIN_CARD;
        friendMainCardPlaceholder.getChildren().add(friendMainCard.getRoot());
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
