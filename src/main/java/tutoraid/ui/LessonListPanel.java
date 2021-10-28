package tutoraid.ui;

import static tutoraid.ui.DetailLevel.HIGH;
import static tutoraid.ui.DetailLevel.MED;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import tutoraid.commons.core.LogsCenter;
import tutoraid.model.lesson.Lesson;

/**
 * Panel containing the list of lessons.
 */
public class LessonListPanel extends UiPart<Region> {
    private static final String FXML = "LessonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(LessonListPanel.class);
    private final DetailLevel detailLevel;

    @FXML
    private ListView<Lesson> lessonListView;

    /**
     * Creates a {@code LessonListPanel} with the given {@code ObservableList}.
     */
    public LessonListPanel(ObservableList<Lesson> lessonList, DetailLevel detailLevel) {
        super(FXML);
        lessonListView.setItems(lessonList);
        lessonListView.setCellFactory(listView -> new LessonListViewCell());
        this.detailLevel = detailLevel;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Lesson} using a {@code LessonCard}.
     */
    class LessonListViewCell extends ListCell<Lesson> {
        @Override
        protected void updateItem(Lesson lesson, boolean empty) {
            super.updateItem(lesson, empty);

            if (empty || lesson == null) {
                setGraphic(null);
                setText(null);
            } else if (detailLevel == HIGH || detailLevel == MED) {
                setGraphic(new LessonCard(lesson, getIndex() + 1).getRoot());
            } else {
                setGraphic(new MinimalLessonCard(lesson, getIndex() + 1).getRoot());
            }
        }
    }

}
