package seedu.address.commons.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.student.IdContainsKeywordsPredicate;
import seedu.address.model.student.Student;

/**
 * A container for Student name conflicts utility functions
 */
public class SameNameConflictUtil {

    /**
     * Resolves conflicts when students have the same name, and displays the conflicting student cards on the GUI.
     *
     * @param model current model.
     * @param matchedStudents list of students with the same conflicting name.
     */
    public static void showStudentsWithNameConflicts(Model model, List<Student> matchedStudents) {
        List<String> keywordsList = new ArrayList<>();
        for (Student student : matchedStudents) {
            String studentId = student.getId().getValue();
            if (!keywordsList.contains(studentId)) {
                keywordsList.add(studentId);
            }
        }
        Predicate predicate = new IdContainsKeywordsPredicate(keywordsList);
        model.updateFilteredStudentList(predicate);
    }
}
