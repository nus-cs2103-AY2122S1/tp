package seedu.address.model.modulelesson;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code ModuleLesson}'s {@code ModuleCode}s matches the keyword given.
 */
public class ModuleCodeContainsKeywordsPredicate implements Predicate<ModuleLesson> {

    private final List<String> keywords;

    public ModuleCodeContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(ModuleLesson moduleLesson) {
        return keywords.stream()
                .allMatch(keyword -> moduleLesson.getModuleCode().getModuleCodeName().equalsIgnoreCase(keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleCodeContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ModuleCodeContainsKeywordsPredicate) other).keywords)); // state check
    }
}
