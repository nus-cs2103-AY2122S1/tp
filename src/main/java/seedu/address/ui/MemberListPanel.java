package seedu.address.ui;

import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Student;

/**
 * Panel containing the list of students.
 */
public class MemberListPanel extends UiPart<Region> {
    private static final String FXML = "MemberListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(MemberListPanel.class);
    private final int cardHeight = 100;

    @FXML
    private ListView<Student> memberListView;

    /**
     * Creates a {@code MemberListPanel} with the given {@code ObservableList}.
     */
    public MemberListPanel(ObservableList<Student> studentList) {
        super(FXML);
        memberListView.setItems(studentList);
        memberListView.setCellFactory(listView -> new MemberListViewCell());
        memberListView.prefHeightProperty().bind(Bindings.size(studentList).multiply(cardHeight));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code StudentCard}.
     */
    class MemberListViewCell extends ListCell<Student> {
        @Override
        protected void updateItem(Student student, boolean empty) {
            super.updateItem(student, empty);

            if (empty || student == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MemberListCard(student, getIndex() + 1).getRoot());
            }
        }
    }

}
