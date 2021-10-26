package seedu.unify.ui;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Region;


/**
 * An UI component that displays information of a {@code Task} in the specified day.
 */
public class ProgressPanel extends UiPart<Region> {

    private static final String FXML = "ProgressPanel.fxml";
    private static final String PROGRESS_LABEL = "%d / %d done";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final SimpleDoubleProperty progress;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label progressLabel;

    /**
     * Creates a {@code TaskCode} with the given {@code Task} and index to display.
     */
    public ProgressPanel(SimpleDoubleProperty progress, SimpleIntegerProperty total, SimpleIntegerProperty completed) {
        super(FXML);
        this.progress = progress;
        progressLabel.setText(String.format(PROGRESS_LABEL, completed.getValue(), total.getValue()));
        progressBar.setProgress(progress.getValue());
        progress.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                progressLabel.setText(String.format(PROGRESS_LABEL, completed.getValue(), total.getValue()));
                progressBar.setProgress(newValue.doubleValue());
            }
        });
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ProgressPanel)) {
            return false;
        }

        // state check
        ProgressPanel progressPanel = (ProgressPanel) other;
        return progressLabel.getText().equals(progressPanel.progressLabel.getText())
                && progress.equals(progressPanel.progress);
    }
}
