package seedu.academydirectory.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.academydirectory.model.AdditionalViewModel;
import seedu.academydirectory.ui.creator.Creator;
import seedu.academydirectory.ui.creator.DefaultCreator;
import seedu.academydirectory.ui.creator.GraphCreator;
import seedu.academydirectory.ui.creator.ViewCreator;

/**
 * A ui for the visualiser bar that is displayed at bottom right of the app.
 */
public class VisualiserDisplay extends UiPart<Region> {
    private static final String FXML = "VisualiserDisplay.fxml";

    @FXML
    private StackPane placeHolder;

    /**
     *
     * @param additionalViewModel
     */
    public VisualiserDisplay(AdditionalViewModel additionalViewModel) {
        super(FXML);
        Creator creator = parseCreator(additionalViewModel);
        placeHolder.getChildren().add(creator.create(additionalViewModel.getAdditionalInfo()));
    }

    private Creator parseCreator(AdditionalViewModel additionalViewModel) {
        switch (additionalViewModel.getAdditionalViewType()) {
        case VIEW:
            return new ViewCreator();
        case VISUALISE:
            return new GraphCreator();
        default:
            return new DefaultCreator();
        }
    }
}
