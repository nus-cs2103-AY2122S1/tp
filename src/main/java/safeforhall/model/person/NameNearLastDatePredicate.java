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
        this.date1 = date.toLocalDate();
        this.date2 = date.toLocalDate();
        this.keyword = keyword;
    }

    /**
     * Creates a NameNearLastDatePredicate with the optional date parameter {@code LastDate}
     */
    public NameNearLastDatePredicate(String keyword, LastDate date1, LastDate date2) {
        this.date1 = date1.toLocalDate();
        this.date2 = date2.toLocalDate();
        this.keyword = keyword;
    }

    /**
     * Tests if the given {@code Person} object's FET or Collection Date is due in a week's time by adding their
     * lastDate's week by 1 and comparing with the given date.
     *
     * @return true if the person's lastDate added by 1 falls within the given date or the given range of dates
     */
    @Override
    public boolean test(Person person) {
        LastDate currentPersonLastDate;
        LocalDate deadline;
        if (keyword.equals("f")) {
            currentPersonLastDate = person.getLastFetDate();
        } else {
            currentPersonLastDate = person.getLastCollectionDate();
        }
        deadline = currentPersonLastDate.getDeadline();
        long p1 = ChronoUnit.DAYS.between(date1, deadline);
        long p2 = ChronoUnit.DAYS.between(deadline, date2);
        return ((p1 >= 0) && (p2 >= 0));
    }
}
