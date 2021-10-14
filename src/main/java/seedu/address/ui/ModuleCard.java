package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.module.Module;
import seedu.address.model.module.student.Student;

/**
 * An UI component that displays information of a {@code Module}.
 */
public class ModuleCard extends UiPart<Region> {

    private static final String FXML = "ModuleCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">
     * The issue on TeachingAssistantBuddy level 4</a>
     */

    public final Module module;

    // Independent Ui parts residing in this Ui container
    private StudentListPanel studentListPanel;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private ListView<Student> studentListView;
    @FXML
    private StackPane studentListPanelPlaceholder;

    /**
     * Creates a {@code ModuleCard} with the given {@code Module} and index to display.
     */
    public ModuleCard(Module module, int displayedIndex) {
        super(FXML);
        this.module = module;
        name.setText(module.getName().moduleName);
    }

    /**
     * Fills up all the placeholders of this ModuleCard.
     */
    void fillInnerParts() {
        ObservableList<Student> studentList = module.getStudentList();
        if (studentList.size() != 0) {
            studentListPanel = new StudentListPanel(module.getStudentList());
            studentListPanelPlaceholder.getChildren().add(studentListPanel.getRoot());
        }
    }

    public StudentListPanel getStudentListPanel() {
        return studentListPanel;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleCard)) {
            return false;
        }

        // state check
        ModuleCard card = (ModuleCard) other;

        return name.getText().equals(card.name.getText()) && module.equals(card.module);
    }
}
