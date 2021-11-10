package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.tag.Tag;


public class TagCard extends UiPart<Region> {
    private static final String FXML = "TagListCard.fxml";

    public final Tag tag;

    @javafx.fxml.FXML
    private HBox cardPane;

    @FXML
    private Label tagName;

    @FXML
    private Label description;

    /**
     * Creates a new TagCard Object
     */
    public TagCard(Tag tag, int numOfContacts) {
        super(FXML);
        this.tag = tag;
        tagName.setText(tag.tagName);
        description.setText("Contacts under this tag : \n"
                + numOfContacts);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        TagCard card = (TagCard) other;
        return tagName.getText().equals(card.tagName.getText())
                && tag.equals(card.tag);
    }
}
