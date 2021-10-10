package safeforhall.model.person;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

public class NameNearLastDatePredicate implements Predicate<Person> {
    private final LocalDate today;
    private final LocalDate lastDate;
    private final String keyword;

    /**
     * Creates a NameNearLastDatePredicate without the optional date parameter
     */
    public NameNearLastDatePredicate(String keyword) {
        this.today = LocalDate.now();
        this.lastDate = LocalDate.now();
        this.keyword = keyword;
    }

    /**
     * Creates a NameNearLastDatePredicate with the optional date parameter {@code LastDate}
     */
    public NameNearLastDatePredicate(String keyword, LastDate lastDate) {
        this.today = LocalDate.now();
        this.lastDate = lastDate.getLocalDate();
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        LocalDate currentPersonLastDate;
        if (keyword.equals("f")) {
            currentPersonLastDate = person.getLastFetDate().getLocalDate().plusMonths(1);
        } else {
            currentPersonLastDate = person.getLastCollectionDate().getLocalDate().plusMonths(1);
        }
        long p1 = ChronoUnit.DAYS.between(today, currentPersonLastDate);
        long p2 = ChronoUnit.DAYS.between(currentPersonLastDate, lastDate);
        return ((p1 >= 0) && (p2 >= 0));
    }
}
