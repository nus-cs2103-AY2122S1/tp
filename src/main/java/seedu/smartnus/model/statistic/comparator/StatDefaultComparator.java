package seedu.smartnus.model.statistic.comparator;

import java.util.Comparator;

import seedu.smartnus.model.statistic.TagStatistic;

public class StatDefaultComparator implements Comparator<TagStatistic> {

    @Override
    public int compare(TagStatistic o1, TagStatistic o2) {
        int res;
        Integer firstStat = o1.getCorrectPercentage();
        Integer secondStat = o2.getCorrectPercentage();
        res = firstStat.compareTo(secondStat);
        // if percentages are the same, then show the one with more attempts first
        if (res == 0) {
            Integer firstStatAttempts = o1.getAttemptCount();
            Integer secondStatAttempts = o2.getAttemptCount();
            res = -firstStatAttempts.compareTo(secondStatAttempts);
        }
        return res;
    }
}
