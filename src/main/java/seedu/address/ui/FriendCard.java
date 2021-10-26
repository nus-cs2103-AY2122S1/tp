package seedu.address.ui;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendName;
import seedu.address.model.gamefriendlink.GameFriendLink;
import seedu.address.ui.util.SampleStyles;

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
    private GridPane games;

    /**
     * Creates a {@code FriendCard} with the given {@code Friend} and index to display.
     */
    public FriendCard(Friend friend, int displayedIndex) {
        super(FXML);
        this.friend = friend;
        id.setText(displayedIndex + ". ");
        friendName.setText(formatFriendNameId(friend));
        List<GameFriendLink> toSort = new ArrayList<>();
        for (GameFriendLink game : friend.getGameFriendLinks()) {
            toSort.add(game);
        }
        toSort.sort(Comparator.comparing(game -> game.getGameId().value));
        for (int i = 0; i < toSort.size(); i++) {
            if (i < 2) {
                GameFriendLink game = toSort.get(i);
                Label label = new Label(game.getGameId().value);
                label.setPrefWidth(90);
                label.setAlignment(Pos.CENTER);
                label.setBackground(SampleStyles.BLURPLE_BACKGROUND);
                games.add(label, i, 0);
            } else {
                break;
            }

        }
    }

    /**
     * Formats the friendName for the {@Code FriendCard}.
     * @param friend The friend whose id and name (if available) is to be displayed.
     * @return String with the friend's id and name.
     */
    private String formatFriendNameId(Friend friend) {
        if (friend.getFriendName().equals(FriendName.DEFAULT_FRIEND_NAME)) {
            return friend.getFriendId().toString();
        } else {
            return friend.getFriendId() + " (Name: " + friend.getFriendName().fullName + ")";
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
