package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

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
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label phonePlaceholder;
    @FXML
    private Label phone;
    @FXML
    private Label emailPlaceholder;
    @FXML
    private Label email;
    @FXML
    private Label ppPlaceholder;
    @FXML
    private Label parentPhone;
    @FXML
    private Label pePlaceholder;
    @FXML
    private Label parentEmail;
    @FXML
    private Label address;
    @FXML
    private Label feePlaceholder;
    @FXML
    private Label outstandingFee;
    @FXML
    private Label schPlaceholder;
    @FXML
    private Label school;
    @FXML
    private Label streamPlaceholder;
    @FXML
    private Label acadStream;
    @FXML
    private Label lvlPlaceholder;
    @FXML
    private Label acadLevel;
    @FXML
    private Label remarkPlaceholder;
    @FXML
    private Label remark;
    @FXML
    private VBox tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        address.setText(person.getAddress().value);

        phonePlaceholder.setManaged(!person.getPhone().isEmpty());
        phone.setManaged(!person.getPhone().isEmpty());
        phone.setText(person.getPhone().value);

        emailPlaceholder.setManaged(!person.getEmail().isEmpty());
        email.setManaged(!person.getEmail().isEmpty());
        email.setText(person.getEmail().value);

        ppPlaceholder.setManaged(!person.getParentPhone().isEmpty());
        parentPhone.setManaged(!person.getParentPhone().isEmpty());
        parentPhone.setText(person.getParentPhone().value);

        pePlaceholder.setManaged(!person.getParentEmail().isEmpty());
        parentEmail.setManaged(!person.getParentEmail().isEmpty());
        parentEmail.setText(person.getParentEmail().value);

        schPlaceholder.setManaged(!person.getSchool().isEmpty());
        school.setManaged(!person.getSchool().isEmpty());
        school.setText(person.getSchool().value);

        streamPlaceholder.setManaged(!person.getAcadStream().isEmpty());
        acadStream.setManaged(!person.getAcadStream().isEmpty());
        acadStream.setText(person.getAcadStream().value);

        acadLevel.setManaged(!person.getAcadLevel().isEmpty());
        lvlPlaceholder.setManaged(!person.getAcadLevel().isEmpty());
        acadLevel.setText(person.getAcadLevel().value);

        remarkPlaceholder.setManaged(!person.getRemark().isEmpty());
        remark.setManaged(!person.getRemark().isEmpty());
        remark.setText(person.getRemark().value);

        feePlaceholder.setManaged(!person.getFee().isEmpty());
        outstandingFee.setManaged(!person.getFee().isEmpty());
        outstandingFee.setText("$" + person.getFee().value);

        tags.setManaged(!person.getTags().isEmpty());
        person.getTags().stream()
                .sorted(Comparator.comparing(Tag::getTagName))
                .forEach(tag -> tags.getChildren().add(createTagLabel(tag.getTagName())));
    }

    private Label createTagLabel(String tag) {
        Label label = new Label(tag);
        label.setWrapText(true);
        return label;
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
