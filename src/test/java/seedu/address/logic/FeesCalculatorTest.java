package seedu.address.logic;

import org.junit.jupiter.api.Test;
import seedu.address.model.LastUpdatedDate;
import seedu.address.model.lesson.OutstandingFees;

import java.time.LocalDateTime;

import static seedu.address.logic.commands.CommandTestUtil.VALID_OUTSTANDING_FEES;

class FeesCalculatorTest {
    private final LastUpdatedDate lastUpdatedDate = new LastUpdatedDate("2021-10-15T00:00");
    FeesCalculator feesCalculator = new FeesCalculator(lastUpdatedDate, LocalDateTime.parse("2021-10-25T08:00"));

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
