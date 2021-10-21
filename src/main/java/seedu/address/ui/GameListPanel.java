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
 * Panel containing the list of Games.
 */
public class GameListPanel extends UiPart<Region> {
    private static final String FXML = "GameListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(GameListPanel.class);

    /**
     * The default GameMainCard with no game's data to be used when a non-get command is run.
     */
    private static final GameMainCard DEFAULT_GAME_MAIN_CARD = new GameMainCard();

    private GameMainCard gameMainCard;

    @FXML
    private ListView<Game> gameListView;

    @FXML
    private StackPane gameMainCardPlaceholder;

    /**
     * Creates a {@code GameListPanel} with the given {@code ObservableList}.
     */
    public GameListPanel(ObservableList<Game> gameList) {
        super(FXML);
        gameMainCard = DEFAULT_GAME_MAIN_CARD;
        gameMainCardPlaceholder.getChildren().add(gameMainCard.getRoot());
        gameListView.setItems(gameList);
        gameListView.setCellFactory(listView -> new GameListViewCell());
    }

    /**
     * Updates the {@Code GameMainCard} to contain details about a game.
     * @param game The game whose details are to be displayed.
     * @param friendObservableList The list of friends to be filtered and displayed.
     */
    public void updateGameMainCardWithGame(Game game, ObservableList<Friend> friendObservableList) {
        gameMainCardPlaceholder.getChildren().remove(gameMainCard.getRoot());
        gameMainCard = new GameMainCard(game, friendObservableList);
        gameMainCardPlaceholder.getChildren().add(gameMainCard.getRoot());
    }

    /**
     * Sets the GameMainCard to the default setting.
     */
    public void setGameMainCardToDefault() {
        gameMainCardPlaceholder.getChildren().remove(gameMainCard.getRoot());
        gameMainCard = DEFAULT_GAME_MAIN_CARD;
        gameMainCardPlaceholder.getChildren().add(gameMainCard.getRoot());
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
                setGraphic(new GameCard(game, getIndex() + 1).getRoot());
            }
        }
    }

}
