package seedu.academydirectory.logic.commands;

import static seedu.academydirectory.model.VersionedModel.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import seedu.academydirectory.logic.AdditionalViewType;
import seedu.academydirectory.logic.commands.exceptions.CommandException;
import seedu.academydirectory.model.AdditionalInfo;
import seedu.academydirectory.model.VersionedModel;
import seedu.academydirectory.model.student.Assessment;
import seedu.academydirectory.model.student.Student;

public class VisualizeCommand extends Command {

    public static final String COMMAND_WORD = "visualize";

    public static final String HELP_MESSAGE = "#### Visualize class assessment statistic : `visualize`\n"
            + "\n"
            + "Tutors will be able to visualize class performance in assessment with box and whisker plot.\n"
            + "\n"
            + "Format: `visualize`\n"
            + "\n"
            + "Examples:\n"
            + "* `visualize`";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "visualize class performance in assessment with box and whisker plot. ";

    public static final String MESSAGE_VISUALIZE_SUCCESS = "Class performance in assessments shown";
    public static final String MESSAGE_NO_STUDENT_TO_VISUALIZE = "No students found to visualize";

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code VersionedModel} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(VersionedModel model) throws CommandException {
        //Always go back to the full list of students
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        List<Student> studentList = model.getFilteredStudentList();

        LinkedHashMap<String, List<Integer>> orderedAssessmentResults = getOrderedAssessmentResults(studentList);

        model.setAdditionalViewType(AdditionalViewType.VISUALIZE);
        model.setAdditionalInfo(AdditionalInfo.of(orderedAssessmentResults));

        if (studentList.size() == 0) {
            return new CommandResult(MESSAGE_NO_STUDENT_TO_VISUALIZE);
        } else {
            return new CommandResult(MESSAGE_VISUALIZE_SUCCESS);
        }
    }

    private static LinkedHashMap<String, List<Integer>> emptyAssessmentResults() {
        LinkedHashMap<String, List<Integer>> emptyMap = new LinkedHashMap<>();
        for (String assessment: Assessment.ASSESSMENT_LIST) {
            emptyMap.put(assessment, new ArrayList<>());
        }
        return emptyMap;
    }

    /**
     * Function to make a hashmap containing a list of grades per assessment
     * @param studentList List of students in the classd
     * @return linked hashmap to maintain the order of printing
     */
    public LinkedHashMap<String, List<Integer>> getOrderedAssessmentResults(List<Student> studentList) {
        Map<String, List<Integer>> classAssessmentResults = studentList
                .stream()
                .map(Student::getAssessment)
                .map(Assessment::getAssessmentHashMap)
                .flatMap(hashMap -> hashMap.entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())));

        if (classAssessmentResults.isEmpty()) {
            return emptyAssessmentResults();
        }

        LinkedHashMap<String, List<Integer>> orderedAssessmentResults = new LinkedHashMap<>();

        for (String assessment: Assessment.ASSESSMENT_LIST) {
            orderedAssessmentResults.put(assessment, classAssessmentResults.get(assessment));
        }
        return orderedAssessmentResults;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof VisualizeCommand);
    }
}
