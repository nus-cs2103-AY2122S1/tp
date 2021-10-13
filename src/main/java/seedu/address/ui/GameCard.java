package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.game.Game;

/**
 * An UI component that displays information of a {@code Game}.
 */
public class GameCard extends UiPart<Region> {

    private static final String FXML = "GameListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Game game;

    @FXML
    private HBox cardPane;
    @FXML
    private Label gameId;
    @FXML
    private Label id;
    @FXML
    private FlowPane games;

    /**
     * Creates a {@code GameCode} with the given {@code Game} and index to display.
     */
    public GameCard(Game game, int displayedIndex) {
        super(FXML);
        this.game = game;
        id.setText(displayedIndex + ". ");
        gameId.setText(game.getGameId().value);
        // game.getGames().stream()
        // .sorted(Comparator.comparing(game -> game.getGameId().value))
        // .forEach(game -> games.getChildren().add(new Label(game.getGameId().value + " ")));
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
