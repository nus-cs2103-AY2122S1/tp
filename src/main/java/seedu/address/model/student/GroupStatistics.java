package seedu.address.model.student;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javafx.scene.chart.Chart;
import seedu.address.commons.util.ChartUtil;
import seedu.address.model.Model;

/**
 * Represents statistics about a student and the students' performance in each assessment.
 */
public class GroupStatistics {

    private static final String CHART_TITLE = "Performance of %1$s";
    private static final String CHART_X_AXIS_LABEL = "Assessments";
    private static final String CHART_Y_AXIS_LABEL = "Scores";

    private final Model model;
    private final Group group;
    private final List<ID> studentList;
    private final List<Assessment> assessmentList;

    /**
     * Constructs a {@code GroupStatistics} with the specified {@code group}.
     */
    public GroupStatistics(Group group, Model model) {
        requireNonNull(model);
        requireNonNull(group);
        requireNonNull(group.getStudents());
        this.model = model;
        this.group = group;
        this.studentList = group.getStudents();
        this.assessmentList = model.getAddressBook().getAssessmentList();
    }

    /**
     * Returns true if more than one student in group is graded in {@code assessment}.
     */
    private boolean isGraded(Assessment assessment) {
        for (ID id : studentList) {
            if (assessment.isGraded(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns mean and median score of students in group who are graded in {@code assessment}.
     *
     * @return array of {@code double} where the first element contains the mean score,
     * and the second element contains the median score.
     */
    private double[] getMeanAndMedian(Assessment assessment) {
        double sum = 0;
        int count = 0;
        ArrayList<Score> scores = new ArrayList<>();
        for (ID id : studentList) {
            if (!assessment.isGraded(id)) {
                continue;
            }
            scores.add(assessment.getScores().get(id));
            sum += assessment.getScores().get(id).getNumericValue();
            count++;
        }

        double mean = sum / count;
        double median;

        List<Double> sorted = scores.stream()
                .map(Score::getNumericValue)
                .sorted().collect(Collectors.toList());
        int midPos; // middle position of the sorted list
        if (count % 2 == 1) { // odd number of scores
            midPos = (int) ((count + 1) / 2.0 - 1);
            median = sorted.get(midPos);
        } else { // even number of scores
            midPos = (int) (count / 2.0);
            median = (sorted.get(midPos - 1)
                    + sorted.get(midPos)) / 2.0;
        }

        return new double[]{mean, median};
    }

    /**
     * Returns a distribution of scores for the assessment.
     *
     * @return array of {@code Map<String, Number>} where the first element contains the group mean distribution,
     * the second element contains the group median distribution,
     * the third element contains the cohort mean distribution,
     * and the fourth element contains the cohort median distribution.
     */
    private Map<String, Number>[] getDataSet() {
        Map<String, Number> groupMean = new TreeMap<>();
        Map<String, Number> cohortMean = new TreeMap<>();
        Map<String, Number> groupMedian = new TreeMap<>();
        Map<String, Number> cohortMedian = new TreeMap<>();

        for (Assessment assessment: assessmentList) {
            if (!isGraded(assessment)) {
                continue;
            }
            // group mean and median
            double[] groupData = getMeanAndMedian(assessment);
            groupMean.put(assessment.getName(), groupData[0]);
            groupMedian.put(assessment.getName(), groupData[1]);

            // cohort mean and median
            AssessmentStatistics statistics = new AssessmentStatistics(assessment);
            cohortMean.put(assessment.getName(), statistics.getMean());
            cohortMedian.put(assessment.getName(), statistics.getMedian());
        }

        @SuppressWarnings("unchecked")
        Map<String, Number>[] dataSet = new Map[]{groupMean, groupMedian, cohortMean, cohortMedian};
        return dataSet;
    }

    /**
     * Returns a line chart representing the scores of student for each assessment.
     */
    public Chart toLineChart() {
        Map<String, Number>[] dataSet = getDataSet();
        return ChartUtil.createLineChart(String.format(CHART_TITLE, group.getName()),
                CHART_X_AXIS_LABEL, CHART_Y_AXIS_LABEL, dataSet[0], dataSet[1], dataSet[2], dataSet[3]);
    }
}
