package seedu.smartnus.ui.card;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.ui.UiPart;

/**
 * An UI component that displays information of a {@code Question}.
 */
public class QuestionCard extends UiPart<Region> {

    private static final String FXML = "QuestionListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Question question;
    private int displayedIndex;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label importance;
    @FXML
    private Label statistic;
    @FXML
    private FlowPane tags;
    @FXML
    private HBox choices;

    /**
     * Creates a {@code QuestionCard} with the given {@code Question} and index to display.
     */
    public QuestionCard(Question question, int displayedIndex) {
        super(FXML);
        this.question = question;
        this.displayedIndex = displayedIndex;
        name.setText(displayedIndex + ". " + question.getName().fullName);
        name.setWrapText(true);
        importance.setText("Importance: " + question.getImportance().value);
        question.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        question.getChoices().stream()
                .forEach(choice -> setChoiceLabel(choice));
        statistic.setText(question.getStatistic().toString());
    }

    // TODO: update choice background colours when we change gui display for quiz
    // If not, during quiz, users can see the correct answers
    private void setChoiceLabel(Choice choice) {
        Label choiceLabel = new Label(choice.getTitle());
        choiceLabel.setWrapText(true);
        if (choice.getIsCorrect()) {
            choiceLabel.getStyleClass().add("correct-choice-bg");
        } else {
            choiceLabel.getStyleClass().add("wrong-choice-bg");
        }
        choices.getChildren().add(choiceLabel);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof QuestionCard)) {
            return false;
        }

        // state check
        QuestionCard card = (QuestionCard) other;
        return displayedIndex == card.displayedIndex
                && question.equals(card.question);
    }
}
