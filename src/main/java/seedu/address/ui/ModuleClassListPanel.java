package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.modulelesson.ModuleLesson;

public class ModuleClassListPanel extends UiPart<Region> {
    private static final String FXML = "ModuleLessonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ModuleClassListPanel.class);

    @FXML
    private ListView<ModuleLesson> moduleClassListView;

    /**
     * Creates a {@code ModuleLessonListPanel} with the given {@code ObservableList}.
     */
    public ModuleClassListPanel(ObservableList<ModuleLesson> moduleLessonList) {
        super(FXML);
        moduleClassListView.setItems(moduleLessonList);
        moduleClassListView.setCellFactory(listview -> new ModuleClassListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code ModuleLesson} using a {@code ModuleLessonCard}.
     */
    class ModuleClassListViewCell extends ListCell<ModuleLesson> {
        @Override
        protected void updateItem(ModuleLesson moduleLesson, boolean empty) {
            super.updateItem(moduleLesson, empty);

            if (empty || moduleLesson == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ModuleClassCard(moduleLesson, getIndex() + 1).getRoot());
            }
        }

    }
}
