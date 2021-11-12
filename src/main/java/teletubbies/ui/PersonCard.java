package teletubbies.ui;

import java.nio.charset.StandardCharsets;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import teletubbies.model.person.Person;
import teletubbies.model.tag.Tag;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    private static final String COMPLETED_EMOJI = new String(
            new byte[]{(byte) 0xE2, (byte) 0x9C, (byte) 0x85}, StandardCharsets.UTF_8
    );

    private static final String UNCOMPLETED_EMOJI = new String(
            new byte[]{(byte) 0xE2, (byte) 0x9D, (byte) 0x8C}, StandardCharsets.UTF_8
    );

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
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label remark;
    @FXML
    private ProgressBar completionStatusTag;
    @FXML
    private Label completionStatusLabel;
    @FXML
    private FlowPane tags;
    @FXML
    private Button copyButton;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText("Phone Number: " + person.getPhone().value);

        if (person.getAddress().isPresent()) {
            address.setText("Address: " + person.getAddress().value);
        } else {
            address.setManaged(false);
        }

        if (person.getEmail().isPresent()) {
            email.setText("Email: " + person.getEmail().value);
        } else {
            email.setManaged(false);
        }

        if (person.getRemark().isPresent()) {
            remark.setText("Remark: " + person.getRemark().value);
        } else {
            remark.setManaged(false);
        }

        completionStatusLabel.setText(person.getCompletionStatus().status.name());
        switch (person.getCompletionStatus().status) {
        case COMPLETE:
            completionStatusTag.setProgress(1.0);
            completionStatusLabel.setStyle("-fx-background-color: #237a1d;");
            break;
        case ONGOING:
            completionStatusTag.setProgress(0.5);
            completionStatusLabel.setStyle("-fx-background-color: #e0872d;");
            break;
        default:
            completionStatusTag.setProgress(0.0);
            completionStatusLabel.setStyle("-fx-background-color: #ec2626;");
        }

        person.getTags().stream()
                .sorted(Comparator.comparing(Tag::toString))
                .forEach(tag -> tags.getChildren().add(new Label(tag.toString())));
    }

    @FXML
    private void copyToClipboard() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(person.getPhone().value);
        clipboard.setContent(content);
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
