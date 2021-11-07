package seedu.address.ui;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import seedu.address.model.friend.Friend;
import seedu.address.model.gamefriendlink.GameFriendLink;

/**
 * A table showing games that are linked to a friend, and their corresponding usernames and skills levels.
 */
public class FriendMainCardTable extends UiPart<Region> {

    private static final String FXML = "FriendMainCardTable.fxml";

    @FXML
    private TableView<GameFriendLink> tableView;

    @FXML
    private TableColumn<GameFriendLink, String> gameCol;

    @FXML
    private TableColumn<GameFriendLink, String> usernameCol;

    @FXML
    private TableColumn<GameFriendLink, String> skillCol;

    /**
     * Constructor for FriendMainCardTable Ui component.
     *
     * @param friend Friend to display information for.
     */
    public FriendMainCardTable(Friend friend) {
        super(FXML);

        tableView.setItems(FXCollections.observableList(new ArrayList<>(friend.getGameFriendLinks().values())));

        gameCol.setCellValueFactory(link -> new SimpleStringProperty(link.getValue().getGameId().toString()));
        usernameCol.setCellValueFactory(link -> new SimpleStringProperty(link.getValue().getUserName().toString()));
        skillCol.setCellValueFactory(link -> new SimpleStringProperty(link.getValue().getSkillValue().toString()));

    }

    public FriendMainCardTable() {
        super(FXML);
    }
}
