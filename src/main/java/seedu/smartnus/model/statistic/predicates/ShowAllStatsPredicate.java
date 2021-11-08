package seedu.smartnus.model.statistic.predicates;

import java.util.function.Predicate;

import seedu.smartnus.model.statistic.TagStatistic;

public class ShowAllStatsPredicate implements Predicate<TagStatistic> {

    @Override
    public boolean test(TagStatistic tagStatistic) {
        return true;
    }
}
