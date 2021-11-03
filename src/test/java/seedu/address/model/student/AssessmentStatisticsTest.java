package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.student.AssessmentStatistics.createBins;
import static seedu.address.model.student.AssessmentStatistics.Bin;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class AssessmentStatisticsTest {
    private static final Assessment assessment = new Assessment("P01");
    private static final AssessmentStatistics statistics = new AssessmentStatistics(assessment);

    @Test
    public void createBins_invalidBinSize_throwsException() {
        // Negative bin size
        assertThrows(IllegalArgumentException.class, () -> createBins(-1));

        // Zero bin size
        assertThrows(IllegalArgumentException.class, () -> createBins(0));

        // Bin size greater than max score
        assertThrows(IllegalArgumentException.class, () -> createBins(Score.MAX_SCORE + 0.01));
    }

    @Test
    public void createBins_normalBinSize_success() {
        List<Bin> expectedBins = Arrays.asList(
                new Bin(new Score("0"), new Score("10")),
                new Bin(new Score("10"), new Score("20")),
                new Bin(new Score("20"), new Score("30")),
                new Bin(new Score("30"), new Score("40")),
                new Bin(new Score("40"), new Score("50")),
                new Bin(new Score("50"), new Score("60")),
                new Bin(new Score("60"), new Score("70")),
                new Bin(new Score("70"), new Score("80")),
                new Bin(new Score("80"), new Score("90")),
                new Bin(new Score("90"), new Score("100"))
        );

        assertEquals(expectedBins, createBins(10));
    }

}
