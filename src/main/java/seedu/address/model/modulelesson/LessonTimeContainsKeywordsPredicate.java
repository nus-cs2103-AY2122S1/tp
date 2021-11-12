package seedu.address.model.modulelesson;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code ModuleLesson}'s {@code LessonTime} matches the keyword given.
 */
public class LessonTimeContainsKeywordsPredicate implements Predicate<ModuleLesson> {

    private final List<String> keywords;

    public LessonTimeContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(ModuleLesson moduleLesson) {
        return keywords.stream()
                .anyMatch(keyword -> moduleLesson.getLessonStartTime().toString().equals(keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LessonTimeContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((LessonTimeContainsKeywordsPredicate) other).keywords)); // state check
    }
}
