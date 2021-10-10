package safeforhall.model.person;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

public class NameNearLastDatePredicate implements Predicate<Person> {
    private final LocalDate date1;
    private final LocalDate date2;
    private final String keyword;

    /**
     * Creates a NameNearLastDatePredicate without the optional date parameter
     */
    public NameNearLastDatePredicate(String keyword, LastDate date) {
        this.date1 = date.getLocalDate();
        this.date2 = date.getLocalDate();
        this.keyword = keyword;
    }

    /**
     * Creates a NameNearLastDatePredicate with the optional date parameter {@code LastDate}
     */
    public NameNearLastDatePredicate(String keyword, LastDate date1, LastDate date2) {
        this.date1 = date1.getLocalDate();
        this.date2 = date2.getLocalDate();
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
        long p1 = ChronoUnit.DAYS.between(date1, currentPersonLastDate);
        long p2 = ChronoUnit.DAYS.between(currentPersonLastDate, date2);
        return ((p1 >= 0) && (p2 >= 0));
    }
}
