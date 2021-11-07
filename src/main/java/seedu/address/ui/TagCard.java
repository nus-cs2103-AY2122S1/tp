package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.tag.Tag;

/**
 * A UI component that displays information of a {@code Tag}.
 */
public class TagCard extends UiPart<Region> {

    private static final String FXML = "TagListCard.fxml";

    public final Tag tag;

    @FXML
    private HBox cardPane;
    @FXML
    private Label tagName;
    @FXML
    private Label numDuplicates;

    /**
     * Constructs a TagCard object with the specified tag.
     *
     * @param tag Tag to be displayed in the TagCard.
     */
    public TagCard(Tag tag, Integer count) {
        super(FXML);
        this.tag = tag;
        tagName.setText(tag.toString());
        numDuplicates.setText(String.valueOf(count));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagCard)) {
            return false;
        }

        // state check
        TagCard card = (TagCard) other;
        return tagName.getText().equals(card.tagName.getText())
                && tag.equals(card.tag);
    }
}
