package seedu.address.model.statistic;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.Map;


/**
 * Represents a GenderStatistic in the address book.
 * Guarantees: immutable
 */
public class GenderStatistic {

    private final int numberOfMales;
    private final int numberOfFemales;
    private final int numberOfOthers;
    private final int total;

    /**
     * Constructs a {@code GenderStatistic}.
     *
     * @param numberOfMales Number of males
     * @param numberOfFemales Number of females
     * @param numberOfOthers Number of others
     */
    public GenderStatistic(int numberOfMales, int numberOfFemales, int numberOfOthers) {
        requireAllNonNull(numberOfMales, numberOfFemales, numberOfOthers);
        this.numberOfMales = numberOfMales;
        this.numberOfFemales = numberOfFemales;
        this.numberOfOthers = numberOfOthers;
        this.total = numberOfMales + numberOfFemales + numberOfOthers;
    }

    public Map<String, Integer> getGenderCount() {
        Map<String, Integer> genderCount = new HashMap<>();
        genderCount.put("Females", numberOfFemales);
        genderCount.put("Males", numberOfMales);
        genderCount.put("Others", numberOfOthers);

        return genderCount;
    }

    private float computePercentage(int amount) {
        return (float) amount / total * 100;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof GenderStatistic)) { //this handles null as well.
            return false;
        }

        GenderStatistic otherGenderStats = (GenderStatistic) other;

        return this.numberOfMales == otherGenderStats.numberOfMales
                && this.numberOfFemales == otherGenderStats.numberOfFemales
                && this.numberOfOthers == otherGenderStats.numberOfOthers;
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return String.format("Gender statistics: \nMale: %.1f%% \nFemale: %.1f%% \nOthers: %.1f%%",
                computePercentage(numberOfMales),
                computePercentage(numberOfFemales),
                computePercentage(numberOfOthers));
    }

}
