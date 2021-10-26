package seedu.address.ui;

import java.util.Set;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupWithDetails;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Group}.
 */
public class GroupViewCard extends UiPart<Region> {

    private static final String FXML = "GroupViewCard.fxml";

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
    private Label persons;
    @FXML
    private Label personsLabel;


    /**
     * Creates a {@code Groupcode} with the given {@code Group} and index to display.
     */
    public GroupViewCard(ObservableValue<GroupWithDetails> groupWithDetailsObservable) {
        super(FXML);
        ChangeListener<? super GroupWithDetails> changeListener = new ChangeListener<GroupWithDetails>() {
            @Override
            public void changed(ObservableValue<? extends GroupWithDetails> observable,
                                GroupWithDetails oldValue, GroupWithDetails newValue) {
                updateCard(newValue);
            }
        };
        groupWithDetailsObservable.addListener(changeListener);
        updateCard(groupWithDetailsObservable.getValue());

    }

    private void updateCard(GroupWithDetails groupWithDetails) {
        if (groupWithDetails == null) {
            // null value, this is expected to happen if there is no groups!
            // however, logic handlers should prevent user from viewing an empty view card.
            return;
        }
        Group group = groupWithDetails.getGroup();
        Set<Person> persons = groupWithDetails.getPersons();

        name.setText(group.getName().name);
        String allNames = "";
        for (Person person : persons) {
            String name = person.getName().fullName;
            allNames += name + "\n";

        }
        personsLabel.setText(allNames);
    }
}
