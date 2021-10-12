package safeforhall.model.person;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

public class NameMissedDeadlinePredicate implements Predicate<Person> {
    private final LocalDate date;
    private final String keyword;

    /**
     * Creates a NameMissedDeadlinePredicate without the date parameter
     */
    public NameMissedDeadlinePredicate(String keyword, LastDate date) {
        this.date = date.toLocalDate();
        this.keyword = keyword;
    }

    /**
     * Tests if the given {@code Person} object's FET or Collection Date is due before the given date.
     *
     * @return true if the person's deadline is due before the given date
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
        long period = ChronoUnit.DAYS.between(deadline, date);
        return period > 0;
    }
}
