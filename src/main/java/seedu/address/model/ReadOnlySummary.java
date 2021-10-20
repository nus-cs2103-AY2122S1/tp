package seedu.address.model;

/**
 * Unmodifiable view of summary.
 */
public interface ReadOnlySummary {

    int getTotalElderly();

    int getOverdueVisits();

    int getVisitsNextWeek();

    int getVisitsNextMonth();

    int getVisitsLastWeek();

    int getVisitsLastMonth();
}
