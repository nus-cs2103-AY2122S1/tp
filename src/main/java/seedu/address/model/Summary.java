package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.collections.ObservableList;
import seedu.address.model.person.LastVisit;
import seedu.address.model.person.Person;
import seedu.address.model.person.Visit;

/**
 * Represents the summary statistics of the Address Book.
 */
public class Summary implements ReadOnlySummary {

    private int totalElderly;
    private int overdueVisits;
    private int visitsNextWeek;
    private int visitsNextMonth;
    private int visitsLastWeek;
    private int visitsLastMonth;

    /**
     * Every field must be present and not null.
     */
    public Summary(ReadOnlyAddressBook addressBook) {
        requireAllNonNull(addressBook);
        setStatistics(addressBook);
    }

    public void setStatistics(ReadOnlyAddressBook addressBook) {
        requireNonNull(addressBook);
        ObservableList<Person> listOfPersons = addressBook.getPersonList();

        int overdueVisits = 0;
        int visitsNextWeek = 0;
        int visitsNextMonth = 0;
        int visitsLastWeek = 0;
        int visitsLastMonth = 0;

        for (int i = 0; i < listOfPersons.size(); i++) {
            Person person = listOfPersons.get(i);
            Visit visit = person.getVisit().get();
            LastVisit lastVisit = person.getLastVisit().get();

            if (visit.isOverdue()) {
                overdueVisits++;
            }
            if (visit.isNextSevenDays()) {
                visitsNextWeek++;
            }
            if (visit.isNextThirtyDays()) {
                visitsNextMonth++;
            }
            if (lastVisit.isLastSevenDays()) {
                visitsLastWeek++;
            }
            if (lastVisit.isLastThirtyDays()) {
                visitsLastMonth++;
            }
        }

        this.totalElderly = addressBook.getPersonList().size();
        this.overdueVisits = overdueVisits;
        this.visitsNextWeek = visitsNextWeek;
        this.visitsNextMonth = visitsNextMonth;
        this.visitsLastWeek = visitsLastWeek;
        this.visitsLastMonth = visitsLastMonth;
    }

    public int getTotalElderly() {
        return totalElderly;
    }

    public int getOverdueVisits() {
        return overdueVisits;
    }

    public int getVisitsNextWeek() {
        return visitsNextWeek;
    }

    public int getVisitsNextMonth() {
        return visitsNextMonth;
    }

    public int getVisitsLastWeek() {
        return visitsLastWeek;
    }

    public int getVisitsLastMonth() {
        return visitsLastMonth;
    }


}
