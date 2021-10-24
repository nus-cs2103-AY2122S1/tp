package seedu.address.ui;

import java.util.Comparator;
import java.util.Set;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonWithDetails;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonViewCard extends UiPart<Region> {

    private static final String FXML = "PersonViewCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label lessons;
    @FXML
    private Label groupsLabel;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonViewCard(ObservableValue<PersonWithDetails> personWithDetailsObservable) {
        super(FXML);
        ChangeListener<? super PersonWithDetails> changeListener = new ChangeListener<PersonWithDetails>() {
            @Override
            public void changed(ObservableValue<? extends PersonWithDetails> observable,
                                PersonWithDetails oldValue, PersonWithDetails newValue) {
                updateCard(newValue);
            }
        };
        personWithDetailsObservable.addListener(changeListener);
        updateCard(personWithDetailsObservable.getValue());

    }

    private void updateCard(PersonWithDetails personWithDetails) {
        if (personWithDetails == null) {
            // null value, this is expected to happen if there is no persons!
            // however, logic handlers should prevent user from viewing an empty view card.
            return;
        }

        Person person = personWithDetails.getPerson();
        Set<Group> groups = personWithDetails.getGroups();

        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        lessons.setText(person.getLessonsList().toString());
        groupsLabel.setText(groups.toString());
    }
}
