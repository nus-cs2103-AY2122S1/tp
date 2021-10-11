package seedu.tracker.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.calendar.AcademicYear;
import seedu.tracker.model.calendar.Semester;
import seedu.tracker.model.module.Module;

/**
 * An UI component that displays information of a {@code Module}.
 */
public class ModuleCard extends UiPart<Region> {

    private static final String FXML = "ModuleListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on ModuleTracker level 4</a>
     */

    public final Module module;

    @FXML
    private HBox cardPane;
    @FXML
    private Label code;
    @FXML
    private Label id;
    @FXML
    private Label title;
    @FXML
    private Label mc;
    @FXML
    private Label description;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane academicCalendar;

    /**
     * Creates a {@code ModuleCard} with the given {@code Module} and index to display.
     */
    public ModuleCard(Module module, int displayedIndex) {
        super(FXML);
        this.module = module;
        id.setText(displayedIndex + ". ");
        code.setText(module.getCode().value);
        title.setText(module.getTitle().value);
        mc.setText(String.valueOf(module.getMc().value));
        description.setText(module.getDescription().value);

        if (module.hasAcademicCalendar()) {
            academicCalendar.getChildren().add(new Label(getFormattedAcademicCalendar(module)));
        }

        module.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    private String getFormattedAcademicCalendar(Module module) {
        AcademicCalendar academicCalendar = module.getAcademicCalendar();
        AcademicYear year = academicCalendar.getAcademicYear();
        Semester semester = academicCalendar.getSemester();
        return String.format("y%ss%s", year, semester);
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
        return id.getText().equals(card.id.getText())
                && module.equals(card.module);
    }
}
