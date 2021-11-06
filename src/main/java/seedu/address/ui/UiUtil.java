package seedu.address.ui;

import java.util.Collection;
import java.util.Optional;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Util class for UI functions
 */
public class UiUtil {
    /**
     * Updates the given VBox with multiple labels
     * Optional default message to use if the collection is empty.
     * @param toUpdate Vbox to update.
     * @param stringCollections collection of Strings to use in the label details.
     * @param defaultMessage optional default message if the list is empty.
     */
    public static <T> void addIndexedLabels(VBox toUpdate, Collection<String> stringCollections,
            Optional<String> defaultMessage) {
        toUpdate.getChildren().clear();

        // if is empty, give a default message if present.
        if (stringCollections.isEmpty()) {
            defaultMessage.ifPresent((msg) -> {
                Label emptyLabel = new Label(msg);
                emptyLabel.setMinWidth(Label.USE_PREF_SIZE);
                toUpdate.getChildren().add(emptyLabel);
            });
            return;
        }
        // otherwise add all of the objects
        int index = 1;
        for (String str: stringCollections) {
            toUpdate.getChildren().add(createLabelWithIndex(index, str));
            index++;
        }
    }

    /**
     * Creates a label with the the given index and string
     * @param index of label
     * @param str to appear after the label
     * @return Label with index and string
     */
    public static Label createLabelWithIndex(int index, String str) {
        Label toAdd = new Label(String.format("%d. %s", index, str));
        toAdd.setMinWidth(Label.USE_PREF_SIZE);
        return toAdd;
    }

}
