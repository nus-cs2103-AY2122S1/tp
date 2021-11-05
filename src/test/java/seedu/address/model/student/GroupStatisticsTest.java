package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;

import org.junit.jupiter.api.Test;

public class GroupStatisticsTest {

    private final ID aliceId = new ID("E0000001");
    private final ID benId = new ID("E0000002");

    private final Student alice = new Student(new Name("Alice"), aliceId);
    private final Student ben = new Student(new Name("Ben"), benId);

    private final Group group = new Group("T01A", Arrays.asList(aliceId, benId));

    private final Assessment P01 = new Assessment("P01");
    private final Assessment P02 = new Assessment("P02");
    private final Assessment P03 = new Assessment("P03");

    private final List<Assessment> assessmentList = Arrays.asList(P01, P02);

    private final Map<Assessment, Score> aliceScore = Map.of(
            P01, new Score("78"),
            P02, new Score("66")
    );

    private final Map<Assessment, Score> benScore = Map.of(
            P01, new Score("44"),
            P02, new Score("90")
    );

    private final Map<ID, Score> P01Score = Map.of(
            aliceId, new Score("78"),
            benId, new Score("44")
    );

    private final Map<ID, Score> P02Score = Map.of(
            aliceId, new Score("66"),
            benId, new Score("90")
    );

    private final Map<ID, Score> P01Score2 = Map.of(
            aliceId, new Score("78"),
            benId, new Score("44"),
            new ID("E0000003"), new Score("79")
    );

    private final Map<ID, Score> P02Score2 = Map.of(
            aliceId, new Score("66"),
            benId, new Score("90"),
            new ID("E0000003"), new Score("87")
    );


    @Test
    public void isGraded() {
        alice.addScores(aliceScore);
        P01.setScores(P01Score);
        P02.setScores(P02Score);
        GroupStatistics statistics = new GroupStatistics(group, assessmentList);

        assertTrue(statistics.isGraded(P01));
        assertTrue(statistics.isGraded(P02));
        assertFalse(statistics.isGraded(P03));
    }

    @Test
    public void getMedian_returnCorrectMedian() {
        alice.addScores(aliceScore);
        ben.addScores(benScore);
        P01.setScores(P01Score);
        P02.setScores(P02Score);
        GroupStatistics statistics = new GroupStatistics(group, assessmentList);

        assertEquals(statistics.getMedian(P01), 61);
        assertEquals(statistics.getMedian(P02), 78);
    }


    @Test
    public void getDataSet_returnsCorrectDataSet() {
        alice.addScores(aliceScore);
        ben.addScores(benScore);
        P01.setScores(P01Score2);
        P02.setScores(P02Score2);
        GroupStatistics statistics = new GroupStatistics(group, assessmentList);

        Map<String, Number> groupMedian = Map.of(
                "P01", 61.0,
                "P02", 78.0
        );
        assertEquals(groupMedian, statistics.getDataSet()[0]);

        Map<String, Number> expectedMean = Map.of(
                "P01", 61.0,
                "P02", 78.0
        );
        assertEquals(expectedMean, statistics.getDataSet()[0]);

        Map<String, Number> expectedMedian = Map.of(
                "P01", 67.0,
                "P02", 81.0
        );
        assertEquals(expectedMedian, statistics.getDataSet()[1]);
    }
}
