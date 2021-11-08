package seedu.address.model.statistic;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Map;


/**
 * Represents a NationalityStatistic in the address book.
 * Guarantees: immutable
 */
public class NationalityStatistic {

    private final HashMap<String, Integer> nationalitiesCount;

    /**
     * Constructs a {@code NationalityStatistic}.
     *
     * @param nationalitiesCount A HashMap of nationalities and their count
     */
    public NationalityStatistic(HashMap<String, Integer> nationalitiesCount) {
        requireNonNull(nationalitiesCount);
        this.nationalitiesCount = nationalitiesCount;
    }

    public HashMap<String, Integer> getNationalitiesCount() {
        return nationalitiesCount;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof NationalityStatistic)) { //this handles null as well.
            return false;
        }

        NationalityStatistic otherNationalityStats = (NationalityStatistic) other;

        return this.nationalitiesCount.equals(otherNationalityStats.nationalitiesCount);
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        StringBuilder output = new StringBuilder("Nationalities statistics: \n");

        for (Map.Entry<String, Integer> entry: nationalitiesCount.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            output.append(String.format("%s: %d\n", key, value));
        }

        return output.toString();
    }

}
