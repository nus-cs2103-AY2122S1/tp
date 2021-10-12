package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.friend.Friend;
import seedu.address.model.game.Game;

public class GameMainCard extends UiPart<Region> {
    private static final String FXML = "GameMainCard.fxml";
    private static final String GAME_TITLE = "Game: %s";

    private final Logger logger = LogsCenter.getLogger(GameMainCard.class);

    @FXML
    private Label gameTitle;

    @FXML
    private ListView<Friend> friendListView;

    private Game currentGame;

    /**
     * Creates a {@code GameMainCard} with the given {@Code Game} and {@code ObservableList}.
     */
    public GameMainCard(Game game, ObservableList<Friend> friendList) {
        super(FXML);
        gameTitle.setText(String.format(GAME_TITLE, game.getGameId()));
        friendListView.setItems(friendList);
        friendListView.setCellFactory(listView -> new FriendListViewCell());
        this.currentGame = game;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Friend} using a {@code FriendCard}.
     */
    class FriendListViewCell extends ListCell<Friend> {
        @Override
        protected void updateItem(Friend friend, boolean empty) {
            super.updateItem(friend, empty);

            if (empty || friend == null) {
                setGraphic(null);
                setText(null);
            } else {
                Label gameList = new Label();
                gameList.setText(friend.getFriendId().value);
                setGraphic(gameList);
            }
        }
    }
}
