package seedu.notor.ui;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
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
    private Label subGroups;
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
        phone.setText(Objects.requireNonNullElse(person.getPhone().value, ""));
        email.setText(Objects.requireNonNullElse(person.getEmail().value, ""));
        if (!person.getNoteSavedDate().isEmpty()) {
            note.setText(person.getNote().getNoEmptyLineNote());
            noteLastModified.setText(person.getNoteSavedDate());
            noteLastModified.setTextAlignment(TextAlignment.CENTER);
        } else {
            vBox.setManaged(false);
            note.setManaged(false);
            noteLastModified.setManaged(false);
        }
        groups.setText(Arrays.toString(person.getSuperGroups().toArray()));
        subGroups.setText(Arrays.toString(person.getDisplaySubGroups().toArray()));
        person.getTags().stream()
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
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
