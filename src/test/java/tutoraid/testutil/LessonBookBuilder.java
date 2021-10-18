package tutoraid.testutil;

import tutoraid.model.LessonBook;
import tutoraid.model.lesson.Lesson;

/**
 * A utility class to help with building LessonBook objects.
 * Example usage: <br>
 *     {@code LessonBook lb = new LessonBookBuilder().withLesson("Maths_One").build();}
 */
public class LessonBookBuilder {

    private LessonBook lessonBook;

    public LessonBookBuilder() {
        lessonBook = new LessonBook();
    }

    public LessonBookBuilder(LessonBook lessonBook) {
        this.lessonBook = lessonBook;
    }

    /**
     * Adds a new {@code Lesson} to the {@code LessonBook} that we are building.
     */
    public LessonBookBuilder withLesson(Lesson lesson) {
        lessonBook.addLesson(lesson);
        return this;
    }

    public LessonBook build() {
        return lessonBook;
    }
}
