package tutoraid.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * A generic class to use as an abstraction for card-like objects.
 * @param <T> The type of entity to be represented using a card
 */
public class Card<T> extends UiPart<Region> {
    public final T entry;
    protected Label id = new Label();

    /**
     * Constructor for a card object.
     *
     * @param fxml The FXML file
     * @param entry The object to be represented using the card
     * @param displayedIndex The index number of the card
     */
    public Card(String fxml, T entry, int displayedIndex) {
        super(fxml);
        this.entry = entry;
        id.setText(displayedIndex + ". ");
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Card)) {
            return false;
        }

        // state check
        Card<?> card = (Card<?>) other;
        return id.getText().equals(card.id.getText())
                && entry.equals(card.entry);
    }

    /**
     * Formats the text for a Card to include both the name and value if present.
     * If the value is empty, it is displayed as (None).
     * If the name is empty, we will display only the value.
     *
     * @param fieldName The name of the field
     * @param value     The value of the field
     * @return A formatted string that includes the field name and its value
     */
    public static String formatCardLabel(String fieldName, String value) {
        if (value.equals("")) {
            value = "(None)";
        }
        return fieldName.equals("")
                ? value
                : String.format("%s: %s", fieldName, value);
    }
}
