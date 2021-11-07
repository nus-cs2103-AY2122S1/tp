package seedu.notor.ui;

import java.util.ArrayList;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.notor.model.common.Note;
import seedu.notor.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label note;
    @FXML
    private Label noteLastModified;
    @FXML
    private VBox vBox;
    @FXML
    private Label groups;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        email.setText(person.getEmail().value);
        if (!person.getNote().equals(Note.EMPTY_NOTE)) {
            note.setText(person.getNote().getNoEmptyLineNote());
            noteLastModified.setText(person.getNoteSavedDate());
        } else {
            vBox.setManaged(false);
            note.setManaged(false);
            noteLastModified.setManaged(false);
        }
        String groupsDisplayText = getGroupsDisplayText();
        if (groupsDisplayText.toString().equals("")) {
            groups.setManaged(false);
        } else {
            groups.setText(groupsDisplayText.toString());
        }
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    private String getGroupsDisplayText() {
        StringBuilder groupsDisplayText = new StringBuilder();
        int i = 0;
        for (String superGroup : person.getDisplaySuperGroups()) {
            groupsDisplayText.append(superGroup);
            ArrayList<String> displaySubGroups = new ArrayList<>();
            for (String subGroup : person.getDisplaySubGroups()) {
                String[] split = subGroup.split("_");
                if (split[0].equals(superGroup)) {
                    displaySubGroups.add(split[1]);
                }
            }
            if (displaySubGroups.size() != 0) {
                groupsDisplayText.append(" (").append(String.join(",", displaySubGroups)).append(")");
            }
            if (i != person.getDisplaySuperGroups().size() - 1) {
                groupsDisplayText.append(", ");
            }
            i += 1;
        }
        return groupsDisplayText.toString();
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
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
