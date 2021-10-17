package seedu.address.ui;

import java.util.Comparator;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.LastVisit;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    public static final String OVERDUE_STYLE_CLASS = "overdue";

    private static final String FXML = "PersonListCard.fxml";
    private static final String DISPLAY_LAST_VISIT = "Last visit: ";
    private static final String DISPLAY_NEXT_VISIT = "Next visit: ";

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
    private Label language;
    @FXML
    private FlowPane tags;
    @FXML
    private Label lastVisit;
    @FXML
    private Label visit;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        language.setText(person.getLanguage().value);
        lastVisit.setText(DISPLAY_LAST_VISIT + person.getLastVisit().orElse(new LastVisit("")).getFormatted());
        visit.setText(DISPLAY_NEXT_VISIT + person.getFormattedVisit());
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        setVisitOverdueStyle(person.isVisitOverdue());
    }

    /**
     * Updates the PersonCard visit field's style based on if it is overdue.
     * Defensive programming involved here.
     * @param isOverdue whether the visit is overdue
     */
    private void setVisitOverdueStyle(boolean isOverdue) {
        ObservableList<String> styles = visit.getStyleClass();
        if (!isOverdue) {
            styles.remove(OVERDUE_STYLE_CLASS);
        } else {
            if (!styles.contains(OVERDUE_STYLE_CLASS)) {
                styles.add(OVERDUE_STYLE_CLASS);
            }
        }
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
