package seedu.edrecord.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.edrecord.model.assignment.Assignment;
import seedu.edrecord.model.assignment.Grade;
import seedu.edrecord.model.assignment.Score;
import seedu.edrecord.model.person.AssignmentGradeMap;
import seedu.edrecord.model.person.Person;
import seedu.edrecord.model.module.Module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static seedu.edrecord.model.assignment.Grade.GradeStatus.NOT_SUBMITTED;

/**
 * An UI component that displays assignment information of a {@code Person}.
 */
public class AssignmentListCard extends UiPart<Region> {

    private static final String FXML = "AssignmentListCard.fxml";

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
    private TableView<Map.Entry<Assignment, Grade>> assignments;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public AssignmentListCard(Person person, int displayedIndex, ObservableValue<Module> module) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().name);

        AssignmentGradeMap assignmentGradeMap = person.getGrades();
        HashMap<Assignment, Grade> map = assignmentGradeMap.getGrades();

        Module m = module.getValue();
        if (m != null) {
            for (Assignment a : m.getAssignmentList()) {
                if (!map.containsKey(a)) {
                    map.put(a, new Grade(new Score("0"), NOT_SUBMITTED));
                }
            }
        }

        TableColumn<Map.Entry<Assignment, Grade>, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(c -> {
            Grade g = map.get(c.getValue().getKey());
            return new SimpleStringProperty(g.getStatus().name());
        });

        TableColumn<Map.Entry<Assignment, Grade>, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(c -> {
            Assignment a = c.getValue().getKey();
            return new SimpleStringProperty(a.getName().toString());
        });

        TableColumn<Map.Entry<Assignment, Grade>, String> weightageCol = new TableColumn<>("Weightage");
        weightageCol.setCellValueFactory(c -> {
            Assignment a = c.getValue().getKey();
            return new SimpleStringProperty(a.getWeightage().toString());
        });

        TableColumn<Map.Entry<Assignment, Grade>, String> scoreCol = new TableColumn<>("Score");
        scoreCol.setCellValueFactory(c -> {
            Grade g = map.get(c.getValue().getKey());
            return new SimpleStringProperty(g.getScore().toString());
        });

        TableColumn<Map.Entry<Assignment, Grade>, String> maxScoreCol = new TableColumn<>("Max Score");
        maxScoreCol.setCellValueFactory(c -> {
            Assignment a = c.getValue().getKey();
            return new SimpleStringProperty(a.getMaxScore().toString());
        });

        ObservableList<Map.Entry<Assignment, Grade>> items = FXCollections.observableArrayList(map.entrySet());
        assignments.setItems(items);
        assignments.getColumns().setAll(List.of(statusCol, nameCol, weightageCol, scoreCol, maxScoreCol));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssignmentListCard)) {
            return false;
        }

        // state check
        AssignmentListCard card = (AssignmentListCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
