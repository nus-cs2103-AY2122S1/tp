package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.friend.Friend;
import seedu.address.model.game.Game;

/**
 * A panel containing the list of Games.
 */
public class GameListPanel extends UiPart<Region> {
    private static final String FXML = "GameListPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(GameListPanel.class);

    private final ObservableList<Friend> friendList;

    @FXML
    private ListView<Game> gameListView;

    /**
     * Creates a {@code GameListPanel} with the given {@code ObservableList}.
     */
    public GameListPanel(ObservableList<Game> gameList, ObservableList<Friend> friendList) {
        super(FXML);
        gameListView.setItems(gameList);
        gameListView.setCellFactory(listView -> new GameListViewCell());
        this.friendList = friendList;
    }

    /**
     * Returns the number of friends with the game in their GameFriendLinks.
     *
     * @return Number of friends.
     */
    private int numberOfFriendsWithGame(Game game) {
        return (int) friendList.stream().filter(friend -> friend.hasGameAssociation(game)).count();
    }

    public void updateNumberOfFriends() {
        gameListView.setCellFactory(listView -> new GameListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Game} using a {@code GameCard}.
     */
    class GameListViewCell extends ListCell<Game> {
        @Override
        protected void updateItem(Game game, boolean empty) {
            super.updateItem(game, empty);

            if (empty || game == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new GameCard(game, getIndex() + 1, numberOfFriendsWithGame(game)).getRoot());
            }
        }
    }

}
