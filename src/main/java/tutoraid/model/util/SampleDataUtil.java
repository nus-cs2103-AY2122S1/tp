package tutoraid.model.util;

import java.util.ArrayList;
import java.util.List;

import tutoraid.model.LessonBook;
import tutoraid.model.ReadOnlyLessonBook;
import tutoraid.model.ReadOnlyStudentBook;
import tutoraid.model.StudentBook;
import tutoraid.model.lesson.Capacity;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.lesson.LessonName;
import tutoraid.model.lesson.Price;
import tutoraid.model.lesson.Timing;
import tutoraid.model.student.Lessons;
import tutoraid.model.student.ParentName;
import tutoraid.model.student.Phone;
import tutoraid.model.student.ProgressList;
import tutoraid.model.student.Student;
import tutoraid.model.student.StudentName;

/**
 * Contains utility methods for populating {@code StudentBook} and {@code LessonBook} with sample data.
 */
public class SampleDataUtil {

    public static Student[] getSampleStudents(ReadOnlyLessonBook lessonBook) {
        return new Student[] {
            // All details available
            new Student(new StudentName("Alex Yeoh"), new Phone("87438807"),
                    new ParentName("Mr Yeoh"), new Phone("93726483"),
                    new ProgressList(),
                    new Lessons(new ArrayList<>(List.of("Maths 1")), lessonBook)),
            new Student(new StudentName("Bernice Yu"), new Phone("99272758"),
                    new ParentName("Mrs Yu"), new Phone("83548274"),
                    new ProgressList(), new Lessons()),
            // Parent's details unavailable
            new Student(new StudentName("Charlotte Oliveiro"), new Phone("93210283"),
                    new ParentName(""), new Phone(""),
                    new ProgressList(), new Lessons()),
            // Phone details unavailable
            new Student(new StudentName("David Li"), new Phone(""),
                    new ParentName("Mr Li"), new Phone(""),
                    new ProgressList(), new Lessons()),
            // Phone and parent's details unavailable
            new Student(new StudentName("Irfan Ibrahim"), new Phone(""),
                    new ParentName(""), new Phone(""),
                    new ProgressList(), new Lessons()),
            // Student phone unavailable
            new Student(new StudentName("Roy Balakrishnan"), new Phone(""),
                    new ParentName("Mrs Balakrishnan"), new Phone("93628676"),
                    new ProgressList(), new Lessons())
        };
    }

    public static ReadOnlyStudentBook getSampleStudentBook(ReadOnlyLessonBook lessonBook) {
        StudentBook sampleSb = new StudentBook();
        for (Student sampleStudent : getSampleStudents(lessonBook)) {
            sampleSb.addStudent(sampleStudent);
        }
        return sampleSb;
    }

    public static Lesson[] getSampleLessons() {
        return new Lesson[] {
            new Lesson(new LessonName("Maths 1"), new Capacity("50"),
                    new Price("100"),
                    new Timing("1000-1200"))
        };
    }

    public static ReadOnlyLessonBook getSampleLessonBook() {
        LessonBook sampleLb = new LessonBook();
        for (Lesson sampleLesson : getSampleLessons()) {
            sampleLb.addLesson(sampleLesson);
        }
        return sampleLb;
    }

}
