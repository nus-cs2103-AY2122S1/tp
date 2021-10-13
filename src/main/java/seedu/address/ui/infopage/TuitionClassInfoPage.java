package seedu.address.ui.infopage;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.tuition.TuitionClass;

public class TuitionClassInfoPage extends InfoPage {

    private static final String FXML = "TuitionClassInfoPage.fxml";

    private static final Logger logger = LogsCenter.getLogger(TuitionClassInfoPage.class);

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private final TuitionClass tuitionClass;

    @FXML
    private VBox tuitionPagePane;
    @FXML
    private Label name;
    @FXML
    private Label sizeLimit;
    @FXML
    private Label studentCount;
    @FXML
    private Label timeSlot;
    @FXML
    private Label students;
    @FXML
    private Label remark;

    /**
     * Constructor for a TuitionClassInfoPage
     * @param tuitionClass Tuition Class to display information of.
     */
    public TuitionClassInfoPage(TuitionClass tuitionClass) {
        super(FXML);
        logger.info("TuitionClassInfoPage " + tuitionClass);

        this.tuitionClass = tuitionClass;
        this.name.setText(tuitionClass.getName().toString());
        this.sizeLimit.setText("Maximum students: " + tuitionClass.getLimit());
        this.studentCount.setText("No. of students: " + tuitionClass.getStudentCount());
        this.timeSlot.setText("Timeslot: " + tuitionClass.getTimeslot());
        this.students.setText("Students: " + tuitionClass.listStudents());
        this.remark.setText("Remark: " + tuitionClass.getRemark());
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TuitionClassInfoPage)) {
            return false;
        }

        // state check
        TuitionClassInfoPage otherInfoPage = (TuitionClassInfoPage) other;
        return this.tuitionClass.equals(otherInfoPage.tuitionClass);
    }

}
