package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.modulelesson.ModuleLesson;

public class ModuleLessonCard extends UiPart<Region> {

    private static final String FXML = "ModuleLessonCard.fxml";

    public final ModuleLesson moduleLesson;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label moduleCode;
    @FXML
    private Label lessonDay;
    @FXML
    private Label lessonTime;
    @FXML
    private Label remark;
    @FXML
    private FlowPane moduleCodeContainer;
    @FXML
    private FlowPane lessonCodes;

    /**
     * An UI component that displays information of a {@code moduleLesson}.
     */
    public ModuleLessonCard(ModuleLesson moduleLesson, int displayedIndex) {
        super(FXML);
        this.moduleLesson = moduleLesson;
        id.setText(displayedIndex + ". ");
        moduleLesson.getModuleCodes().stream()
                .sorted(Comparator.comparing(moduleCode -> moduleCode.value))
                .forEach(moduleCode -> moduleCodeContainer.getChildren().add(new Label(moduleCode.value)));

        moduleLesson.getModuleCodes().stream()
                .sorted(Comparator.comparing(moduleCode -> moduleCode.value))
                .map(moduleCode -> moduleCode.lessonCodes)
                .forEach(t -> t.forEach(lessonCode -> lessonCodes.getChildren().add(new Label(lessonCode.lessonCode))));
        lessonDay.setText(moduleLesson.getDay().toString());
        lessonTime.setText(moduleLesson.getTime().toString());
        remark.setText(moduleLesson.getRemark().value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ModuleLessonCard)) {
            return false;
        }

        ModuleLessonCard card = (ModuleLessonCard) other;
        return id.getText().equals(card.id.getText())
                && moduleLesson.equals(moduleLesson);
    }

}

