package seedu.academydirectory.logic.commands;

import static seedu.academydirectory.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import seedu.academydirectory.logic.AdditionalViewType;
import seedu.academydirectory.logic.commands.exceptions.CommandException;
import seedu.academydirectory.model.AdditionalInfo;
import seedu.academydirectory.model.VersionedModel;
import seedu.academydirectory.model.student.Assessment;
import seedu.academydirectory.model.student.Student;

public class VisualiseCommand extends Command {

    public static final String COMMAND_WORD = "visualise";

    public static final String HELP_MESSAGE = "### Visualise class assessment statistic : `visualise`\n"
            + "\n"
            + "Tutors will be able to visualise class performance in assessment with box and whisker plot.\n"
            + "\n"
            + "Format: `visualise`\n"
            + "\n"
            + "Examples:\n"
            + "* `visualise`";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "visualise class performance in assessment with box and whisker plot. ";

    public static final String MESSAGE_VISUALISE_SUCCESS = "Class Performance in Assessment shown";

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(VersionedModel model) throws CommandException {
        //Always go back to the full list of students
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        List<Student> studentList = model.getFilteredStudentList();

        Map<String, List<Integer>> classAssessmentResults = studentList
                                .stream()
                                .map(Student::getAssessment)
                                .map(Assessment::getAssessmentHashMap)
                                .flatMap(hashMap -> hashMap.entrySet().stream())
                                .collect(Collectors.groupingBy(
                                        Map.Entry::getKey,
                                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())));

        model.setAdditionalViewType(AdditionalViewType.VISUALISE);
        model.setAdditionalInfo(AdditionalInfo.of(classAssessmentResults));

        return new CommandResult(MESSAGE_VISUALISE_SUCCESS);
    }
}
