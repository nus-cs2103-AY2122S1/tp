package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendName;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class FriendCard extends UiPart<Region> {

    private static final String FXML = "FriendListCard.fxml";

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
    private Label friendName;
    @FXML
    private Label id;
    @FXML
    private FlowPane games;

    /**
     * Creates a {@code FriendCard} with the given {@code Friend} and index to display.
     */
    public FriendCard(Friend friend, int displayedIndex) {
        super(FXML);
        this.friend = friend;
        id.setText(displayedIndex + ". ");
        friendName.setText(formatFriendNameId(friend));
        games.setHgap(20.0);
        games.setVgap(15.0);
        friend.getGameFriendLinks().stream()
                .sorted(Comparator.comparing(game -> game.getGameId().value))
                .forEach(game -> {
                    Label label = new Label(game.getGameId().value);
                    label.setBackground(new Background(new BackgroundFill(Color.rgb(114, 137, 218, 1),
                            new CornerRadii(10.0),
                            new Insets(-5.0, -7.0, -5.0, -7.0))));

                    games.getChildren().add(label);
                });
    }

    /**
     * Formats the friendName for the {@Code FriendCard}.
     * @param friend The friend whose id and name (if available) is to be displayed.
     * @return String with the friend's id and name.
     */
    private String formatFriendNameId(Friend friend) {
        if (friend.getName().equals(FriendName.DEFAULT_FRIEND_NAME)) {
            return friend.getFriendId().toString();
        } else {
            return friend.getFriendId() + " (" + friend.getName().fullName + ")";
        }
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
