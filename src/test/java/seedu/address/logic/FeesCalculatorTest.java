package seedu.address.logic;

import static seedu.address.logic.commands.CommandTestUtil.VALID_OUTSTANDING_FEES;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.LastUpdatedDate;
import seedu.address.model.lesson.OutstandingFees;

class FeesCalculatorTest {
    private final LastUpdatedDate lastUpdatedDate = new LastUpdatedDate("2021-10-15T00:00");
    private FeesCalculator feesCalculator =
            new FeesCalculator(lastUpdatedDate, LocalDateTime.parse("2021-10-25T08:00"));

    @Test
    public void updateLessonOutstandingFeesField_recurringLessons() {
        // Last updated within the same week, lesson has passed since last updated

        // Last updated within the same week, lesson has not passed since last updated

        // Last updated several weeks ago

        // Last updated today

    }

    @Test
    public void getUpdateOutstandingFees_success() {
        OutstandingFees outstandingFees = new OutstandingFees(VALID_OUTSTANDING_FEES); // start out at 100.00

    }
}
