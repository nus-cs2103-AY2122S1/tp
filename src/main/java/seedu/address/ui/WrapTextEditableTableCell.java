package seedu.address.ui;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.converter.DefaultStringConverter;

/**
 * Wrapper class for a wrap text editable table cell.
 * From: https://stackoverflow.com/questions/43440067/wrapping-text-in-a-javafx-tableview-editable-textfieldtablecell
 * @@author Pavlo Viazovskyy
 * @param <S> Any subclass of Object
 */
public class WrapTextEditableTableCell<S>  extends TextFieldTableCell<S, String> {

    private final Text cellText;

    public WrapTextEditableTableCell() {
        super(new DefaultStringConverter());
        this.cellText = createText();
        cellText.setFill(Color.web("#424874"));
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setGraphic(cellText);
    }

    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (!isEmpty() && !isEditing()) {
            setGraphic(cellText);
        }
    }

    private Text createText() {
        Text text = new Text();
        text.wrappingWidthProperty().bind(widthProperty());
        text.textProperty().bind(itemProperty());
        return text;
    }
}