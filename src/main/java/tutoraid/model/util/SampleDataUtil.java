package tutoraid.model.util;

import java.io.IOException;
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
import tutoraid.model.student.InitialStudent;
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

    public static InitialStudent[] getSampleStudents(ReadOnlyLessonBook lessonBook) {
        return new InitialStudent[] {
            // All details available
            new InitialStudent(new StudentName("Alex Yeoh"), new Phone("87438807"),
                    new ParentName("Mr Yeoh"), new Phone("93726483"),
                    new ProgressList(), new ArrayList<>(List.of("Maths 1"))),
            new InitialStudent(new StudentName("Bernice Yu"), new Phone("99272758"),
                    new ParentName("Mrs Yu"), new Phone("83548274"),
                    new ProgressList(), new ArrayList<>()),
            // Parent's details unavailable
            new InitialStudent(new StudentName("Charlotte Oliveiro"), new Phone("93210283"),
                    new ParentName(""), new Phone(""),
                    new ProgressList(), new ArrayList<>()),
            // Phone details unavailable
            new InitialStudent(new StudentName("David Li"), new Phone(""),
                    new ParentName("Mr Li"), new Phone(""),
                    new ProgressList(), new ArrayList<>()),
            // Phone and parent's details unavailable
            new InitialStudent(new StudentName("Irfan Ibrahim"), new Phone(""),
                    new ParentName(""), new Phone(""),
                    new ProgressList(), new ArrayList<>()),
            // Student phone unavailable
            new InitialStudent(new StudentName("Roy Balakrishnan"), new Phone(""),
                    new ParentName("Mrs Balakrishnan"), new Phone("93628676"),
                    new ProgressList(), new ArrayList<>())
        };
    }

    public static ReadOnlyStudentBook getSampleStudentBook(ReadOnlyLessonBook lessonBook) {
        StudentBook sampleSb = new StudentBook();
        try {
            for (InitialStudent sampleStudent : getSampleStudents(lessonBook)) {
                sampleSb.addStudent(sampleStudent.toStudent(lessonBook.getLessonList()));
            }
            return sampleSb;
        } catch (IOException e) {
            throw new RuntimeException("Could not create sample student book.");
        }
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
