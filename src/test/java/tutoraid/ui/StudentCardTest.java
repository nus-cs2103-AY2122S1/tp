package tutoraid.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tutoraid.ui.StudentCard.formatCardLabel;

import org.junit.jupiter.api.Test;



public class StudentCardTest {

    @Test
    public void formatCardLabel_emptyLabelEmptyField() {
        assertEquals("(None)", formatCardLabel("", ""));
    }

    @Test
    public void formatCardLabel_emptyLabelValidField() {
        assertEquals("Value", formatCardLabel("", "Value"));
    }

    @Test
    public void formatCardLabel_validLabelEmptyField() {
        assertEquals("Field: (None)", formatCardLabel("Field", ""));
    }

    @Test
    public void formatCardLabel_validLabelValidField() {
        assertEquals("Field: Name", formatCardLabel("Field", "Name"));
    }
}
