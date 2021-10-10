package seedu.address.ui.util;

import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

/**
 * @@author Roland09 - reused
 * Reused from: https://gist.github.com/Roland09/6fb31781a64d9cb62179#file-tableutils-java
 * with modifications
 */
public class TableUtil {
    /**
     * Installs the keyboard handler: CTRL + C or CMD + C = copy to clipboard..
     *
     * @param table Table to copy cell values from.
     */
    public static void installCopyPasteHandler(TableView<?> table) {
        // install copy/paste keyboard handler
        table.setOnKeyPressed(new TableKeyEventHandler());
    }

    /**
     * Wrapper class for copy keyboard event handler.
     * The handler uses the keyEvent's source for the clipboard data. The source must be of type TableView.
     */
    public static class TableKeyEventHandler implements EventHandler<KeyEvent> {
        private final KeyCodeCombination copyKeyCodeCombiWin =
                new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_ANY);
        private final KeyCodeCombination copyKeyCodeCombiMac =
                new KeyCodeCombination(KeyCode.C, KeyCombination.META_ANY);

        /**
         * Handles key events when keys are pressed.
         *
         * @param keyEvent Event of keys pressed.
         */
        public void handle(final KeyEvent keyEvent) {
            if (copyKeyCodeCombiWin.match(keyEvent) || copyKeyCodeCombiMac.match(keyEvent)) {

                if (keyEvent.getSource() instanceof TableView) {
                    // copy to clipboard
                    copySelectionToClipboard((TableView<?>) keyEvent.getSource());

                    // event is handled, consume it
                    keyEvent.consume();
                }
            }
        }
    }

    /**
     * Gets table selection and copies the selected cell values to the clipboard.
     *
     * @param table Table of the selected cells.
     */
    public static void copySelectionToClipboard(TableView<?> table) {

        StringBuilder clipboardString = new StringBuilder();

        ObservableList<TablePosition> positionList = table.getSelectionModel().getSelectedCells();

        int prevRow = -1;

        for (TablePosition position : positionList) {

            int row = position.getRow();
            int col = position.getColumn();

            // determine whether we advance in a row (tab) or a column (newline).
            if (prevRow == row) {
                clipboardString.append('\t');
            } else if (prevRow != -1) {
                clipboardString.append('\n');
            }

            // create string from cell
            String text = "";

            Object observableValue = table.getColumns().get(col).getCellObservableValue(row);

            if (observableValue == null) {
                text = "";
            } else if (observableValue instanceof StringProperty) {
                text = ((StringProperty) observableValue).get();
            } else {
                assert observableValue instanceof Property : "Unsupported observable value: " + observableValue;
            }
            // add new item to clipboard
            clipboardString.append(text);
            // remember previous
            prevRow = row;
        }

        // create clipboard content
        final ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putString(clipboardString.toString());

        // set clipboard content
        Clipboard.getSystemClipboard().setContent(clipboardContent);
    }
}
