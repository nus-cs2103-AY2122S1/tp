package seedu.address.logic;

import java.util.Optional;
import java.util.Set;

import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;

public interface Calculator {
    static final float LESSON_CYCLE = 4.00F;
    /**
     * Calculate and update all student's lesson's outstanding fees.
     *
     * @param model Model to be updated.
     * @return Updated model.
     */
    Model updateAllLessonOutstandingFees(Model model);

    /**
     * Calculate student's total fee by summing up all outstanding
     * fees from their lessons.
     */
    static float getStudentTotalFees(Set<Lesson> lessons) {
        float total = lessons
                .stream()
                .map(lesson -> lesson.getOutstandingFees().getMonetaryValueInFloat())
                .reduce(0.00F, (curr, next) -> curr + next);

        return total;
    }

    /**
     * For reminding when the fees are due. In cycles of 4 lessons.
     */
    static float getThreshold(float costPerLesson) {
        return costPerLesson * LESSON_CYCLE;
    }
}
