package tutoraid.model.lesson;

import java.util.List;
import java.util.function.Predicate;

import tutoraid.commons.util.StringUtil;

/**
 * Tests that a {@code Lesson}'s {@code LessonName} matches any of the keywords given.
 */
public class LessonNameContainsKeywordsPredicate implements Predicate<Lesson> {
    private final List<String> keywords;

    public LessonNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Lesson lesson) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(lesson.getLessonName().lessonName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LessonNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((LessonNameContainsKeywordsPredicate) other).keywords)); // state check
    }
}
