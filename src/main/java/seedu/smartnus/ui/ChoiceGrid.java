package seedu.smartnus.ui;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seedu.smartnus.model.choice.Choice;

/**
 * An UI component that displays information of a {@code Question}.
 */
public abstract class ChoiceGrid extends UiPart<Region> {


    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private GridPane mcqGrid;


    /**
     * Creates a {@code QuestionCard} with the given {@code Question} and index to display.
     */
    public ChoiceGrid(String fxml) {
        super(fxml);
    };

    protected abstract void showCorrectOption(ArrayList<Choice> choices, Choice correctChoice);

    protected abstract void showIncorrectOption(ArrayList<Choice> choices, Choice selectedChoice);

    protected abstract void disableButtons();

}
