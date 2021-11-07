package tutoraid.model.student;

import java.util.List;
import java.util.Objects;

import tutoraid.commons.util.CollectionUtil;
import tutoraid.model.lesson.Lesson;

/**
 * Represents a Student in the TutorAid.
 * Guarantees: details are present and not null
 */
public class Student {

    // Identity fields
    private StudentName studentName;
    private Phone studentPhone;
    private ParentName parentName;
    private Phone parentPhone;

    // Data fields
    private ProgressList progressList;
    private Lessons lessons;

    /**
     * Constructor for a Student when the Lessons are not yet initialised
     */
    public Student(StudentName studentName, Phone studentPhone, ParentName parentName, Phone parentPhone,
                   ProgressList progressList) {
        CollectionUtil.requireAllNonNull(studentName, studentPhone, parentName, parentPhone, progressList);
        this.studentName = studentName;
        this.studentPhone = studentPhone;
        this.parentName = parentName;
        this.parentPhone = parentPhone;
        this.progressList = progressList;
        this.lessons = new Lessons();
    }

    /**
     * Every field must be present and not null.
     */
    public Student(StudentName studentName, Phone studentPhone, ParentName parentName, Phone parentPhone,
                   ProgressList progressList, Lessons lessons) {
        CollectionUtil.requireAllNonNull(studentName, studentPhone, parentName, parentPhone, progressList);
        this.studentName = studentName;
        this.studentPhone = studentPhone;
        this.parentName = parentName;
        this.parentPhone = parentPhone;
        this.progressList = progressList;
        this.lessons = lessons;
    }

    /**
     * Updates the dependency between each lesson and a student if the lesson gets edited
     *
     * @param studentList  A list containing all students in TutorAid
     * @param lessonToEdit The lesson being edited
     * @param editedLesson The edited lesson
     */
    public static void updateStudentLessonLink(List<Student> studentList, Lesson lessonToEdit, Lesson editedLesson) {
        for (Student student : studentList) {
            if (student.hasLesson(lessonToEdit)) {
                student.removeLesson(lessonToEdit);
                student.addLesson(editedLesson);
            }
        }
    }

    public StudentName getStudentName() {
        return studentName;
    }

    public Phone getStudentPhone() {
        return studentPhone;
    }

    public ParentName getParentName() {
        return parentName;
    }

    public Phone getParentPhone() {
        return parentPhone;
    }

    public ProgressList getProgressList() {
        return progressList;
    }

    public Progress getLatestProgress() {
        return progressList.getLatestProgress();
    }

    public Lessons getLessons() {
        return lessons;
    }

    public void addProgress(Progress toAdd) {
        progressList.addProgress(toAdd);
    }

    public Progress deleteLatestProgress() {
        return progressList.deleteLatestProgress();
    }

    public boolean isProgressListEmpty() {
        return progressList.isProgressListEmpty();
    }

    public void addLesson(Lesson toAttend) {
        lessons.addLesson(toAttend);
    }

    public void removeLesson(Lesson toQuit) {
        lessons.deleteLesson(toQuit);
    }

    public boolean hasLesson(Lesson lesson) {
        return lessons.hasLesson(lesson);
    }

    /**
     * Replaces the fields of this student with those of a different student to edit it
     *
     * @param student The student whose fields should replace this student
     */
    public void replace(Student student) {
        studentName = student.getStudentName();
        studentPhone = student.getStudentPhone();
        parentName = student.getParentName();
        parentPhone = student.getParentPhone();
        progressList = student.getProgressList();
    }

    /**
     * Returns true if both students have the same name.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getStudentName().equals(getStudentName());
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getStudentName().equals(getStudentName())
                && otherStudent.getStudentPhone().equals(getStudentPhone())
                && otherStudent.getParentName().equals(getParentName())
                && otherStudent.getParentPhone().equals(getParentPhone())
                && otherStudent.getProgressList().equals(getProgressList())
                && otherStudent.getLessons().equals(getLessons());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(studentName, studentPhone, parentName, parentPhone, progressList, lessons);
    }

    /**
     * Returns the name of the student in a string form
     *
     * @return The name of the student in a String
     */
    public String toNameString() {
        return this.getStudentName().toString();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("\nStudent's name: " + getStudentName());
        builder.append("\nStudent's phone: ")
                .append(getStudentPhone());
        builder.append("\nParent's name: ")
                .append(getParentName());
        builder.append("\nParent's phone: ")
                .append(getParentPhone());
        builder.append("\nProgress: ")
                .append(getLatestProgress())
                .append("\nLessons: ")
                .append(getLessons())
                .append("\n");
        return builder.toString();
    }

    /**
     * Returns a copy of the current student object by creating a new object with the same fields.
     *
     * @return Copy of this student object
     */
    public Student copy() {
        return new Student(
                new StudentName(getStudentName().toString()),
                new Phone(getStudentPhone().toString()),
                new ParentName(getParentName().toString()),
                new Phone(getParentPhone().toString()),
                this.progressList,
                this.lessons
        );
    }
}
