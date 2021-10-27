package tutoraid.model.lesson;

import java.util.List;
import java.util.function.Predicate;

import tutoraid.commons.util.StringUtil;

/**
 * Tests that a {@code Lesson}'s {@code LessonName} contains any of the substrings given.
 */
public class LessonNameContainsSubstringsPredicate implements Predicate<Lesson> {
    private final List<String> substrings;

    public LessonNameContainsSubstringsPredicate(List<String> substrings) {
        this.substrings = substrings;
    }


    @Override
    public boolean test(Lesson lesson) {
        return substrings.stream()
                .anyMatch(substring ->
                        StringUtil.containsSubstringIgnoreCase(lesson.getLessonName().lessonName, substring));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LessonNameContainsSubstringsPredicate // instanceof handles nulls
                && substrings.equals(((LessonNameContainsSubstringsPredicate) other).substrings)); // state check
    }

}

