package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.participant.Participant;

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
    private FlowPane nextOfKins;

    /**
     * Creates a {@code ParticipantCode} with the given {@code Participant} and index to display.
     */
    public ParticipantCard(Participant participant, int displayedIndex) {
        super(FXML);
        this.participant = participant;
        id.setText(displayedIndex + ". ");
        name.setText(participant.getName().fullName);
        phone.setText(participant.getPhone().value);
        address.setText(participant.getAddress().value);
        email.setText(participant.getEmail().value);
        participant.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
                
        participantId.setText(participant.getParticipantId().toString());
        birthDate.setText(participant.getBirthDate().toString());
        participant.getNotes().stream()
                .sorted(Comparator.comparing(Object::toString))
                .forEach(note -> notes.getChildren().add(new Label(note.toString())));
        participant.getNextOfKins().stream()
                .sorted(Comparator.comparing(nok -> nok.getName().fullName))
                .forEach(nok -> nextOfKins.getChildren().add(new Label(nok.toString())));
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
