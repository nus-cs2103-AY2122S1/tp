package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a Student's assessment.
 * Guarantees: immutable; is valid as declared in {@link #isValidAssessment(String)}
 */
public class Assessment {

    public static final String MESSAGE_CONSTRAINTS =
            "Assessment names should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    // Assessment score list
    public final Map<ID, Score> scores = new HashMap<>();

    // Assessment name
    public final String value;

    /**
     * Constructs an {@code Assessment}.
     *
     * @param name A valid assessment name.
     */
    public Assessment(String name) {
        requireNonNull(name);
        checkArgument(isValidAssessment(name), MESSAGE_CONSTRAINTS);
        value = name;
    }

    /**
     * Returns true if a given string is a valid assessment name.
     */
    public static boolean isValidAssessment(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getValue() {
        return value;
    }

    public Map<ID, Score> getScores() {
        return scores;
    }

    public void setScore(ID id, Score score) {
        scores.put(id, score);
    }

    public void setScores(Map<ID, Score> scores) {
        this.scores.putAll(scores);
    }

    /**
     * Returns true if the student with the given {@code id} is already graded.
     */
    public boolean isGraded(ID id) {
        requireNonNull(id);
        return scores.containsKey(id);
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * Returns true if both assessments have the same name.
     * This defines a weaker notion of equality between two assessments.
     */
    public boolean isSameAssessment(Assessment otherAssessment) {
        if (otherAssessment == this) {
            return true;
        }

        return otherAssessment != null
                && otherAssessment.getValue().equals(getValue());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Assessment // instanceof handles nulls
                && value.equals(((Assessment) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public static class AssessmentStatistics {
        public static final double DEFAULT_INTERVAL = 10.0;

        public static class Bin {
            private final Score binMinimum;
            private final Score binMaximum;
            private final boolean maxIsInclusive;

            public Bin(Score binMinimum, Score binMaximum) {
                this.binMinimum = binMinimum;
                this.binMaximum = binMaximum;
                maxIsInclusive = binMaximum.isMaxScore();
            }

            public boolean containsScore(Score score) {
                return score.getNumericValue() >= binMinimum.getNumericValue() &&
                        (maxIsInclusive
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

        private List<Bin> bins;
        public final Map<Bin, Integer> binCounts;

        public AssessmentStatistics(Assessment assessment) {
            this(assessment, DEFAULT_INTERVAL);
        }

        public AssessmentStatistics(Assessment assessment, double interval) {
            requireNonNull(assessment);
            requireNonNull(assessment.scores);

            bins = createBins(interval);
            binCounts = new HashMap<>();
            for (Bin b : bins) {
                binCounts.put(b, 0);
            }

            addScoresToBins(assessment.scores.values());
        }

        private List<Bin> createBins(double interval) {
            List<Bin> bins = new ArrayList<>();

            double binLowestValue = 0.0;

            while (binLowestValue < Score.MAX_SCORE) {
                Score binLowestScore = new Score(String.valueOf(binLowestValue));
                double binHighestValue = Math.min(binLowestValue + interval, Score.MAX_SCORE);
                Score binHighestScore = new Score(String.valueOf(binHighestValue));
                bins.add(new Bin(binLowestScore, binHighestScore));
                binLowestValue = binHighestValue;
            }

            return bins;
        }

        private Bin getBin(Score score) {
            for (Bin b : bins) {
                if (b.containsScore(score)) {
                    return b;
                }
            }

            // Should not happen since a Score must be between the minimum and maximum value (inclusive)
            assert false;

            return null;
        }

        private void addScoresToBins(Collection<Score> scores) {
            for (Score score : scores) {
                Bin binForScore = getBin(score);
                binCounts.put(binForScore, binCounts.get(binForScore) + 1);
            }
        }

        public Map<String, Number> getScoreDistribution() {
            Map<String, Number> distribution = new HashMap<>();
            binCounts.forEach((bin, count) -> distribution.put(bin.toString(), count));
            return distribution;
        }
    }

}
