package seedu.notor.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.notor.model.common.Note;
import seedu.notor.model.group.SubGroup;


/**
 * An UI component that displays information of a {@code SubGroup}.
 * Not implemented due to time constraints
 */
public class SubgroupViewCard extends UiPart<Region> {

    private static final String FXML = "SubgroupViewCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final SubGroup subgroup;

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
    private Label parentGroup;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code SuperGroupCode} with the given {@code SuperGroup} and index to display.
     */
    public SubgroupViewCard(SubGroup subgroup, int displayedIndex) {
        super(FXML);
        this.subgroup = subgroup;
        name.setText(subgroup.getName());
        if (!subgroup.getNote().equals(Note.EMPTY_NOTE)) {
            note.setText(subgroup.getNote().getNoEmptyLineNote());
            noteLastModified.setText(subgroup.getNoteSavedDate());
        } else {
            vBox.setManaged(false);
            note.setManaged(false);
            noteLastModified.setManaged(false);
        }
        parentGroup.setText("Parent Group: " + subgroup.getParent());
        numberOfPersons.setText("Number of Persons: " + subgroup.getPeople().size());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SubgroupViewCard)) {
            return false;
        }

        // state check
        SubgroupViewCard card = (SubgroupViewCard) other;
        return subgroup.equals(card.subgroup);
    }
}
