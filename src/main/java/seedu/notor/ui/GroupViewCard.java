package seedu.notor.ui;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.notor.model.common.Note;
import seedu.notor.model.group.SuperGroup;


/**
 * An UI component that displays information of a {@code SuperGroup}.
 */
public class GroupViewCard extends UiPart<Region> {

    private static final String FXML = "GroupViewCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final SuperGroup group;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label note;
    @FXML
    private Label noteLastModified;
    @FXML
    private VBox vBox;
    @FXML
    private Label numberOfPersons;
    @FXML
    private Label numberOfSubgroups;

    /**
     * Creates a {@code SuperGroupCode} with the given {@code SuperGroup} and index to display.
     */
    public GroupViewCard(SuperGroup group, int displayedIndex) {
        super(FXML);
        this.group = group;
        name.setText(group.getName());
        if (!group.getNote().equals(Note.EMPTY_NOTE)) {
            note.setText(group.getNote().getNoEmptyLineNote());
            noteLastModified.setText(group.getNoteSavedDate());
        } else {
            vBox.setManaged(false);
            note.setManaged(false);
            noteLastModified.setManaged(false);
        }
        numberOfPersons.setText("Number of Persons: " + group.getPeople().size());
        numberOfSubgroups.setText("Number of SubGroups: " + group.getSubGroups().asUnmodifiableObservableList().size());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GroupViewCard)) {
            return false;
        }

        // state check
        GroupViewCard card = (GroupViewCard) other;
        return group.equals(card.group);
    }
}
