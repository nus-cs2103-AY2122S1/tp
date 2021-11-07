package seedu.address.ui;

import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Period;
import seedu.address.model.person.Person;
import seedu.address.model.person.Role;
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

    public final Person staff;
    private int displayedIndex;

    @FXML
    private HBox cardPane;
    @FXML
    private Label index;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private ListView<String> periodListView;
    @FXML
    private Label email;
    @FXML
    private FlowPane roles;
    @FXML
    private Label salary;
    @FXML
    private Label status;
    @FXML
    private ListView<String> tagsListView;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person staff, int displayedIndex) {
        super(FXML);
        this.staff = staff;
        this.displayedIndex = displayedIndex;
        index.setText(String.valueOf(displayedIndex));
        name.setText(staff.getName().fullName);
        phone.setText(staff.getPhone().value);
        email.setText(staff.getEmail().value);
        salary.setText(staff.getSalary().convertToDollars());
        status.setText(staff.getStatus().getValue());

        String[] periodArray = staff.getAbsentDates().stream().map(Period::toDisplayString).toArray(String[]::new);
        periodListView.setItems(FXCollections.observableArrayList(periodArray));

        staff.getRoles().stream()
                .sorted(Comparator.comparing(Role::toString))
                .forEach(role -> roles.getChildren().add(new Label(role.toString())));

        String[] tagArray =staff.getTags().stream().map(tag -> tag.tagName).toArray(String[]::new);
        tagsListView.setItems(FXCollections.observableArrayList(tagArray));
    }

    public int getDisplayedIndex() {
        return displayedIndex;
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
        return displayedIndex == card.getDisplayedIndex()
                && staff.equals(card.staff);
    }
}
