package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.game.Game;

/**
 * A UI component that displays information of a {@code Game}.
 */
public class GameCard extends UiPart<Region> {

    private static final String FXML = "GameListCard.fxml";
    private static final String NUMBER_OF_FRIENDS_PLURAL = "Played by %s friends";
    private static final String NUMBER_OF_FRIENDS_SINGULAR = "Played by %s friend";
    public final Game game;

    @FXML
    private HBox cardPane;
    @FXML
    private Label gameId;
    @FXML
    private Label id;
    @FXML
    private Label numberOfFriends;

    /**
     * Creates a {@code GameCode} with the given {@code Game} and index to display.
     */
    public GameCard(Game game, int displayedIndex, int friends) {
        super(FXML);
        this.game = game;
        id.setText(displayedIndex + ". ");
        gameId.setText(game.getGameId().value);
        if (friends == 1) {
            numberOfFriends.setText(String.format(NUMBER_OF_FRIENDS_SINGULAR, friends));
        } else {
            numberOfFriends.setText(String.format(NUMBER_OF_FRIENDS_PLURAL, friends));
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GameCard)) {
            return false;
        }

        // state check
        GameCard card = (GameCard) other;
        return id.getText().equals(card.id.getText())
                && game.equals(card.game);
    }
}
