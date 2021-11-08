package seedu.awe.ui.group;

import static java.util.stream.Collectors.joining;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.awe.model.group.Group;
import seedu.awe.model.person.Name;
import seedu.awe.model.person.Person;
import seedu.awe.ui.UiPart;


/**
 * An UI component that displays information of a {@code Group}.
 */
public class GroupCard extends UiPart<Region> {

    private static final String FXML = "GroupListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on Awe level 4</a>
     */

    public final Group group;

    @FXML
    private HBox cardPane;
    @FXML
    private Label groupName;
    @FXML
    private Label id;
    @FXML
    private Label members;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code GroupCode} with the given {@code Group} and index to display.
     */
    public GroupCard(Group group, int displayedIndex) {
        super(FXML);
        this.group = group;
        id.setText(displayedIndex + ". ");
        groupName.setText(group.getGroupName().toString());
        members.setText(group.getMembers().stream()
                .map(Person::getName)
                .map(Name::toString)
                .collect(joining(", ")));
        group.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GroupCard)) {
            return false;
        }

        // state check
        GroupCard card = (GroupCard) other;
        return id.getText().equals(card.id.getText())
                && group.equals(card.group);
    }
}
