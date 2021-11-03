package seedu.address.logic;
import java.math.BigDecimal;
import java.util.Set;

import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;

public interface Calculator {
    /**
     * Calculates and updates all students' lesson's outstanding fees.
     *
     * @param model Model to be updated.
     * @return Updated model.
     */
    Model updateAllLessonOutstandingFees(Model model);

    /**
     * Calculates one student's total fee by summing up all outstanding
     * fees from their lessons.
     */
    static BigDecimal getStudentTotalFees(Set<Lesson> lessons) {
        BigDecimal total = lessons
                .stream()
                .map(lesson -> lesson.getOutstandingFees().getMonetaryValue())
                .reduce(BigDecimal.ZERO, (curr, next) -> curr.add(next));

        return total;
    }
}
