package seedu.teachbook.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.teachbook.model.classobject.Class;


/**
 * A UI component that displays information of a {@code Class}.
 */
public class ClassCard extends UiPart<Region> {

    private static final String FXML = "ClassListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Class classObj;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label numberOfStudents;


    /**
     * Creates a {@code ClassCard} with the given {@code classObj} and index to display.
     */
    public ClassCard(Class classObj, int displayedIndex) {
        super(FXML);
        this.classObj = classObj;
        id.setText(displayedIndex + ". ");
        name.setText(classObj.getClassName().nameOfClass);
        numberOfStudents.setText(Integer.toString(classObj.getClassSize()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClassCard)) {
            return false;
        }

        // state check
        ClassCard card = (ClassCard) other;
        return id.getText().equals(card.id.getText())
                && classObj.equals(card.classObj);
    }
}
