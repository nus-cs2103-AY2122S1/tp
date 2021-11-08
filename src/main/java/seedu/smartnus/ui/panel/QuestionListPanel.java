package seedu.smartnus.ui.panel;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.smartnus.commons.core.LogsCenter;
import seedu.smartnus.logic.Logic;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.ui.StatusBarFooter;
import seedu.smartnus.ui.UiPart;
import seedu.smartnus.ui.card.QuestionCard;


/**
 * Panel containing the list of questions.
 */
public class QuestionListPanel extends UiPart<Region> implements Panel {
    public static final String QUESTION_PANEL = "question";

    private static final String FXML = "QuestionListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(QuestionListPanel.class);

    @FXML
    private ListView<Question> questionListView;

    private StackPane questionListPanelPlaceholder;
    private StackPane statusbarPlaceholder;

    /**
     * Creates a {@code QuestionListPanel} with the given {@code ObservableList}.
     */
    public QuestionListPanel(StackPane questionListPanelPlaceholder, StackPane status) {
        super(FXML);
        this.questionListPanelPlaceholder = questionListPanelPlaceholder;
        this.statusbarPlaceholder = status;
    }

    private QuestionListPanel(ObservableList<Question> questionList) {
        super(FXML);
        questionListView.setItems(questionList);
        questionListView.setCellFactory(listView -> new QuestionListViewCell());
    }

    @Override
    public Panel render(Logic logic) {
        QuestionListPanel questionListPanel = new QuestionListPanel(logic.getFilteredQuestionList());
        questionListPanelPlaceholder.setVisible(true);
        questionListPanelPlaceholder.getChildren().add(questionListPanel.getRoot());

        StatusBarFooter status = new StatusBarFooter("Questions");
        statusbarPlaceholder.getChildren().add(status.getRoot());

        return questionListPanel;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Question} using a {@code QuestionCard}.
     */
    class QuestionListViewCell extends ListCell<Question> {
        @Override
        protected void updateItem(Question question, boolean empty) {
            super.updateItem(question, empty);

            if (empty || question == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new QuestionCard(question, getIndex() + 1).getRoot());
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof QuestionListPanel;
    }

}
