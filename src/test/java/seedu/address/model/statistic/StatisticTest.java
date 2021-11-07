package seedu.address.model.statistic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code Statistic}.
 */
public class StatisticTest {

    private final List<Person> personList = Arrays.asList(ALICE, BOB, BENSON, CARL, ELLE);
    private final Statistic statistic = new Statistic(personList);

    public StatisticTest() throws CommandException {
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Statistic(null));
    }

    @Test
    public void computeGenderStatistics() {
        GenderStatistic genderStatistic = statistic.computeGenderStatistic();
        GenderStatistic expectedGenderStatistic = new GenderStatistic(3, 2, 0);

        assertEquals(genderStatistic, expectedGenderStatistic);
    }

    @Test
    public void computeNationalityStatistic() throws CommandException {
        NationalityStatistic nationalityStatistic = statistic.computeNationalityStatistic();

        HashMap<String, Integer> nationalitiesCount = new HashMap<String, Integer>();
        for (Person p: personList) {
            String nationality = p.getNationality().toString();
            int currCount = nationalitiesCount.getOrDefault(nationality, 0);

            nationalitiesCount.put(nationality, currCount + 1);
        }

        NationalityStatistic expectedNationalityStatistic = new NationalityStatistic(nationalitiesCount);

        assertEquals(nationalityStatistic, expectedNationalityStatistic);
    }
}
