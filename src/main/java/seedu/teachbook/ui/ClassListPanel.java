package seedu.teachbook.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import seedu.teachbook.commons.core.LogsCenter;
import seedu.teachbook.model.classobject.Class;


/**
 * Panel containing the list of persons.
 */
public class ClassListPanel extends UiPart<Region> {
    private static final String FXML = "ClassListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ClassListPanel.class);

    @FXML
    private ListView<Class> classListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public ClassListPanel(ObservableList<Class> classList) {
        super(FXML);
        classListView.setItems(classList);
        classListView.setCellFactory(listView -> new ClassListViewCell());
        classListView.addEventFilter(MouseEvent.MOUSE_PRESSED, Event::consume);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class ClassListViewCell extends ListCell<Class> {
        @Override
        protected void updateItem(Class classObj, boolean empty) {
            super.updateItem(classObj, empty);

            if (empty || classObj == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ClassCard(classObj, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Selects the currently selected class and highlights it on the UI.
     *
     * @param currentlySelectedClassIndex the currently selected class's index
     */
    public void select(int currentlySelectedClassIndex) {
        classListView.getSelectionModel().clearAndSelect(currentlySelectedClassIndex);
        classListView.getFocusModel().focus(currentlySelectedClassIndex);
        classListView.scrollTo(currentlySelectedClassIndex);
    }

}
