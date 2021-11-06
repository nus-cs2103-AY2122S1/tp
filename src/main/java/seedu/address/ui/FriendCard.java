package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.friend.Friend;

/**
 * A UI component that displays information of a {@code Person}.
 */
public class FriendCard extends UiPart<Region> {

    public static final String NUMBER_OF_GAMES_SINGULAR = "%s game";
    public static final String NUMBER_OF_GAMES_PLURAL = "%s games";
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
    private Label games;

    /**
     * Creates a {@code FriendCard} with the given {@code Friend} and index to display.
     */
    public FriendCard(Friend friend, int displayedIndex) {
        super(FXML);
        this.friend = friend;
        id.setText(displayedIndex + ". ");
        friendName.setText(formatFriendNameId(friend));
        int numberOfGames = friend.getNumberOfGames();
        String numberOfGamesString = Integer.toString(numberOfGames);
        if (numberOfGames == 1) {
            games.setText(String.format(NUMBER_OF_GAMES_SINGULAR, numberOfGamesString));
        } else {
            games.setText(String.format(NUMBER_OF_GAMES_PLURAL, numberOfGamesString));
        }
    }

    /**
     * Formats the friendName for the {@Code FriendCard}.
     *
     * @param friend The friend whose id and name (if available) is to be displayed.
     * @return String with the friend's id and name.
     */
    private String formatFriendNameId(Friend friend) {
        return friend.getFriendId().toString();
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
