package seedu.address.logic;

import java.util.Optional;
import java.util.Set;

import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;

public interface Calculator {
    static final float lessonCycle = 4.00F;
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
        Optional<Float> total = lessons
                .stream()
                .map(lesson -> lesson.getOutstandingFees().getMonetaryValueInFloat())
                .reduce((curr, next) -> curr + next);

        return total.get();
    }

    /**
     * For reminding when the fees are due. In cycles of 4 lessons.
     */
    static float getThreshold(float costPerLesson) {
        return costPerLesson * lessonCycle;
    }
}
