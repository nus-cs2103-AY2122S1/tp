package seedu.address.ui;

import java.util.Comparator;
import java.util.stream.Collectors;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.tag.Tag;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class TagsPanel extends UiPart<Region> {

    private static final String FXML = "TagsPanel.fxml";
    // private final ObservableList<Tag> tagList;

    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code TagsPanel} with the given {@code CommandExecutor}.
     */
    public TagsPanel(ObservableList<Tag> tagList) {
        super(FXML);
        tagList.stream()
            .sorted(Comparator.comparing(Tag::getName))
            .forEach(tag -> tags.getChildren().add(new Label(tag.getName())));

        tagList.addListener((ListChangeListener<Tag>) c -> updateView(tagList));
    }

    private void updateView(ObservableList<Tag> tagList) {
        tags.getChildren().setAll(
            tagList.stream().map(tag -> new Label(tag.getName())).collect(Collectors.toList()));
    }
}
