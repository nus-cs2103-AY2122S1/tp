package tutoraid.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import tutoraid.commons.exceptions.IllegalValueException;
import tutoraid.model.LessonBook;
import tutoraid.model.ReadOnlyLessonBook;
import tutoraid.model.lesson.Lesson;

/**
 * An Immutable LessonBook that is serializable to JSON format.
 */
@JsonRootName(value = "lessonbook")
public class JsonSerializableLessonBook {

    public static final String MESSAGE_DUPLICATE_LESSON = "Lessons list contains duplicate lesson(s).";

    private final List<JsonAdaptedLesson> lessons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableLessonBook} with the given students.
     */
    @JsonCreator
    public JsonSerializableLessonBook(@JsonProperty("lessons") List<JsonAdaptedLesson> lessons) {
        this.lessons.addAll(lessons);
    }

    /**
     * Converts a given {@code ReadOnlyLessonBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableLessonBook}.
     */
    public JsonSerializableLessonBook(ReadOnlyLessonBook source) {
        lessons.addAll(source.getLessonList().stream().map(JsonAdaptedLesson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this lesson book into the model's {@code LessonBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public LessonBook toModelType() throws IllegalValueException {
        LessonBook lessonBook = new LessonBook();
        for (JsonAdaptedLesson jsonAdaptedLesson: lessons) {
            Lesson lesson = jsonAdaptedLesson.toModelType();
            if (lessonBook.hasLesson(lesson)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_LESSON);
            }
            lessonBook.addLesson(lesson);
        }
        return lessonBook;
    }
}
