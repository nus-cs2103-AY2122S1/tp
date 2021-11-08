package seedu.track2gather.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.track2gather.model.person.Person;

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
    private Label caseNumber;
    @FXML
    private Label homeAddress;
    @FXML
    private Label workAddress;
    @FXML
    private Label quarantineAddress;
    @FXML
    private Label shnPeriod;
    @FXML
    private Label nextOfKinName;
    @FXML
    private Label nextOfKinPhone;
    @FXML
    private Label nextOfKinAddress;
    @FXML
    private Label numFailedCalls;
    @FXML
    private HBox phoneHBox;
    @FXML
    private HBox homeHBox;
    @FXML
    private HBox emailHBox;
    @FXML
    private HBox workAddressHBox;
    @FXML
    private HBox quarantineAddressHBox;
    @FXML
    private HBox shnPeriodHBox;
    @FXML
    private HBox nextOfKinNameHBox;
    @FXML
    private HBox nextOfKinPhoneHBox;
    @FXML
    private HBox nextOfKinAddressHBox;
    @FXML
    private VBox nextOfKinBlock;
    @FXML
    private VBox callStatusBlock;
    @FXML
    private ImageView callStatusTick;
    @FXML
    private ImageView callStatusCross;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().value);
        phone.setText(person.getPhone().value);
        email.setText(person.getEmail().value);
        caseNumber.setText(String.format("#%1$6s", person.getCaseNumber().value).replace(' ', '0'));
        homeAddress.setText(person.getHomeAddress().value);

        person.getWorkAddress().value.map(Object::toString).ifPresentOrElse(
            text -> workAddress.setText(text), () -> hideNode(workAddressHBox));

        person.getQuarantineAddress().value.map(Object::toString).ifPresentOrElse(
            text -> quarantineAddress.setText(text), () -> hideNode(quarantineAddressHBox));

        person.getShnPeriod().value.map(Object::toString).ifPresentOrElse(
            text -> shnPeriod.setText(text), () -> hideNode(shnPeriodHBox));

        person.getNextOfKinName().value.map(Object::toString).ifPresentOrElse(
            text -> nextOfKinName.setText(text), () -> hideNode(nextOfKinNameHBox));

        person.getNextOfKinPhone().value.map(Object::toString).ifPresentOrElse(
            text -> nextOfKinPhone.setText(text), () -> hideNode(nextOfKinPhoneHBox));

        person.getNextOfKinAddress().value.map(Object::toString).ifPresentOrElse(
            text -> nextOfKinAddress.setText(text), () -> hideNode(nextOfKinAddressHBox));

        if (person.getNextOfKinName().value.isEmpty()
                && person.getNextOfKinPhone().value.isEmpty()
                && person.getNextOfKinAddress().value.isEmpty()) {
            hideNode(nextOfKinBlock);
        }

        if (person.getCallStatus().isCalledInCurrentSession()) {
            hideNode(callStatusCross);
        } else {
            hideNode(callStatusTick);
        }

        numFailedCalls.setText(String.format("Failed: %s time(s)", person.getCallStatus().getNumFailedCalls()));
    }

    /**
     * Removes the specified {@code Node} from display.
     * @param node The {@code Node} to be hidden from display.
     */
    private void hideNode(Node node) {
        node.setManaged(false);
        node.setVisible(false);
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
