package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";
    private static double TEXT_WIDTH_PERC = 0.85;

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

        if (person.getPhone().isEmpty()) {
            phonePlaceholder.setManaged(false);
            phone.setManaged(false);
        } else {
            phone.setText(person.getPhone().value);
        }

        if (person.getEmail().isEmpty()) {
            emailPlaceholder.setManaged(false);
            email.setManaged(false);
        } else {
            email.setText(person.getEmail().value);
        }

        if (person.getParentPhone().isEmpty()) {
            ppPlaceholder.setManaged(false);
            parentPhone.setManaged(false);
        } else {
            parentPhone.setText(person.getParentPhone().value);
        }

        if (person.getParentEmail().isEmpty()) {
            pePlaceholder.setManaged(false);
            parentEmail.setManaged(false);
        } else {
            parentEmail.setText(person.getParentEmail().value);
        }

        if (person.getSchool().isEmpty()) {
            schPlaceholder.setManaged(false);
            school.setManaged(false);
        } else {
            school.setText(person.getSchool().value);
        }

        if (person.getAcadStream().isEmpty()) {
            streamPlaceholder.setManaged(false);
            acadStream.setManaged(false);
        } else {
            acadStream.setText(person.getAcadStream().value);
        }

        if (person.getAcadLevel().isEmpty()) {
            acadLevel.setVisible(false);
            acadLevel.setManaged(false);
        } else {
            acadLevel.setText(person.getAcadLevel().value);
        }

        if (person.getRemark().isEmpty()) {
            remarkPlaceholder.setManaged(false);
            remark.setManaged(false);
        } else {
            remark.setText(person.getRemark().value);
        }

        if (person.getFee().isEmpty()) {
            feePlaceholder.setManaged(false);
            outstandingFee.setManaged(false);
        } else {
            outstandingFee.setText("$" + person.getFee().value);
        }

        if (person.getTags().isEmpty()) {
            tags.setManaged(false);
        } else {
            person.getTags().stream()
                    .sorted(Comparator.comparing(tag -> tag.tagName))
                    .forEach(tag -> tags.getChildren().add(createTagLabel(tag.tagName)));
        }
        setAllLabelsMaxWidth();
        tags.maxWidthProperty().bind(getRoot().widthProperty().multiply(TEXT_WIDTH_PERC));
    }

    private Label createTagLabel(String tag) {
        Label label = new Label(tag);
        label.setWrapText(true);
        return label;
    }

    private void setLabelMaxWidth(Label label) {
        label.maxWidthProperty().bind(getRoot().widthProperty().multiply(TEXT_WIDTH_PERC));
    }

    private void setAllLabelsMaxWidth() {
        setLabelMaxWidth(name);
        setLabelMaxWidth(phone);
        setLabelMaxWidth(email);
        setLabelMaxWidth(parentPhone);
        setLabelMaxWidth(parentEmail);
        setLabelMaxWidth(address);
        setLabelMaxWidth(outstandingFee);
        setLabelMaxWidth(school);
        setLabelMaxWidth(acadStream);
        setLabelMaxWidth(acadLevel);
        setLabelMaxWidth(remark);
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
