package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.friend.Friend;
import seedu.address.model.game.Game;
import seedu.address.model.game.GameFriendLinksContainsGameIdPredicate;

public class FriendMainCard extends UiPart<Region> {
    private static final String FXML = "FriendMainCard.fxml";
    private static final String FRIEND_TITLE = "%s \n Friend ID: %s";

    private final Logger logger = LogsCenter.getLogger(FriendMainCard.class);

    @FXML
    private Label friendTitle;

    @FXML
    private FlowPane games;

    @FXML
    private ListView<Game> gameListView;

    private Friend currentFriend;

    /**
     * Creates a {@code FriendMainCard}.
     */
    public FriendMainCard() {
        super(FXML);
        friendTitle.setText("No friend selected");
    }

    /**
     * Creates a {@code FriendMainCard} with the given {@Code Friend} and {@code ObservableList}.
     */
    public FriendMainCard(Friend friend, ObservableList<Game> gamesList) {
        super(FXML);
        friendTitle.setText(String.format(FRIEND_TITLE, friend.getName(), friend.getFriendId()));
        ObservableList<Game> filteredGamesList = filterGamesList(friend, gamesList);
        filteredGamesList.stream()
                .sorted(Comparator.comparing(game -> game.getGameId().value))
                .forEach(game -> {
                    Label label = new Label(game.getGameId().value);
                    label.setTextFill(Color.WHITE);
                    label.setBackground(new Background(new BackgroundFill(Color.rgb(114, 137, 218, 1),
                            new CornerRadii(10.0),
                            new Insets(-5.0, -7.0, -5.0, -7.0))));

                    games.getChildren().add(label);
                });
        this.currentFriend = friend;
    }

    private ObservableList<Game> filterGamesList(Friend friend, ObservableList<Game> gamesList) {
        FilteredList<Game> filteredGames = new FilteredList<>(gamesList);
        filteredGames.setPredicate(new GameFriendLinksContainsGameIdPredicate(friend));
        return filteredGames;
    }
}
