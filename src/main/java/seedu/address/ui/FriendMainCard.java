package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.friend.Friend;
import seedu.address.model.game.Game;
import seedu.address.ui.util.SampleStyles;


public class FriendMainCard extends UiPart<Region> {
    private static final String FXML = "FriendMainCard.fxml";
    private static final String FRIEND_TITLE = "Friend ID: %s \n Name: %s";
    private static final String DEFAULT_FRIEND_TITLE = "No friend selected";

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
        friendTitle.setText(DEFAULT_FRIEND_TITLE);
    }

    /**
     * Creates a {@code FriendMainCard} with the given {@Code Friend} and {@code ObservableList}.
     */
    public FriendMainCard(Friend friend, ObservableList<Game> filteredGamesList) {
        super(FXML);
        friendTitle.setText(String.format(FRIEND_TITLE, friend.getFriendId(), friend.getFriendName()));
        filteredGamesList.stream()
                .sorted(Comparator.comparing(game -> game.getGameId().value))
                .forEach(game -> {
                    Label label = new Label(game.getGameId().value);
                    label.setBackground(SampleStyles.BLURPLE_BACKGROUND);
                    games.getChildren().add(label);
                });
        this.currentFriend = friend;
    }

//    private ObservableList<Game> filterGamesList(Friend friend, ObservableList<Game> gamesList) {
//        FilteredList<Game> filteredGames = new FilteredList<>(gamesList);
//        filteredGames.setPredicate(new GameFriendLinksContainsGameIdPredicate(friend));
//        return filteredGames;
//    }
}
