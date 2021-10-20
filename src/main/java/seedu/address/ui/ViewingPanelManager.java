package seedu.address.ui;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import seedu.address.logic.Logic;

public class ViewingPanelManager {

    public enum ViewType {
        DEFAULT, SCHEDULE
    }

    private Node defaultNode;

    public ViewingPanelManager(Node defaultNode) {
        this.defaultNode = defaultNode;
    }

    public void setViewingPanel(StackPane pane, ViewType toView, Logic logic) {
        switch(toView) {
        case SCHEDULE:
            pane.getChildren().clear();
            pane.getChildren()
                    .addAll(new LessonSchedulePanel(logic.getSortedLessonsWithAttendees()).getRoot());
            break;
        case DEFAULT:
            pane.getChildren().clear();
            pane.getChildren().addAll(defaultNode);
            break;
        default:
            // TODO throw exception?
        }
    }
}
