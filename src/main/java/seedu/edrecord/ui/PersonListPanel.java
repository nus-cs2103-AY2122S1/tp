package seedu.edrecord.ui;

import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.edrecord.commons.core.LogsCenter;
import seedu.edrecord.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    public enum View {
        CONTACTS,
        ASSIGNMENTS,
    }

    private View selectedView;

    @FXML
    private ListView<Person> personListView;
    @FXML
    private ListView<Person> assignmentListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList, ObservableValue<View> selectedView) {
        super(FXML);

        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        personListView.managedProperty().bind(personListView.visibleProperty());

        assignmentListView.setItems(personList);
        assignmentListView.setCellFactory(listView -> new AssignmentListViewCell());
        assignmentListView.managedProperty().bind(assignmentListView.visibleProperty());

        this.setView(selectedView.getValue());
        selectedView.addListener((unused, oldView, newView) -> setView(newView));
    }

    public void setView(View newView) {
        this.selectedView = newView;
        personListView.setVisible(this.selectedView == View.CONTACTS);
        assignmentListView.setVisible(this.selectedView == View.ASSIGNMENTS);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person}'s assignments using a {@code PersonCard}.
     */
    class AssignmentListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AssignmentCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
