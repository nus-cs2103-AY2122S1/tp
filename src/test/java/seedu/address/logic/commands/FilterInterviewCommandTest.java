package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class FilterInterviewCommandTest {

    @Test
    public void equals() {

        FilterInterviewCommand firstFilterPastInterviewCommand = new FilterInterviewPastCommand();
        FilterInterviewCommand secondFilterPastInterviewCommand = new FilterInterviewPastCommand();
        FilterInterviewCommand filterInterviewFutureCommand = new FilterInterviewFutureCommand();

        // same object -> returns true
        assertEquals(firstFilterPastInterviewCommand, firstFilterPastInterviewCommand);

        // same values -> returns true
        assertEquals(firstFilterPastInterviewCommand, secondFilterPastInterviewCommand);

        // different values -> returns false
        assertNotEquals(firstFilterPastInterviewCommand, filterInterviewFutureCommand);

        // different types -> returns false
        assertNotEquals(firstFilterPastInterviewCommand, 1);

        // null -> returns false
        assertNotEquals(firstFilterPastInterviewCommand, null);
    }

}
