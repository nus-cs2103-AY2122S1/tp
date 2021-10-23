package seedu.address.model.student;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import javafx.scene.chart.Chart;
import seedu.address.commons.util.ChartUtil;

/**
 * Represents statistics about an assessment and the students' performance.
 */
public class AssessmentStatistics {
    public static final double DEFAULT_BIN_SIZE = 10.0;

    private static final String CHART_X_AXIS_LABEL = "Scores";
    private static final String CHART_Y_AXIS_LABEL = "Number of Students";

    private final Assessment assessment;
    private final Map<Bin, Integer> binCounts;
    private int numScores = 0;
    private double sumOfScores = 0.0;

    /**
     * Constructs a {@code AssessmentStatistics} with the specified {@code Assessment}.
     */
    public AssessmentStatistics(Assessment assessment) {
        this(assessment, DEFAULT_BIN_SIZE);
    }

    /**
     * Constructs a {@code AssessmentStatistics} with the specified {@code Assessment} and bin size.
     */
    public AssessmentStatistics(Assessment assessment, double binSize) {
        requireNonNull(assessment);
        requireNonNull(assessment.scores);

        this.assessment = assessment;

        List<Bin> bins = createBins(binSize);
        binCounts = new HashMap<>();
        for (Bin b : bins) {
            binCounts.put(b, 0);
        }

        for (Score score : assessment.scores.values()) {
            addScoreToBin(score);
            numScores++;
            sumOfScores += score.getNumericValue();
        }
    }

    /**
     * Creates a list of bins spanning from the minimum to maximum score, with each {@code Bin} having the specified
     * bin size.
     */
    private static List<Bin> createBins(double binSize) {
        List<Bin> bins = new ArrayList<>();

        double binLowestValue = Score.MIN_SCORE;

        while (binLowestValue < Score.MAX_SCORE) {
            Score binLowestScore = new Score(String.valueOf(binLowestValue));
            double binHighestValue = Math.min(binLowestValue + binSize, Score.MAX_SCORE);
            Score binHighestScore = new Score(String.valueOf(binHighestValue));
            bins.add(new Bin(binLowestScore, binHighestScore));
            binLowestValue = binHighestValue;
        }

        return bins;
    }

    /**
     * Returns the {@code Bin} that the {@code Score} belongs in.
     */
    private Bin getBin(Score score) {
        for (Bin b : binCounts.keySet()) {
            if (b.includesScore(score)) {
                return b;
            }
        }

        // Should not happen since a Score must be between the minimum and maximum value (inclusive)
        assert false;

        return null;
    }

    /**
     * Adds the specified {@code Score} to its corresponding {@code Bin}.
     */
    private void addScoreToBin(Score score) {
        Bin binForScore = getBin(score);
        binCounts.put(binForScore, binCounts.get(binForScore) + 1);
    }

    /**
     * Returns a distribution of scores for the assessment, with the bins in their string representations.
     */
    private Map<String, Number> getScoreDistribution() {
        Map<String, Number> distribution = new TreeMap<>();
        binCounts.forEach((bin, count) -> distribution.put(bin.toString(), count));
        return distribution;
    }

    /**
     * Returns the mean score for the {@code Assessment}.
     */
    public double getMean() {
        return sumOfScores / numScores;
    }

    /**
     * Returns a histogram representing the scores for the assessment.
     */
    public Chart toHistogram() {
        return ChartUtil.createBarChart(assessment.getValue(), CHART_X_AXIS_LABEL, CHART_Y_AXIS_LABEL,
                getScoreDistribution());
    }

    /**
     * Represents a bin in the histogram.
     */
    public static class Bin {
        private final Score binMinimum;
        private final Score binMaximum;
        private final boolean maxIsInclusive;

        /**
         * Constructs a {@code Bin} spanning from the specified minimum to the specified maximum score.
         */
        public Bin(Score binMinimum, Score binMaximum) {
            this.binMinimum = binMinimum;
            this.binMaximum = binMaximum;
            maxIsInclusive = binMaximum.isMaxScore();
        }

        /**
         * Returns whether the specified {@code Score} is included in this bin.
         */
        public boolean includesScore(Score score) {
            return score.getNumericValue() >= binMinimum.getNumericValue()
                    && (maxIsInclusive
                            ? score.getNumericValue() <= binMaximum.getNumericValue()
                            : score.getNumericValue() < binMaximum.getNumericValue());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Bin otherBin = (Bin) o;
            return binMinimum.equals(otherBin.binMinimum) && binMaximum.equals(otherBin.binMaximum);
        }

        @Override
        public int hashCode() {
            return Objects.hash(binMinimum, binMaximum);
        }

        @Override
        public String toString() {
            return String.format("%.0f-%.0f", binMinimum.getNumericValue(), binMaximum.getNumericValue());
        }
    }
}
