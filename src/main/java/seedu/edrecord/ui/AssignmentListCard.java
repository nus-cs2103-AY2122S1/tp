package seedu.edrecord.ui;

import static seedu.edrecord.model.assignment.Grade.GradeStatus.NOT_SUBMITTED;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberBinding;
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
import seedu.edrecord.model.module.Module;
import seedu.edrecord.model.person.AssignmentGradeMap;
import seedu.edrecord.model.person.Person;

/**
 * An UI component that displays assignment information of a {@code Person}.
 */
public class AssignmentListCard extends UiPart<Region> {

    private static final String FXML = "AssignmentListCard.fxml";

    private static final int FIXED_CELL_SIZE = 25;
    private static final int TABLE_HEADER_SIZE = 3; // in number of normal rows

    private static final double WIDTH_PADDING = 0.02;
    private static final double WIDTH_ID_COL = 0.05;
    private static final double WIDTH_STATUS_COL = 0.2;
    private static final double WIDTH_NAME_COL = 0.4;
    private static final double WIDTH_WEIGHTAGE_COL = 0.15;
    private static final double WIDTH_SCORE_COL =
            1 - WIDTH_ID_COL - WIDTH_STATUS_COL - WIDTH_NAME_COL - WIDTH_WEIGHTAGE_COL - WIDTH_PADDING;

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
    private TableView<Map.Entry<Assignment, Grade>> assignmentsTable;

    /**
     * Creates an {@code AssignmentListCard} with the given {@code Person}, index and {@code module} to display.
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
                    map.put(a, new Grade(Optional.of(new Score("0")), NOT_SUBMITTED));
                }
            }
        }

        TableColumn<Map.Entry<Assignment, Grade>, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(c -> {
            Assignment a = c.getValue().getKey();
            return new SimpleStringProperty(a.getId().toString());
        });

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
            Assignment a = c.getValue().getKey();
            Grade g = map.get(a);
            Score s = g.getScore().orElse(new Score("0"));
            return new SimpleStringProperty(s + " / " + a.getMaxScore().toString());
        });

        ObservableList<Map.Entry<Assignment, Grade>> items = FXCollections.observableArrayList(map.entrySet());
        assignmentsTable.setItems(items);

        List<TableColumn<Map.Entry<Assignment, Grade>, String>> cols =
                List.of(idCol, statusCol, nameCol, weightageCol, scoreCol);
        double[] weights = {WIDTH_ID_COL, WIDTH_STATUS_COL, WIDTH_NAME_COL, WIDTH_WEIGHTAGE_COL, WIDTH_SCORE_COL};
        assignmentsTable.getColumns().setAll(cols);

        int i = 0;
        for (var col : cols) {
            col.setResizable(false);
            DoubleBinding prefWidth = assignmentsTable.widthProperty().multiply(weights[i++]);
            col.prefWidthProperty().bind(prefWidth);
        }
        assignmentsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        assignmentsTable.setFixedCellSize(FIXED_CELL_SIZE);
        NumberBinding numRows = Bindings.size(assignmentsTable.getItems()).add(TABLE_HEADER_SIZE);
        assignmentsTable.prefHeightProperty().bind(numRows.multiply(FIXED_CELL_SIZE));
        assignmentsTable.minHeightProperty().bind(assignmentsTable.prefHeightProperty());
        assignmentsTable.maxHeightProperty().bind(assignmentsTable.prefHeightProperty());
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
