package seedu.times.ui.timetabletab;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * A UI for the header of the timetable.
 */
public class TimetableHeader extends TimetableRegion {
    private static final String FXML = "timetableTab/TimetableHeader.fxml";

    @FXML
    private Label headerLabel;

    /**
     * Creates a new Timetable header with a string as its input and its width.
     *
     * @param headerStr String to be displayed on the header.
     * @param width Width of the header.
     */
    public TimetableHeader(String headerStr, int width) {
        super(FXML, width);
        this.headerLabel.setText(headerStr);
    }

}
