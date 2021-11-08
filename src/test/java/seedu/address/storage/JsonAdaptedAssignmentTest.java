package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assignment.Description;
import seedu.address.model.assignment.DueDate;
import seedu.address.model.assignment.Status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

public class JsonAdaptedAssignmentTest {
    private static final String INVALID_DESCRIPTION = "#Assignment 1|17 Sep 2021, 11:59 PM|PENDING";
    private static final String INVALID_DUE_DATE = "Assignment 1|32 Sep 2021, 11:59 PM|PENDING";
    private static final String INVALID_STATUS = "Assignment 1|17 Sep 2021, 11:59 PM|FINISHED";

    @Test
    public void toModelType_validAssignmentDetails_returnsAssignment() throws Exception {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(BENSON.getAssignments().get(0));
        assertEquals(BENSON.getAssignments().get(0), assignment.toModelType());
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() throws Exception {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(INVALID_DESCRIPTION);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_invalidDueDate_throwsIllegalValueException() throws Exception {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(INVALID_DUE_DATE);
        String expectedMessage = DueDate.OUTPUT_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() throws Exception {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(INVALID_STATUS);
        String expectedMessage = Status.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }
}
