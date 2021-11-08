package seedu.smartnus.model.question.comparator;

import java.util.Comparator;

import seedu.smartnus.model.question.Importance;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.statistic.Statistic;

public class QuestionsDefaultComparator implements Comparator<Question> {
    @Override
    public int compare(Question q1, Question q2) {
        int res = 0;
        Importance q1Importance = q1.getImportance();
        Importance q2Importance = q2.getImportance();
        res = -q1Importance.compareTo(q2Importance);
        if (res == 0) {
            Statistic q1Statistic = q1.getStatistic();
            Statistic q2Statistic = q2.getStatistic();
            res = q1Statistic.compareTo(q2Statistic);
        }
        return res;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof QuestionsDefaultComparator; // instanceof handles nulls
    }
}
