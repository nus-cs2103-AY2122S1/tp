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

public class FriendMainCard extends UiPart<Region> {
    private static final String FXML = "FriendMainCard.fxml";
    private final Logger logger = LogsCenter.getLogger(FriendMainCard.class);

    @FXML
    private Label friendTitle;

    @FXML
    private ListView<Game> gameListView;

    private final static String FRIEND_TITLE = "%s \n Friend ID: %s";


    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public FriendMainCard(Friend friend, ObservableList<Game> gameList) {
        super(FXML);
        friendTitle.setText(String.format(FRIEND_TITLE, friend.getName(), friend.getFriendId()));
        gameListView.setItems(gameList);
        gameListView.setCellFactory(listView -> new GameListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class GameListViewCell extends ListCell<Game> {
        @Override
        protected void updateItem(Game game, boolean empty) {
            super.updateItem(game, empty);

            if (empty || game == null) {
                setGraphic(null);
                setText(null);
            } else {
                Label gameList = new Label();
                gameList.setText(game.getGameId().value);
                setGraphic(gameList);
            }
        }
    }
}
