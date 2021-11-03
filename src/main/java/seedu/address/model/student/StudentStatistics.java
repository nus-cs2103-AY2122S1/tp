package seedu.address.model.student;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.TreeMap;

import javafx.scene.chart.Chart;
import seedu.address.commons.util.ChartUtil;

/**
 * Represents statistics about a student and the students' performance in each assessment.
 */
public class StudentStatistics {

    private static final String CHART_TITLE = "'s Results";
    private static final String CHART_X_AXIS_LABEL = "Assessments";
    private static final String CHART_Y_AXIS_LABEL = "Scores";

    private final Student student;
    private final Map<Assessment, Score> scoreMap;

    /**
     * Constructs a {@code studentStatistics} with the specified {@code student}.
     */
    public StudentStatistics(Student student) {
        requireNonNull(student);
        requireNonNull(student.getScores());
        this.student = student;
        this.scoreMap = student.getScores();
    }

    /**
     * Returns a distribution of student's scores for the assessment.
     */
    public Map<String, Number> getScoreDistribution() {
        Map<String, Number> distribution = new TreeMap<>();
        scoreMap.forEach((assessment, score) -> distribution.put(assessment.getName(), score.getNumericValue()));
        return distribution;
    }

    /**
     * Returns a distribution of cohort mean and median for the assessment.
     *
     * @return array of {@code Map<String, Number>} where the first element contains the mean distribution,
     * and the second element contains the median distribution.
     */
    private Map<String, Number>[] getDataSet() {
        Map<String, Number> mean = new TreeMap<>();
        Map<String, Number> median = new TreeMap<>();
        scoreMap.forEach((assessment, score) -> {
            AssessmentStatistics statistics = new AssessmentStatistics(assessment);
            mean.put(assessment.getName(), statistics.getMean());
            median.put(assessment.getName(), statistics.getMedian());
        });
        @SuppressWarnings("unchecked")
        Map<String, Number>[] dataSet = new Map[]{mean, median};
        return dataSet;
    }

    /**
     * Returns a line chart representing the scores of student for each assessment.
     */
    public Chart toLineChart() {
        Map<String, Number>[] dataSet = getDataSet();
        return ChartUtil.createLineChart(student.getName() + CHART_TITLE,
                CHART_X_AXIS_LABEL, CHART_Y_AXIS_LABEL, "score", getScoreDistribution(), dataSet[0], dataSet[1]);
    }
}
