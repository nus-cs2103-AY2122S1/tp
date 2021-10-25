package seedu.address.model.modulelesson;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code ModuleLesson}'s {@code LessonDay} matches the keyword given.
 */
public class LessonDayContainsKeywordsPredicate implements Predicate<ModuleLesson> {

    private final List<String> keywords;

    public LessonDayContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(ModuleLesson moduleLesson) {
        return keywords.stream()
                .anyMatch(keyword -> moduleLesson.getDay().getDayAsIntString().equals(keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LessonDayContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((LessonDayContainsKeywordsPredicate) other).keywords)); // state check
    }
}
