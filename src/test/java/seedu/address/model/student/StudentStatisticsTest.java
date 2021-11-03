package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.Map;

import org.junit.jupiter.api.Test;

public class StudentStatisticsTest {

    @Test
    public void getScoreDistribution() {
        StudentStatistics alice = new StudentStatistics(ALICE);
        Map<String, Number> aliceDistribution = Map.of("P01", 100.0);
        assertEquals(alice.getScoreDistribution(), aliceDistribution);

        StudentStatistics benson = new StudentStatistics(BENSON);
        Map<String, Number> bensonDistribution = Map.of("P01", 100.0, "P02", 100.0);
        assertEquals(benson.getScoreDistribution(), bensonDistribution);
    }
}
