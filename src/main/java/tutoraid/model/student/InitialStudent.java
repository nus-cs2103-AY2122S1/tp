package tutoraid.model.student;

import java.util.ArrayList;
import java.util.List;

import tutoraid.commons.util.CollectionUtil;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.lesson.exceptions.LessonNotFoundException;

/**
 * Represents a Student in the TutorAid.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class InitialStudent {

    // Identity fields
    private final StudentName studentName;
    private final Phone studentPhone;
    private final ParentName parentName;
    private final Phone parentPhone;

    // Data fields
    private final ProgressList progressList;
    private final ArrayList<String> lessonNames;

    /**
     * Every field must be present and not null.
     */
    public InitialStudent(StudentName studentName, Phone studentPhone, ParentName parentName, Phone parentPhone,
                          ProgressList progressList, ArrayList<String> lessonNames) {
        CollectionUtil.requireAllNonNull(studentName, studentPhone, parentName, parentPhone, progressList);
        this.studentName = studentName;
        this.studentPhone = studentPhone;
        this.parentName = parentName;
        this.parentPhone = parentPhone;
        this.progressList = progressList;
        this.lessonNames = lessonNames;
    }


    /**
     * Convert an InitialStudent to a Student by creating the dependencies to Lesson objects.
     *
     * @param fullLessonList A list of available lessons in TutorAid
     * @return The resulting Student object
     */
    public Student toStudent(List<Lesson> fullLessonList) {
        Lessons lessons = new Lessons();
        for (String lessonName : lessonNames) {
            Lesson lesson = fullLessonList.stream()
                    .filter(l -> l.toNameString().equals(lessonName))
                    .findFirst()
                    .orElseThrow(LessonNotFoundException::new);
            lessons.addLesson(lesson);
        }
        return new Student(studentName, studentPhone, parentName, parentPhone, progressList, lessons);
    }

    public StudentName getStudentName() {
        return studentName;
    }
}
