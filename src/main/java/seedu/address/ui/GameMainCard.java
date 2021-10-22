package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendGameFriendLinksContainsGamePredicate;
import seedu.address.model.game.Game;
import seedu.address.ui.util.SampleStyles;


public class GameMainCard extends UiPart<Region> {
    private static final String FXML = "GameMainCard.fxml";
    private static final String GAME_TITLE = "Game: %s";
    private static final String DEFAULT_GAME_TITLE = "No game selected";

    private final Logger logger = LogsCenter.getLogger(GameMainCard.class);

    @FXML
    private Label gameTitle;

    @FXML
    private ListView<Friend> friendListView;

    @FXML
    private FlowPane friends;

    private Game currentGame;

    /**
     * Creates a {@code GameMainCard}.
     */
    public GameMainCard() {
        super(FXML);
        gameTitle.setText(DEFAULT_GAME_TITLE);
    }

    /**
     * Creates a {@code GameMainCard} with the given {@Code Game} and {@code ObservableList}.
     */
    public GameMainCard(Game game, ObservableList<Friend> friendList) {
        super(FXML);
        gameTitle.setText(String.format(GAME_TITLE, game.getGameId()));
        ObservableList<Friend> filteredFriendList = filterFriendsList(game, friendList);
        filteredFriendList.stream()
                .sorted(Comparator.comparing(friend -> friend.getFriendId().value))
                .forEach(friend -> {
                    Label label = new Label(friend.getFriendId().value);
                    label.setBackground(SampleStyles.BLURPLE_BACKGROUND);
                    friends.getChildren().add(label);
                });
        this.currentGame = game;
    }

    private ObservableList<Friend> filterFriendsList(Game game, ObservableList<Friend> friendList) {
        FilteredList<Friend> filteredFriends = new FilteredList<>(friendList);
        filteredFriends.setPredicate(new FriendGameFriendLinksContainsGamePredicate(game));
        return filteredFriends;
    }

    public Game getCurrentGame() {
        return currentGame;
    }
}
