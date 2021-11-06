package seedu.address.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import seedu.address.model.friend.Friend;
import seedu.address.model.game.Game;
import seedu.address.model.gamefriendlink.GameFriendLink;

/**
 * A table showing friends that are linked a game, and their corresponding usernames and skills levels.
 */
public class GameMainCardTable extends UiPart<Region> {

    private static final String FXML = "GameMainCardTable.fxml";

    @FXML
    private TableView<Friend> tableView;

    @FXML
    private TableColumn<Friend, String> friendCol;

    @FXML
    private TableColumn<Friend, String> usernameCol;

    @FXML
    private TableColumn<Friend, String> skillCol;

    /**
     * Constructor to create GameMainCardTable Ui component.
     *
     * @param friendList List of friend that is linked to the Game to get.
     * @param gameToGet  Game to get.
     */
    public GameMainCardTable(ObservableList<Friend> friendList, Game gameToGet) {
        super(FXML);

        tableView.setItems(friendList);

        friendCol.setCellValueFactory(link -> new SimpleStringProperty(link.getValue().getFriendId().toString()));
        usernameCol.setCellValueFactory(link -> new SimpleStringProperty(getGameFriendLink(link.getValue(), gameToGet)
                .getUserName()
                .toString()));
        skillCol.setCellValueFactory(link -> new SimpleStringProperty(getGameFriendLink(link.getValue(), gameToGet)
                .getSkillValue()
                .toString()));
    }

    /**
     * Default constructor to create an empty table.
     */
    public GameMainCardTable() {
        super(FXML);
    }

    private GameFriendLink getGameFriendLink(Friend friend, Game gameToGet) {
        return friend.getGameFriendLinks()
                .values().stream()
                .filter(gameFriendLink -> gameFriendLink.getGameId().equals(gameToGet.getGameId()))
                .findFirst()
                .get();
    }
}
