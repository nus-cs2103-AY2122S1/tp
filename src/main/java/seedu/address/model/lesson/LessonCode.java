package seedu.address.model.lesson;

import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

import seedu.address.model.person.Grade;

/**
 * Represents a LessonCode for a Lesson in tuitiONE book.
 */
public class LessonCode {

    public static final String CODE_MESSAGE_CONSTRAINT = "Lesson code should be of correct format";

    public final String value;

    /**
     * Constructs a {@code LessonCode}.
     *
     * @param code A valid lesson code.
     */
    public LessonCode(String code) {
        requireNonNull(code);
        checkArgument(isValidLessonCode(code), CODE_MESSAGE_CONSTRAINT);
        this.value = code;
    }

    /**
     * Returns formatted {@code LessonCode} based on the respective parameters, e.g. Science-P5-Wed-1430.
     */
    public static LessonCode getLessonCode(Subject subject, Grade grade, LessonTime lessonTime) {
        String code = String.format(
                "%1$s-%2$s-%3$s-%4$s",
                subject,
                grade.value,
                LessonTime.parseDayToString(lessonTime.dayOfWeek),
                lessonTime.startTime.format(LessonTime.TIME_FORMATTER));
        return new LessonCode(code);
    }

    /**
     * Returns true if a given lesson code is follows the correct format.
     */
    public static boolean isValidLessonCode(String testCode) {
        if (testCode == null) {
            return false;
        }
        // check number of parameters in lesson code
        String[] testLessonParams = testCode.split("-");
        if (testLessonParams.length != 4) {
            return false;
        }
        if (!Subject.isValidSubject(testLessonParams[0]) || !Grade.isValidGrade(testLessonParams[1])) {
            return false;
        }
        try {
            // attempt to parse
            LessonTime.parseStringToDay(testLessonParams[2]).orElseThrow();
            LocalTime.parse(testLessonParams[3], LessonTime.TIME_FORMATTER);
        } catch (IllegalArgumentException | DateTimeParseException | NoSuchElementException e) {
            return false;
        }
        return true;
    }

    /**
     * Provides the lesson time for a given lesson code.
     */
    public static LessonTime getLessonTimeFromCode(LessonCode code) {
        String[] lessonFields = code.value.split("-");
        return new LessonTime(
                LessonTime.parseStringToDay(lessonFields[2]).orElseThrow(),
                LocalTime.parse(lessonFields[3], LessonTime.TIME_FORMATTER)
        );
    }

    @Override
    public int hashCode() {
        return hash(value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof LessonCode)) {
            return false;
        }
        LessonCode otherLessonCode = (LessonCode) other;
        return value.equals(otherLessonCode.value);
    }

    @Override
    public String toString() {
        return value;
    }
}
