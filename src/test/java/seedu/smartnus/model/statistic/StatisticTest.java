package seedu.smartnus.model.statistic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StatisticTest {

    private Statistic defaultStat;

    @BeforeEach
    void setUp() {
        defaultStat = new Statistic();
    }

    @Test
    public void equals_test() {
        Statistic tenAttempts = new Statistic(10, 5);

        assertEquals(0, defaultStat.getAttemptCount());
        assertEquals(0, defaultStat.getCorrectCount());
        assertEquals(0, defaultStat.getCorrectPercentage());

        assertEquals(10, tenAttempts.getAttemptCount());
        assertEquals(5, tenAttempts.getCorrectCount());
        assertEquals(50, tenAttempts.getCorrectPercentage());
    }

    @Test
    public void update_test() {
        Statistic defaultStat = new Statistic();
        defaultStat.addAttempt();
        defaultStat.addCorrect();
        assertEquals(1, defaultStat.getAttemptCount());
        assertEquals(1, defaultStat.getCorrectCount());
        assertEquals(100, defaultStat.getCorrectPercentage());

        defaultStat.addAttempt();
        assertEquals(2, defaultStat.getAttemptCount());
        assertEquals(1, defaultStat.getCorrectCount());
        assertEquals(50, defaultStat.getCorrectPercentage());
    }

    @Test
    public void string_test() {
        Statistic defaultStat = new Statistic();
        assertNotEquals(null, defaultStat.toString());
    }

    @Test
    public void isValidStatistic_returnsTrue() {
        Statistic statistic = new Statistic();

        statistic.addAttempt(5);
        statistic.addCorrect(2);
        assertTrue(statistic.isValidStatistic());

        statistic.addCorrect(3);
        assertTrue(statistic.isValidStatistic());
    }

    @Test
    public void isValidStatistic_returnsFalse() {
        Statistic statistic = new Statistic();

        statistic.addAttempt(-1);
        assertFalse(statistic.isValidStatistic());

        statistic.addAttempt(3);
        statistic.addCorrect(-1);
        assertFalse(statistic.isValidStatistic());

        statistic.addCorrect(5);
        assertFalse(statistic.isValidStatistic());
    }

    @Test
    void compareTo() {
        Statistic testStatistic = new Statistic(0, 0);

        // same stats
        assertEquals(testStatistic.compareTo(defaultStat), 0);

        testStatistic.addAttempt();
        // same percentage but testStatistic has more attempts
        assertEquals(testStatistic.compareTo(defaultStat), -1);

        testStatistic.addAttempt();
        testStatistic.addCorrect();
        // testStatistic is 50% while default is 0%
        assertEquals(testStatistic.compareTo(defaultStat), 1);

        defaultStat.addCorrect();
        defaultStat.addAttempt();
        // default is 100%, test is 0%
        assertEquals(testStatistic.compareTo(defaultStat), -1);
    }
}
