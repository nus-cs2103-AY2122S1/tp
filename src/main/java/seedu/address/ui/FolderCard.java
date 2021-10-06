package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.folder.Folder;

/**
 * An UI component that displays information of a {@code Folder}.
 */
public class FolderCard extends UiPart<Region> {

    private static final String FXML = "FolderListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Folder folder;

    @FXML
    private HBox cardPane;
    @FXML
    private Label folderName;
    @FXML
    private Label id;

    /**
     * Creates a {@code FolderCode} with the given {@code Folder} and index to display.
     */
    public FolderCard(Folder folder, int displayedIndex) {
        super(FXML);
        this.folder = folder;
        id.setText(displayedIndex + ". ");
        folderName.setText(folder.getFolderName().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FolderCard)) {
            return false;
        }

        // state check
        FolderCard card = (FolderCard) other;
        return id.getText().equals(card.id.getText())
                && folder.equals(card.folder);
    }
}
