package seedu.address.ui;

import java.util.Comparator;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.participant.NextOfKin;
import seedu.address.model.participant.Note;
import seedu.address.model.participant.Participant;
import seedu.address.model.tag.Tag;

/**
 * An UI component that displays information of a {@code Participant}.
 */
public class ParticipantCard extends UiPart<Region> {

    private static final String FXML = "ParticipantListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Participant participant;

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
    private FlowPane tags;
    @FXML
    private Label participantId;
    @FXML
    private Label birthDate;
    @FXML
    private FlowPane notes;
    @FXML
    private Label nextOfKins;

    /**
     * Creates a {@code ParticipantCode} with the given {@code Participant} and index to display.
     */
    public ParticipantCard(Participant participant, int displayedIndex) {
        super(FXML);
        this.participant = participant;
        id.setText(displayedIndex + ". ");
        name.setText(participant.getFullName());
        phone.setText(participant.getPhoneValue());
        address.setText(participant.getAddressValue());
        email.setText(participant.getEmailValue());
        participant.getTags().stream()
                .sorted(Comparator.comparing(Tag::getTagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.getTagName())));

        participantId.setText(participant.getParticipantIdValue());
        birthDate.setText(participant.getBirthDateString());
        participant.getNotes().stream()
                .sorted(Comparator.comparing(Note::toString))
                .forEach(note -> notes.getChildren().add(noteLabel(note)));
        nextOfKins.setText(participant.getNextOfKins().stream().map(NextOfKin::toString).collect(Collectors.joining()));

    }

    /**
     * Creates a custom Label for Notes with colour based on Note Importance.
     * @param note note to make a Label for.
     * @return a Label representing the Note.
     */
    public Label noteLabel(Note note) {
        Label label = new Label();
        label.setText(note.getContent());

        switch(note.getImportance()) {
        case VERY_HIGH:
            label.setStyle("-fx-background-color: #ff0000; -fx-text-fill: white; -fx-padding: 1 3 1 3; "
                    + "-fx-border-radius: 2; -fx-background-radius: 2; -fx-font-size: 11;");
            break;
        case HIGH:
            label.setStyle("-fx-background-color: #ffa600; -fx-text-fill: black; -fx-padding: 1 3 1 3; "
                    + "-fx-border-radius: 2; -fx-background-radius: 2; -fx-font-size: 11;");
            break;
        case MEDIUM:
            label.setStyle("-fx-background-color: #ffff00; -fx-text-fill: black; -fx-padding: 1 3 1 3; "
                    + "-fx-border-radius: 2; -fx-background-radius: 2; -fx-font-size: 11;");
            break;
        case LOW:
            label.setStyle("-fx-background-color: #00ff00; -fx-text-fill: black; -fx-padding: 1 3 1 3; "
                    + "-fx-border-radius: 2; -fx-background-radius: 2; -fx-font-size: 11;");
            break;
        case VERY_LOW:
            label.setStyle("-fx-background-color: #0000ff; -fx-text-fill: white; -fx-padding: 1 3 1 3; "
                    + "-fx-border-radius: 2; -fx-background-radius: 2; -fx-font-size: 11;");
            break;
        default:
        }

        return label;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ParticipantCard)) {
            return false;
        }

        // state check
        ParticipantCard card = (ParticipantCard) other;
        return id.getText().equals(card.id.getText())
                && participant.equals(card.participant);
    }
}
