package seedu.address.model.statistic;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.logic.commands.StatisticsCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Person;

/**
 * Represents a Statistic in the address book.
 * Guarantees: immutable
 */
public class Statistic {

    public final List<Person> list;
    private final NationalityStatistic nationalityStatistic;
    private final GenderStatistic genderStatistic;

    /**
     * Constructs a {@code Statistic}.
     *
     * @param list A valid filtered persons list.
     */
    public Statistic(List<Person> list) throws CommandException {
        requireNonNull(list);
        this.list = list;

        nationalityStatistic = computeNationalityStatistic();
        genderStatistic = computeGenderStatistic();
    }

    GenderStatistic computeGenderStatistic() {
        int numberOfMales = 0;
        int numberOfFemales = 0;
        int numberOfOthers = 0;

        for (Person p: list) {
            Gender gender = p.getGender();

            if (gender.isMale()) {
                numberOfMales++;
            } else if (gender.isFemale()) {
                numberOfFemales++;
            } else {
                numberOfOthers++;
            }
        }

        return new GenderStatistic(numberOfMales, numberOfFemales, numberOfOthers);
    }

    NationalityStatistic computeNationalityStatistic() throws CommandException {
        HashMap<String, Integer> nationalitiesCount = new HashMap<String, Integer>();

        for (Person p: list) {
            String nationality = p.getNationality().toString();

            try {
                nationality = nationality.substring(0, 1).toUpperCase() + nationality.substring(1);
            } catch (StringIndexOutOfBoundsException e) {
                throw new CommandException(String.format(StatisticsCommand.MESSAGE_TG_FIELD_MISSING, "Nationalities"));
            }

            int currCount = nationalitiesCount.getOrDefault(nationality, 0);

            nationalitiesCount.put(nationality, currCount + 1);
        }

        return new NationalityStatistic(nationalitiesCount);
    }

    /**
     * Returns a map of map with the key being the grouping condition and the value being the statistics
     * based on the grouping condition
     *
     * @return a map of map of statistics
     */
    public Map<String, Map<String, Integer>> getRawData() throws CommandException {
        Map<String, Map<String, Integer>> rawData = new HashMap<>();
        Map<String, Integer> nationalitiesCount = nationalityStatistic.getNationalitiesCount();;
        Map<String, Integer> gendersCount = genderStatistic.getGenderCount();;

        rawData.put("Nationality Statistics", nationalitiesCount);
        rawData.put("Gender Statistics", gendersCount);

        return rawData;
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Statistic)) { //this handles null as well.
            return false;
        }

        Statistic otherStats = (Statistic) other;

        return this.list.equals(otherStats.list);
    }

    /**
     * Format statistics as text for viewing.
     */
    public String toString() {
        return String.format("%s\n\n%s", genderStatistic, nationalityStatistic);
    }

}
