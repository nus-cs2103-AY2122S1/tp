package seedu.address.model.interview;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class InterviewTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Interview(null));
    }

    @Test
    public void constructor_invalidInterview_throwsIllegalArgumentException() {
        String invalidInterview = "monday";
        assertThrows(IllegalArgumentException.class, () -> new Interview(invalidInterview));
    }

    @Test
    public void isValidInterview() {
        // null interview
        assertThrows(NullPointerException.class, () -> Interview.isValidInterviewTime(null));

        // invalid interview
        assertFalse(Interview.isValidInterviewTime("monday")); // not following format
        assertFalse(Interview.isValidInterviewTime(" ")); // empty


        // valid interview
        assertTrue(Interview.isValidInterviewTime("-")); // empty interview stored in database
        assertTrue(Interview.isValidInterviewTime("2020-10-29, 10:30")); // 4 digit year
        assertTrue(Interview.isValidInterviewTime("20-10-29, 10:30")); // 2 digit year
        assertTrue(Interview.isValidInterviewTime("2020-09-29, 10:30")); // 2 digit month
        assertTrue(Interview.isValidInterviewTime("2020-9-29, 10:30")); // 1 digit month
        assertTrue(Interview.isValidInterviewTime("2020-9-09, 10:30")); // 2 digit day
        assertTrue(Interview.isValidInterviewTime("2020-9-9, 10:30")); // 1 digit day
        assertTrue(Interview.isValidInterviewTime("2020-09-29, 08:30")); // 2 digit time
        assertTrue(Interview.isValidInterviewTime("2020-09-29, 8:3")); // 1 digit time
    }

    @Test
    public void isEmptyInterview() {
        // EMPTY
        Interview emptyInterview = new Interview("");
        assertTrue(emptyInterview.isEmptyInterview());

        // NON EMPTY
        Interview nonEmptyInterview = new Interview("2021-10-31, 10:25");
        assertFalse(nonEmptyInterview.isEmptyInterview());
    }

    @Test
    public void hasInterviewPassed() {
        // PASSED
        Interview passedInterview = new Interview("99-08-12, 00:10");
        assertTrue(passedInterview.hasInterviewPassed());

        // NOT YET PASSED
        Interview notPassedInterview = new Interview("2100-08-12, 00:10");
        assertFalse(notPassedInterview.hasInterviewPassed());

    }
}
