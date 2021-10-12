package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.friend.Friend;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class FriendCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Friend friend;

    @FXML
    private HBox cardPane;
    @FXML
    private Label friendId;
    @FXML
    private Label friendName;
    @FXML
    private Label id;
    @FXML
    private FlowPane games;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public FriendCard(Friend friend, int displayedIndex) {
        super(FXML);
        this.friend = friend;
        id.setText(displayedIndex + ". ");
        friendId.setText(friend.getFriendId().value);
        friendName.setText(friend.getName().fullName);
        friend.getGameFriendLinks().stream()
                .sorted(Comparator.comparing(game -> game.getGameId().value))
                .forEach(game -> games.getChildren().add(new Label(game.getGameId().value + " ")));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FriendCard)) {
            return false;
        }

        // state check
        FriendCard card = (FriendCard) other;
        return id.getText().equals(card.id.getText())
                && friend.equals(card.friend);
    }
}
