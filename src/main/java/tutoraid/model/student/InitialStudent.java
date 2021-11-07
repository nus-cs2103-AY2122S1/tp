package tutoraid.model.student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tutoraid.commons.util.CollectionUtil;
import tutoraid.model.lesson.Lesson;

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
     * At the same time, Student objects are added to the respective Lesson objects.
     *
     * @param fullLessonList A list of available lessons in TutorAid
     * @return The resulting Student object
     */
    public Student toStudent(List<Lesson> fullLessonList) throws IOException {
        Lessons lessons = new Lessons();
        Student student = new Student(studentName, studentPhone, parentName, parentPhone, progressList, lessons);
        for (String lessonName : lessonNames) {
            addStudentLessonLink(lessonName, fullLessonList, lessons, student);
        }
        return student;
    }

    /**
     * Finds lessons by a lesson name and creates the dependency between the Lesson object and the Student object.
     *
     * @param lessonName The name of the lesson
     * @param fullLessonList The list of all lessons
     * @param lessons The lessons object of the Student
     * @param student The Student object involved in the dependency
     * @throws IOException if there are too many students in a lesson (over-capacity)
     */
    public void addStudentLessonLink(String lessonName, List<Lesson> fullLessonList, Lessons lessons, Student student)
            throws IOException {
        for (Lesson lesson : fullLessonList) {
            if (lesson.toNameString().equals(lessonName) && lesson.isFull()) {
                fullLessonList.forEach(Lesson::removeAllStudents);
                throw new IOException();
            } else if (lesson.toNameString().equals(lessonName)) {
                lessons.addLesson(lesson);
                lesson.addStudent(student);
            }
        }
    }

    public StudentName getStudentName() {
        return studentName;
    }

    /**
     * Returns true if both initial students have the same identity and data fields.
     * This defines a stronger notion of equality between two initial students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof InitialStudent)) {
            return false;
        }

        InitialStudent otherStudent = (InitialStudent) other;
        return otherStudent.studentName.equals(studentName)
                && otherStudent.studentPhone.equals(studentPhone)
                && otherStudent.parentName.equals(parentName)
                && otherStudent.parentPhone.equals(parentPhone)
                && otherStudent.progressList.equals(progressList)
                && otherStudent.lessonNames.equals(lessonNames);
    }
}
