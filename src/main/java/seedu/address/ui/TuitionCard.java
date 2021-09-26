package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.Tuition.TuitionClass;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class TuitionCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final TuitionClass tuitionClass;

    @FXML
    private HBox tuitionPane;
    @FXML
    private Label id;
    @FXML
    private Label limit;
    @FXML
    private Label counter;
    @FXML
    private Label timeSlot;
    @FXML
    private Label name;
    @FXML
    private Label student;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public TuitionCard(TuitionClass tuitionClass, int displayedIndex) {
        super(FXML);
        System.out.println("TuitionCard" + tuitionClass.toString());

        this.tuitionClass = tuitionClass;
        id.setText(displayedIndex + ". ");
        name.setText(tuitionClass.getName().name);
        limit.setText(tuitionClass.getLimit().toString());
        counter.setText(tuitionClass.getCounter().toString());
        timeSlot.setText(tuitionClass.getTimeslot().time);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TuitionCard)) {
            return false;
        }

        // state check
        TuitionCard card = (TuitionCard) other;
        return id.getText().equals(card.id.getText())
                && tuitionClass.equals(card.tuitionClass);
    }
}
